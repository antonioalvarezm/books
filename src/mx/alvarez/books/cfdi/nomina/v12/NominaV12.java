package mx.alvarez.books.cfdi.nomina.v12;

import java.util.List;

import org.apache.log4j.Logger;

import mx.alvarez.books.model.Cfdis;
import mx.alvarez.books.model.ReciboNomina;
import mx.alvarez.books.model.ReciboNominaDetalle;
import mx.alvarez.persistence.BooksDAO;
import mx.alvarez.utils.Utils;
import mx.bigdata.sat.common.nomina.v12.schema.Nomina;

public class NominaV12 {

	private Logger logger = Logger.getLogger(NominaV12.class);

	private BooksDAO dao;
	private Cfdis cfdi;
	private Nomina nomina;

	public NominaV12(BooksDAO dao, Cfdis cfdi, Nomina nomina) {
		super();
		this.dao = dao;
		this.cfdi = cfdi;
		this.nomina = nomina;
	}

	/**
	 * 
	 */
	public void addNomina() {
		int id = 0;
		try {
			ReciboNomina reciboNomina = new ReciboNomina();
			reciboNomina.setId(0);
			reciboNomina.setCfdi(cfdi);

			reciboNomina.setPatronRfc(cfdi.getEmisor());
			reciboNomina.setRegistroPatronal(nomina.getEmisor().getRegistroPatronal());
			reciboNomina.setFechaInicioLaboral(Utils.getCalendar(nomina.getReceptor().getFechaInicioRelLaboral()));
			reciboNomina.setCurp(nomina.getReceptor().getCurp());
			reciboNomina.setNss(nomina.getReceptor().getNumSeguridadSocial());
			reciboNomina.setFechaInicial(Utils.getCalendar(nomina.getReceptor().getFechaInicioRelLaboral()));
			reciboNomina.setAntiguedad(nomina.getReceptor().getAntigüedad());
			reciboNomina.setTipoContrato(Utils.stringToInt(nomina.getReceptor().getTipoContrato()));
			reciboNomina.setTipoJornada(Utils.stringToInt(nomina.getReceptor().getTipoJornada()));
			reciboNomina.setTipoRegimen(Utils.stringToInt(nomina.getReceptor().getTipoRegimen()));
			reciboNomina.setNumEmpleado(nomina.getReceptor().getNumEmpleado());
			reciboNomina.setDepartamento(nomina.getReceptor().getDepartamento());
			reciboNomina.setPuesto(nomina.getReceptor().getPuesto());
			reciboNomina.setRiesgoPuesto(Utils.stringToInt(nomina.getReceptor().getRiesgoPuesto()));
			reciboNomina.setPeriodicidadPago(Utils.stringToInt(nomina.getReceptor().getPeriodicidadPago()));
			reciboNomina.setBanco(Utils.stringToInt(nomina.getReceptor().getBanco()));
			reciboNomina.setCuentaBancaria(String.valueOf(nomina.getReceptor().getCuentaBancaria().toString()));
			reciboNomina.setSalarioDiarioIntegrado(nomina.getReceptor().getSalarioDiarioIntegrado());
			reciboNomina.setTipoNomina(nomina.getTipoNomina().value());
			reciboNomina.setFechaPago(Utils.getCalendar(nomina.getFechaPago()));
			reciboNomina.setFechaInicial(Utils.getCalendar(nomina.getFechaInicialPago()));
			reciboNomina.setFechaFinal(Utils.getCalendar(nomina.getFechaFinalPago()));
			reciboNomina.setNumDias(nomina.getNumDiasPagados());
			reciboNomina.setTotalPercepciones(nomina.getTotalPercepciones());
			if (nomina.getTotalDeducciones() != null) {
				reciboNomina.setTotalDeducciones(nomina.getTotalDeducciones());
			} else {
				reciboNomina.setTotalDeducciones(Utils.stringToBigDecimal("0.00"));
			}
			reciboNomina.setTotalOtrosPagos(nomina.getTotalOtrosPagos());
			reciboNomina.setTotalSueldos(nomina.getPercepciones().getTotalSueldos());
			reciboNomina.setTotalGravado(nomina.getPercepciones().getTotalGravado());
			reciboNomina.setTotalExento(nomina.getPercepciones().getTotalExento());
			try {
				reciboNomina.setTotalOtrasDeducciones(nomina.getDeducciones().getTotalOtrasDeducciones());
			} catch (Exception e) {
				logger.warn(reciboNomina.getCfdi().getUuid() + " No tiene otras deducciones", e);
				reciboNomina.setTotalOtrasDeducciones(Utils.stringToBigDecimal("0.00"));
			}
			try {
				reciboNomina.setTotalImpuestosRetenidos(nomina.getDeducciones().getTotalImpuestosRetenidos());
			} catch (Exception e) {
				logger.warn(reciboNomina.getCfdi().getUuid() + " No tiene retencion de impuestos", e);
				reciboNomina.setTotalImpuestosRetenidos(Utils.stringToBigDecimal("0.00"));
			}

			id = dao.save(reciboNomina);
			dao.commit();
			reciboNomina.setId(id);
//			System.out.println(reciboNomina);
			logger.info(reciboNomina);
			if (id > 0) {
				addPercepciones(reciboNomina);
				addDeducciones(reciboNomina);
				addOtros(reciboNomina);
			}
		} catch (Exception e) {
			logger.error(cfdi.getUuid(), e);
		}
	}

