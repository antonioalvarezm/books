package mx.alvarez.persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import mx.alvarez.books.model.Cfdis;
import mx.alvarez.books.model.CfdisConceptos;
import mx.alvarez.books.model.CfdisConceptosImpuestos;
import mx.alvarez.books.model.CfdisImpuestos;
import mx.alvarez.books.model.Entidades;
import mx.alvarez.books.model.Pagos;
import mx.alvarez.books.model.PagosDocsRelacionados;
import mx.alvarez.books.model.ReciboNomina;
import mx.alvarez.books.model.ReciboNominaDetalle;
import mx.alvarez.sql.Select;

public class BooksDAO extends DAO {

	private Logger logger = Logger.getLogger(BooksDAO.class);

	public BooksDAO() {
		super();
	}

	public int lastInsertKey() {
		int id = 0;
		try {
			Statement st = getConnection().createStatement();
//			ResultSet rs = st.getGeneratedKeys();
			ResultSet rs = st.executeQuery("select last_insert_id() id");
			while (rs.next()) {
				id = rs.getInt("id");
			}
			if (id > 0) {
				st.executeQuery("select last_insert_id(0) id");
			}
			rs.close();
			rs = null;
			st.close();
			st = null;
		} catch (Exception e) {
			logger.error(e);
		}
		return id;
	}

	public List<Entidades> getListEntidades() {
		List<Entidades> list = null;
		try {
			list = namedFindAll(Entidades.class, "entidades.all");
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public int save(Entidades entidades) {
		int id = 0;
		try {
			if (entidades.getId() == 0) {
				super.create(entidades);
				id = lastInsertKey();
			} else {
				super.merge(entidades);
				id = entidades.getId();
			}
		} catch (Exception e) {
			logger.error(entidades.toString(), e);
		}
		return id;
	}

	public Cfdis getCfdiByUUID(String uuid) {
		Cfdis cfdi = null;
		try {
			Select select = new Select();
			select.addTable("cfdis");
			select.addField("*");
			select.addWhereEq("cfdi_uuid", uuid);
			List<Cfdis> list = createNativeQuery(Cfdis.class, select.build());
			if (list.size() > 0) {
				cfdi = list.get(0);
			}
		} catch (Exception e) {
			logger.error(uuid, e);
		}
		return cfdi;
	}

	public int save(Cfdis cfdi) {
		int id = 0;
		try {
			if (cfdi.getId() == 0) {
				Cfdis c = getCfdiByUUID(cfdi.getUuid());
				if (c == null) {
					super.create(cfdi);
					id = getCfdiByUUID(cfdi.getUuid()).getId();
				} else {
					logger.warn(cfdi.getUuid() + " ya fue cargado");
				}
			} else {
				super.merge(cfdi);
				id = cfdi.getId();
			}
		} catch (Exception e) {
			logger.error(cfdi.toString(), e);
		}
		return id;
	}

	public int save(CfdisImpuestos impuesto) {
		int id = 0;
		try {
			if (impuesto.getId() == 0) {
				super.create(impuesto);
				id = lastInsertKey();
			} else {
				super.merge(impuesto);
				id = impuesto.getId();
			}
		} catch (Exception e) {
			logger.error(impuesto.toString(), e);
		}
		return id;
	}

	public int save(CfdisConceptos concepto) {
		int id = 0;
		try {
			if (concepto.getId() == 0) {
				super.create(concepto);
				id = lastInsertKey();
			} else {
				super.merge(concepto);
				id = concepto.getId();
			}
		} catch (Exception e) {
			logger.error(concepto.toString(), e);
		}
		return id;
	}

	public int save(CfdisConceptosImpuestos impuesto) {
		int id = 0;
		try {
			if (impuesto.getId() == 0) {
				super.create(impuesto);
				id = lastInsertKey();
			} else {
				super.merge(impuesto);
				id = impuesto.getId();
			}
		} catch (Exception e) {
			logger.error(impuesto.toString(), e);
		}
		return id;
	}

	public int save(ReciboNomina reciboNomina) {
		int id = 0;
		try {
			if (reciboNomina.getId() == 0) {
				super.create(reciboNomina);
				id = lastInsertKey();
			} else {
				super.merge(reciboNomina);
				id = reciboNomina.getId();
			}
		} catch (Exception e) {
			logger.error(reciboNomina.toString(), e);
		}
		return id;
	}

	public int save(ReciboNominaDetalle detalle) {
		int id = 0;
		try {
			if (detalle.getId() == 0) {
				super.create(detalle);
				id = lastInsertKey();
			} else {
				super.merge(detalle);
				id = detalle.getId();
			}
		} catch (Exception e) {
			logger.error(detalle.toString(), e);
		}
		return id;
	}

	public int save(Pagos pago) {
		int id = 0;
		try {
			if (pago.getId() == 0) {
				super.create(pago);
				id = lastInsertKey();
			} else {
				super.merge(pago);
				id = pago.getId();
			}
		} catch (Exception e) {
			logger.error(pago.toString(), e);
		}
		return id;
	}

	public int save(PagosDocsRelacionados doc) {
		int id = 0;
		try {
			if (doc.getId() == 0) {
				super.create(doc);
				id = lastInsertKey();
			} else {
				super.merge(doc);
				id = doc.getId();
			}
		} catch (Exception e) {
			logger.error(doc.toString(), e);
		}
		return id;
	}

	public void commit() {
		super.commit();
	}

}
