package mx.alvarez.books;

import mx.alvarez.books.cfdi.ParseCfdi;
import mx.alvarez.persistence.BooksDAO;

public class MainMultipleParseCFDI {
	
	public static void main(String[] args) {
		BooksDAO dao = new BooksDAO();
		dao.connect();
		ParseCfdi p = new ParseCfdi(dao);
		p.readAllXML("archivos/");
		dao.commit();
		dao.close();
	}

}
