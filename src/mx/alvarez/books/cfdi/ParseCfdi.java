package mx.alvarez.books.cfdi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import mx.alvarez.books.cfdi.v33.ComprobanteV33;
import mx.alvarez.books.model.Entidades;
import mx.alvarez.io.ReusableInputStream;
import mx.alvarez.persistence.BooksDAO;
import mx.bigdata.sat.cfdi.CFDv33;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;

public class ParseCfdi {

	private Logger logger = Logger.getLogger(ParseCfdi.class);
	private BooksDAO dao;
	private List<Entidades> entidades;
	
	public ParseCfdi() {
		super();
	}

	public ParseCfdi(BooksDAO dao) {
		super();
		this.dao = dao;
		this.entidades = dao.getListEntidades();
	}

	public void readAllXML(String path) {
		if (path == null) {
			path = "archivos";
		}
		readListFilesForFolder(path);
	}

	public void readListFilesForFolder(String directory) {
		File folder = new File(directory);
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				readListFilesForFolder(fileEntry.getAbsolutePath());
			} else {
				logger.debug(fileEntry.getName());
				if (fileEntry.getName().toLowerCase().endsWith("zip")) {
//					unzip(directory, fileEntry.getName());
					readListFilesForFolder(directory);
				} else if (fileEntry.getName().toLowerCase().endsWith("xml")) {
					read(directory.concat(System.getProperty("file.separator")).concat(fileEntry.getName()));
//					v.add(fileEntry.getName());
				}
			}
		}
//		deleteFiles(directory);
	}

	public void read(String fileName) {
		int id = 0;
		try {
			logger.info("Parseara el archivo " + fileName);
			InputStream is = new ReusableInputStream(new FileInputStream(new File(fileName)));
			is = new ReusableInputStream(new FileInputStream(new File(fileName)));
			Reader reader = new InputStreamReader(is, "UTF-8");
			InputSource iss = new InputSource(reader);
			iss.setEncoding("UTF-8");
			id = read(is);
			logger.debug("id:" + id);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public int read(InputStream is) {
		int id = 0;
		try {
			CFDv33 cfdi = new CFDv33(is, "mx.bigdata.sat.common.nomina.v12.schema");
			Comprobante comprobante = (Comprobante) cfdi.getComprobante().getComprobante();
			ComprobanteV33 v33 = new ComprobanteV33(dao, comprobante);
			v33.setEntidades(entidades);
			id = v33.read();
		} catch (Exception e) {
			logger.error(e);
		}
		return id;
	}

}