	/**
	 * 
	 * @param reciboNomina
	 */
	private void addPercepciones(ReciboNomina reciboNomina) {
		try {
			List<Nomina.Percepciones.Percepcion> percepciones = nomina.getPercepciones().getPercepcion();
			for (Nomina.Percepciones.Percepcion p : percepciones) {
				ReciboNominaDetalle detalle = new ReciboNominaDetalle();
				detalle.setId(0);
				detalle.setReciboNomina(reciboNomina);
				detalle.setClase("P");
				detalle.setTipo(p.getTipoPercepcion());
				detalle.setClave(p.getClave());
				detalle.setConcepto(p.getConcepto());
				detalle.setImporteGravado(p.getImporteGravado());
				detalle.setImporteExento(p.getImporteExento());
				detalle.setImporte(Utils.stringToBigDecimal("0.00"));
				int id = dao.save(detalle);
				dao.commit();
				detalle.setId(id);
				logger.info(detalle);
//				System.out.println(detalle);
			}
		} catch (Exception e) {
			logger.error(reciboNomina.getCfdi().getUuid(), e);
		}
	}

	/**
	 * 
	 * @param reciboNomina
	 */
	private void addDeducciones(ReciboNomina reciboNomina) {
		try {
			List<Nomina.Deducciones.Deduccion> deducciones = nomina.getDeducciones().getDeduccion();
			for (Nomina.Deducciones.Deduccion d : deducciones) {
				ReciboNominaDetalle detalle = new ReciboNominaDetalle();
				detalle.setId(0);
				detalle.setReciboNomina(reciboNomina);
				detalle.setClase("D");
				detalle.setTipo(d.getTipoDeduccion());
				detalle.setClave(d.getClave());
				detalle.setConcepto(d.getConcepto());
				detalle.setImporteGravado(Utils.stringToBigDecimal("0.00"));
				detalle.setImporteExento(Utils.stringToBigDecimal("0.00"));
				detalle.setImporte(d.getImporte());
				int id = dao.save(detalle);
				dao.commit();
				detalle.setId(id);
				logger.info(detalle);
//				System.out.println(detalle);
			}
		} catch (Exception e) {
			logger.error(reciboNomina.getCfdi().getUuid(), e);
		}
	}

	/**
	 * 
	 * @param reciboNomina
	 */
	private void addOtros(ReciboNomina reciboNomina) {
		try {
			List<Nomina.OtrosPagos.OtroPago> otros = nomina.getOtrosPagos().getOtroPago();
			for (Nomina.OtrosPagos.OtroPago o : otros) {
				ReciboNominaDetalle detalle = new ReciboNominaDetalle();
				detalle.setId(0);
				detalle.setReciboNomina(reciboNomina);
				detalle.setClase("O");
				detalle.setTipo(o.getTipoOtroPago());
				detalle.setClave(o.getClave());
				detalle.setConcepto(o.getConcepto());
				detalle.setImporteGravado(Utils.stringToBigDecimal("0.00"));
				detalle.setImporteExento(Utils.stringToBigDecimal("0.00"));
				detalle.setImporte(o.getImporte());
				int id = dao.save(detalle);
				dao.commit();
				detalle.setId(id);
				logger.info(detalle);
//				System.out.println(detalle);
			}
		} catch (Exception e) {
			logger.error(reciboNomina.getCfdi().getUuid(), e);
		}
	}

}
