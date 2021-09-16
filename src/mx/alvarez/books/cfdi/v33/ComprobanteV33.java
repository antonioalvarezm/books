package mx.alvarez.books.cfdi.v33;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

import mx.alvarez.books.cfdi.nomina.v12.NominaV12;
import mx.alvarez.books.model.Cfdis;
import mx.alvarez.books.model.CfdisConceptos;
import mx.alvarez.books.model.CfdisConceptosImpuestos;
import mx.alvarez.books.model.CfdisImpuestos;
import mx.alvarez.books.model.Entidades;
import mx.alvarez.books.model.Pagos;
import mx.alvarez.books.model.PagosDocsRelacionados;
import mx.alvarez.commons.Constants;
import mx.alvarez.persistence.BooksDAO;
import mx.alvarez.utils.StringPadding;
import mx.alvarez.utils.Utils;

import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.cfdi.v33.schema.TimbreFiscalDigital;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Complemento;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Emisor;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Receptor;
import mx.bigdata.sat.common.nomina.v12.schema.Nomina;
import mx.bigdata.sat.common.pagos.schema.Pagos.Pago;
import mx.bigdata.sat.common.pagos.schema.Pagos.Pago.DoctoRelacionado;

public class ComprobanteV33 {

	private Logger logger = Logger.getLogger(ComprobanteV33.class);
	private BooksDAO dao;
	private List<Entidades> entidades;
	private Comprobante comprobante;
	private Nomina nomina;
	private boolean isNomina = false;

	public ComprobanteV33(BooksDAO dao, Comprobante comprobante) {
		this.dao = dao;
		this.comprobante = comprobante;
		this.nomina = getNomina(comprobante);
	}

	public List<Entidades> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Entidades> entidades) {
		this.entidades = entidades;
	}

	public int read() {
		return addComprobante();
	}

	private void lookAtEmisor(Emisor emisor) {
		Entidades entidad = new Entidades();
		try {
			entidad.setId(0);
			entidad.setRfc(emisor.getRfc());
			if (emisor.getNombre() != null) {
				entidad.setRazonSocial(emisor.getNombre());
			} else {
				entidad.setRazonSocial(" ");
			}
			lookAtEntidad(entidad);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void lookAtReceptor(Receptor receptor) {
		Entidades entidad = new Entidades();
		try {
			entidad.setId(0);
			entidad.setRfc(receptor.getRfc());
			if (receptor.getNombre() != null) {
				entidad.setRazonSocial(receptor.getNombre());
			} else {
				entidad.setRazonSocial(" ");
			}
			lookAtEntidad(entidad);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void lookAtEntidad(Entidades entidad) {
		boolean found = false;
		try {
			for (Entidades e : entidades) {
				if (e.getRfc().equals(entidad.getRfc())) {
					found = true;
					break;
				}
			}
			if (!found) {
				dao.save(entidad);
				dao.commit();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private int addComprobante() {
		int id = 0;
		Cfdis cfdi = null;
		try {
			lookAtEmisor(comprobante.getEmisor());
			lookAtReceptor(comprobante.getReceptor());
			cfdi = new Cfdis();
			cfdi.setId(0);
			cfdi.setEmisor(comprobante.getEmisor().getRfc());
			cfdi.setRegimenFiscal(Utils.stringToInt(comprobante.getEmisor().getRegimenFiscal()));
			cfdi.setReceptor(comprobante.getReceptor().getRfc());
			cfdi.setUso(comprobante.getReceptor().getUsoCFDI().value());
			cfdi.setTipoComprobante(comprobante.getTipoDeComprobante().value());
			cfdi.setFecha(Utils.getCalendar(comprobante.getFecha()));
			cfdi.setUuid(getTimbreFiscalDigital(comprobante).getUUID());
			cfdi.setEstatus(1);
			cfdi.setSerie(comprobante.getSerie());
			cfdi.setFolio(comprobante.getFolio());
			if (comprobante.getMetodoPago() == null) {
				cfdi.setMetodoPago("PUE");
			} else {
				cfdi.setMetodoPago(comprobante.getMetodoPago().value());
			}
			if (comprobante.getFormaPago() != null) {
				cfdi.setFormaPago(Utils.stringToInt(comprobante.getFormaPago()));
			} else {
				cfdi.setFormaPago(99);
			}
			if (!comprobante.getMoneda().value().equals("XXX")) {
				cfdi.setMoneda(comprobante.getMoneda().value());
			} else {
				cfdi.setMoneda("MXN");
			}
			if (comprobante.getMoneda().value().equals(Constants.MXN)) {
				cfdi.setTipoCambio(Utils.stringToBigDecimal("1"));
			} else {
				cfdi.setTipoCambio(comprobante.getTipoCambio());
			}
			cfdi.setSubtotal(comprobante.getSubTotal());
			if (comprobante.getDescuento() == null) {
				cfdi.setDescuento(new BigDecimal("0"));
			} else {
				cfdi.setDescuento(comprobante.getDescuento());
			}
			if (!isNomina) {
				try {
					if (comprobante.getImpuestos().getTotalImpuestosRetenidos() != null) {
						cfdi.setImpuestosRetenidos(comprobante.getImpuestos().getTotalImpuestosRetenidos());
					} else {
						cfdi.setImpuestosRetenidos(Utils.stringToBigDecimal("0"));
					}
				} catch (Exception e) {
					cfdi.setImpuestosRetenidos(Utils.stringToBigDecimal("0"));
					logger.warn("No tiene retencion de impuestos");
				}
				try {
					if (comprobante.getImpuestos().getTotalImpuestosTrasladados() != null) {
						cfdi.setImpuestosTrasladados(comprobante.getImpuestos().getTotalImpuestosTrasladados());
					} else {
						cfdi.setImpuestosTrasladados(Utils.stringToBigDecimal("0"));
					}
				} catch (Exception e) {
					cfdi.setImpuestosTrasladados(Utils.stringToBigDecimal("0"));
					logger.warn("No tiene impuestos a trasladar");
				}
			} else {
				cfdi.setImpuestosRetenidos(Utils.stringToBigDecimal("0"));
				cfdi.setImpuestosTrasladados(Utils.stringToBigDecimal("0"));
			}
			cfdi.setTotal(comprobante.getTotal());
			id = dao.save(cfdi);
			cfdi.setId(id);
			dao.commit();
			if (cfdi.getId() > 0) {
				logger.info(cfdi);
				if (isNomina) {
					NominaV12 nominaV12 = new NominaV12(dao, cfdi, nomina);
					nominaV12.addNomina();
				} else {
					if (cfdi.getTipoComprobante().equals("I")) {
						addImpuestosByCfdi(cfdi);
						addConceptos(cfdi);
					} else if (cfdi.getTipoComprobante().equals("P")) {
						addPago(cfdi);
					}
				}
			}
		} catch (Exception e) {
			logger.error(cfdi.getUuid(), e);
		}
		return id;
	}

	private void addImpuestosByCfdi(Cfdis cfdi) {
		try {
			if (comprobante.getImpuestos() != null) {
				List<mx.bigdata.sat.cfdi.v33.schema.Comprobante.Impuestos.Traslados.Traslado> traslados = comprobante
						.getImpuestos().getTraslados().getTraslado();
				for (mx.bigdata.sat.cfdi.v33.schema.Comprobante.Impuestos.Traslados.Traslado t : traslados) {
					CfdisImpuestos impuesto = new CfdisImpuestos();
					impuesto.setId(0);
					impuesto.setCfdi(cfdi);
					impuesto.setFactor(t.getTipoFactor().value().substring(0, 1));
					impuesto.setImpuestoId(Utils.stringToInt(t.getImpuesto()));
					impuesto.setTasaCuota(t.getTasaOCuota());
					impuesto.setImporte(t.getImporte());
					int id = dao.save(impuesto);
					dao.commit();
					impuesto.setId(id);
					logger.info(impuesto.toString());
				}
			}
		} catch (Exception e) {
			logger.warn(cfdi.getUuid() + " No tiene impuestos a trasladar", e);
		}
		try {
			if (comprobante.getImpuestos() != null) {
				List<mx.bigdata.sat.cfdi.v33.schema.Comprobante.Impuestos.Retenciones.Retencion> retenciones = comprobante
						.getImpuestos().getRetenciones().getRetencion();
				for (mx.bigdata.sat.cfdi.v33.schema.Comprobante.Impuestos.Retenciones.Retencion r : retenciones) {
					CfdisImpuestos impuesto = new CfdisImpuestos();
					impuesto.setId(0);
					impuesto.setCfdi(cfdi);
					impuesto.setImpuestoId(Utils.stringToInt(r.getImpuesto()));
					impuesto.setImporte(r.getImporte());
					int id = dao.save(impuesto);
					dao.commit();
					impuesto.setId(id);
					logger.info(impuesto.toString());
				}
			}
		} catch (Exception e) {
			logger.warn(cfdi.getUuid() + " No tiene retencio de impuestos", e);
		}
	}

	private void addConceptos(Cfdis cfdi) {
		try {
			List<Concepto> conceptos = comprobante.getConceptos().getConcepto();
			for (Concepto concepto : conceptos) {
				addConcepto(cfdi, concepto);
			}
		} catch (Exception e) {
			logger.error(cfdi.getUuid(), e);
		}
	}

	private void addConcepto(Cfdis cfdi, Concepto concepto) {
		int id = 0;
		try {
			CfdisConceptos cfdiConceptos = new CfdisConceptos();
			cfdiConceptos.setId(0);
			cfdiConceptos.setCfdi(cfdi);
			cfdiConceptos.setClaveProductoId(Utils.stringToInt(concepto.getClaveProdServ()));
			if (concepto.getNoIdentificacion() != null) {
				if (concepto.getNoIdentificacion().length() <= 50) {
					cfdiConceptos.setNoIdentificacion(concepto.getNoIdentificacion());
				} else {
					cfdiConceptos.setNoIdentificacion(concepto.getNoIdentificacion().substring(0, 50));
				}
			}
			cfdiConceptos.setCantidad(concepto.getCantidad());
			if (concepto.getUnidad() != null) {
				if (concepto.getUnidad().length() <= 10) {
					cfdiConceptos.setUnidad(concepto.getUnidad());
				} else {
					cfdiConceptos.setUnidad(concepto.getUnidad().substring(0, 10));
				}
			}
			cfdiConceptos.setDescripcion(concepto.getDescripcion());
			cfdiConceptos.setValorUnitario(concepto.getValorUnitario());
			cfdiConceptos.setImporte(concepto.getValorUnitario());
			id = dao.save(cfdiConceptos);
			dao.commit();
			if (id > 0) {
				cfdiConceptos.setId(id);
				logger.info(cfdiConceptos.toString());
				addImpuestosByConcepto(cfdiConceptos, concepto);
			}
		} catch (Exception e) {
			logger.error(cfdi.getUuid(), e);
		}
	}

	private void addImpuestosByConcepto(CfdisConceptos cfdiConceptos, Concepto concepto) {
		try {
			if (concepto.getImpuestos() != null) {
				List<Traslado> traslados = concepto.getImpuestos().getTraslados().getTraslado();
				for (Traslado t : traslados) {
					CfdisConceptosImpuestos impuesto = new CfdisConceptosImpuestos();
					impuesto.setId(0);
					impuesto.setCfdi(cfdiConceptos.getCfdi());
					impuesto.setConcepto(cfdiConceptos);
					impuesto.setFactor(t.getTipoFactor().value().substring(0, 1));
					impuesto.setImpuestoId(Utils.stringToInt(t.getImpuesto()));
					impuesto.setBase(t.getBase());
					impuesto.setTasaCuota(t.getTasaOCuota());
					impuesto.setImporte(t.getImporte());
					int id = dao.save(impuesto);
					dao.commit();
					impuesto.setId(id);
					logger.info(impuesto.toString());
				}
			}
		} catch (Exception e) {
			logger.warn(cfdiConceptos.getCfdi().getUuid() + " conceptoId:" + cfdiConceptos.getId() + " "
					+ concepto.getClaveProdServ() + " este concepto no tiene impuestos a trasladar", e);
		}
		try {
			if (comprobante.getImpuestos() != null) {
				List<mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion> retenciones = concepto
						.getImpuestos().getRetenciones().getRetencion();
				for (mx.bigdata.sat.cfdi.v33.schema.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion r : retenciones) {
					CfdisConceptosImpuestos impuesto = new CfdisConceptosImpuestos();
					impuesto.setId(0);
					impuesto.setCfdi(cfdiConceptos.getCfdi());
					impuesto.setConcepto(cfdiConceptos);
					impuesto.setImpuestoId(Utils.stringToInt(r.getImpuesto()));
					impuesto.setBase(r.getBase());
					impuesto.setImporte(r.getImporte());
					int id = dao.save(impuesto);
					dao.commit();
					impuesto.setId(id);
					logger.info(impuesto.toString());
				}
			}
		} catch (Exception e) {
			logger.warn(cfdiConceptos.getCfdi().getUuid() + " conceptoId:" + cfdiConceptos.getId() + " "
					+ concepto.getClaveProdServ() + " este concepto no tiene retencion de impuestos", e);
		}
	}

	private TimbreFiscalDigital getTimbreFiscalDigital(Comprobante comprobante) {
		int i = 0;
		int j = 0;
		TimbreFiscalDigital tfd = null;
		try {
			List<Complemento> complementos = comprobante.getComplemento();
			for (i = 0; i < complementos.size(); i++) {
				List<Object> list = complementos.get(i).getAny();
				for (j = 0; j < list.size(); j++) {
					if (list.get(j).toString().toLowerCase().contains("timbrefiscaldigital")) {
						tfd = (TimbreFiscalDigital) list.get(j);
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return tfd;
	}

	private Nomina getNomina(Comprobante comprobante) {
		int i = 0;
		Nomina nomina = null;
		try {
			List<Complemento> complementos = comprobante.getComplemento();
			for (i = 0; i < complementos.size(); i++) {
				List<Object> list = complementos.get(i).getAny();
				for (Iterator<Object> itr = list.iterator(); itr.hasNext();) {
					Object o = itr.next();
//					logger.info(o.getClass());
					if (o instanceof Nomina) {
						nomina = (Nomina) o;
//						logger.info(nomina.getVersion()+" "+nomina.getEmisor().getRfcPatronOrigen());
					}
				}
			}
			if (nomina != null) {
				this.isNomina = true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return nomina;
	}

	private void addPago(Cfdis cfdi) {
		int i = 0;
		int id = 0;
		Pago pago = null;
		try {
			List<Complemento> complementos = comprobante.getComplemento();
			for (i = 0; i < complementos.size(); i++) {
				List<Object> list = complementos.get(i).getAny();
				for (Iterator<Object> itr = list.iterator(); itr.hasNext();) {
					Object o = itr.next();
					if (o instanceof Element) {
						Element el = (Element) o;
						for (int j = 0; j < el.getChildNodes().getLength(); j++) {
							if (el.getChildNodes().item(j).getNodeName().toString().toLowerCase().contains("pago")) {
								Node n = el.getChildNodes().item(j).getParentNode();
								addPago(cfdi, n);
							}
						}
					}
					if (o instanceof Pago) {
						pago = (Pago) o;
						Pagos p = new Pagos();
						p.setCfdi(cfdi);
						p.setId(0);
						p.setFecha(Utils.getCalendar(pago.getFechaPago()));
						p.setFormaPago(Utils.stringToInt(pago.getFormaDePagoP()));
						p.setMoneda(pago.getMonedaP().value());
						p.setMonto(pago.getMonto());
						id = dao.save(p);
						dao.commit();
						p.setId(id);
						if (id > 0) {
							List<DoctoRelacionado> docs = pago.getDoctoRelacionado();
							for (DoctoRelacionado d : docs) {
								PagosDocsRelacionados pdr = new PagosDocsRelacionados();
								pdr.setId(0);
								pdr.setUuidRelacionado(d.getIdDocumento());
								pdr.setMoneda(d.getMonedaDR().value());
								pdr.setMetodoPago(d.getMetodoDePagoDR().value());
								pdr.setParcialidad(d.getNumParcialidad());
								pdr.setSaldoAnterior(d.getImpSaldoAnt());
								pdr.setImportePagado(d.getImpPagado());
								pdr.setSaldoInsoluto(d.getImpSaldoInsoluto());
								dao.save(pdr);
							}
							dao.commit();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private Pagos addPago(Cfdis cfdi, Node node) {
		int id = 0;
		Pagos p = new Pagos();
		try {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				NodeList nodeList = e.getChildNodes();
				if (nodeList.getLength() > 0) {
					for (int k = 0; k < nodeList.getLength(); k++) {
						Node child = nodeList.item(k);
						if (child.getNodeType() == Node.ELEMENT_NODE) {
							NamedNodeMap attributes = child.getAttributes();
							p.setId(0);
							p.setCfdi(cfdi);
							for (int l = 0; l < attributes.getLength(); l++) {
								Node a = attributes.item(l);
								if (a.getNodeName().toLowerCase().equalsIgnoreCase("FechaPago")) {
									p.setFecha(Utils.stringToCalendar(a.getNodeValue()));
								} else if (a.getNodeName().toLowerCase().equalsIgnoreCase("FormaDePagoP")) {
									p.setFormaPago(Utils.stringToInt(a.getNodeValue()));
								} else if (a.getNodeName().toLowerCase().equalsIgnoreCase("MonedaP")) {
									p.setMoneda(a.getNodeValue());
								} else if (a.getNodeName().toLowerCase().equalsIgnoreCase("Monto")) {
									p.setMonto(Utils.stringToBigDecimal(a.getNodeValue()));
								}
							}
							id = dao.save(p);
							dao.commit();
							p.setId(id);
							logger.info(p);
							NodeList docs = child.getChildNodes();
							if (docs.getLength() > 0) {
								for (int m = 0; m < docs.getLength(); m++) {
									if (docs.item(m).getNodeType() == Node.ELEMENT_NODE) {
										addPagoDocsRelacionados(p, docs.item(m));
									}
								}
							}
						}

					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return p;
	}

	private void addPagoDocsRelacionados(Pagos p, Node n) {
		PagosDocsRelacionados doc = null;
		try {
			doc = new PagosDocsRelacionados();
			doc.setId(0);
			doc.setPagos(p);
			NamedNodeMap attributes = n.getAttributes();
			for (int j = 0; j < attributes.getLength(); j++) {
				Node a = attributes.item(j);
				if (a.getNodeName().toString().equalsIgnoreCase("IdDocumento")) {
					doc.setUuidRelacionado(a.getNodeValue());
				} else if (a.getNodeName().toString().equalsIgnoreCase("ImpPagado")) {
					doc.setImportePagado(Utils.stringToBigDecimal(a.getNodeValue()));
				} else if (a.getNodeName().toString().equalsIgnoreCase("ImpSaldoAnt")) {
					doc.setSaldoAnterior(Utils.stringToBigDecimal(a.getNodeValue()));
				} else if (a.getNodeName().toString().equalsIgnoreCase("ImpSaldoInsoluto")) {
					doc.setSaldoInsoluto(Utils.stringToBigDecimal(a.getNodeValue()));
				} else if (a.getNodeName().toString().equalsIgnoreCase("MetodoDePagoDR")) {
					doc.setMetodoPago(a.getNodeValue());
				} else if (a.getNodeName().toString().equalsIgnoreCase("MonedaDR")) {
					doc.setMoneda(a.getNodeValue());
				} else if (a.getNodeName().toString().equalsIgnoreCase("NumParcialidad")) {
					doc.setParcialidad(new BigInteger(a.getNodeValue()));
				} else if (a.getNodeName().toString().equalsIgnoreCase("folio")) {
				} else if (a.getNodeName().toString().equalsIgnoreCase("Serie")) {
				}
			}
			int id = dao.save(doc);
			dao.commit();
			doc.setId(id);
			logger.info(doc);
		} catch (Exception e) {
			logger.error(p.getCfdi().getUuid() + " " + doc.toString(), e);
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("version:" + comprobante.getVersion());
		sb.append("\nsello:" + comprobante.getSello());
		sb.append("\ncertificado:" + comprobante.getCertificado());

		sb.append("\nlugarExpedicion:" + comprobante.getLugarExpedicion());

		sb.append("\nfecha:" + comprobante.getFecha().toXMLFormat());
		sb.append("\ntipoComprobante:" + comprobante.getTipoDeComprobante().toString());
		sb.append("\nuuid:" + getTimbreFiscalDigital(comprobante).getUUID());
		sb.append("\nserie:" + comprobante.getSerie());
		sb.append("\nfolio:" + comprobante.getFolio());

		sb.append("\nEmisor:");
		sb.append("\n\trfc:" + comprobante.getEmisor().getRfc());
		sb.append("\n\tnombre:" + comprobante.getEmisor().getNombre());
		sb.append("\n\tregimenFiscal:" + comprobante.getEmisor().getRegimenFiscal());

		sb.append("\nReceptor:");
		sb.append("\n\trfc:" + comprobante.getReceptor().getRfc());
		sb.append("\n\tnombre:" + comprobante.getReceptor().getNombre());
		sb.append("\n\tresidenciaFiscal:" + comprobante.getReceptor().getResidenciaFiscal());
		sb.append("\n\tnumRegIdTrib:" + comprobante.getReceptor().getNumRegIdTrib());
		sb.append("\n\tusoCFDI:" + comprobante.getReceptor().getUsoCFDI().value());

		sb.append("\nmetodoPago:" + comprobante.getMetodoPago());
		sb.append("\nformaPago:" + comprobante.getFormaPago());
		sb.append("\nmoneda:" + comprobante.getMoneda());
		sb.append("\ntipoCambio:" + comprobante.getTipoCambio());

		sb.append("\nsubTotal:" + comprobante.getSubTotal());
		sb.append("\ndescuento:" + comprobante.getDescuento());
		if (!isNomina) {
			sb.append("\nimpuestos:");
			sb.append("\n\tretenidos:" + comprobante.getImpuestos().getTotalImpuestosRetenidos());
			sb.append("\n\ttrasladados:" + comprobante.getImpuestos().getTotalImpuestosTrasladados());
		}
		sb.append("\ntotal:" + comprobante.getTotal());
		sb.append(conceptosToString());
		if (isNomina) {
			sb.append("\nnomina:");
			sb.append("\n\tcurp:" + nomina.getReceptor().getCurp());
			sb.append("\n\tantiguedad:" + nomina.getReceptor().getAntigüedad());
			sb.append("\n\tbanco:" + nomina.getReceptor().getBanco());
			sb.append("\n\tnumEmpleado:" + nomina.getReceptor().getNumEmpleado());
			sb.append("\n\tNSS:" + nomina.getReceptor().getNumSeguridadSocial());
			sb.append("\n\tpago:" + nomina.getReceptor().getPeriodicidadPago());
			sb.append("\n\tpuesto:" + nomina.getReceptor().getPuesto());
			sb.append("\n\ttipoJornada:" + nomina.getReceptor().getTipoJornada());
			sb.append("\n\ttipoRegimen:" + nomina.getReceptor().getTipoRegimen());
			sb.append("\n\tdiasPagados:" + nomina.getNumDiasPagados());
			sb.append("\n\ttipoNomina:" + nomina.getTipoNomina());

			sb.append("\n\ttotalPercepciones:" + nomina.getTotalPercepciones());
			sb.append("\n\ttotalOtrosPagos:" + nomina.getTotalOtrosPagos());
			sb.append("\n\ttotalDeducciones:" + nomina.getTotalDeducciones());

			sb.append("\n\ttotalSueldo:" + nomina.getPercepciones().getTotalSueldos());
			sb.append("\n\ttotalGravado:" + nomina.getPercepciones().getTotalGravado());
			sb.append("\n\ttotalExento:" + nomina.getPercepciones().getTotalExento());

		}
		return sb.toString();
	}

	public String conceptosToString() {
		StringBuffer sb = new StringBuffer();
		try {
			List<Concepto> conceptos = comprobante.getConceptos().getConcepto();
			sb.append("\nconceptos:\n\t");
			sb.append(StringPadding.right("claveProdServ", '-', 15));
			sb.append(StringPadding.right("noIdentificacion", '-', 18));
			sb.append(StringPadding.right("cantidad", '-', 18));
			sb.append(StringPadding.right("unidad", '-', 8));
			sb.append(StringPadding.right("descripcion", '-', 40));
			sb.append(StringPadding.right("valorUnitario", '-', 18));
			sb.append(StringPadding.right("descuento", '-', 18));
//			sb.append(StringPadding.right("impuesto", '-', 18));
			sb.append(StringPadding.right("importe", '-', 18));
			for (Concepto concepto : conceptos) {
				sb.append("\n\t");
				sb.append(StringPadding.right(concepto.getClaveProdServ(), ' ', 15));
				sb.append(StringPadding.right(concepto.getNoIdentificacion(), ' ', 18));
				sb.append(StringPadding.right(String.valueOf(concepto.getCantidad()), ' ', 18));
				sb.append(StringPadding.right(concepto.getUnidad(), ' ', 8));
				sb.append(StringPadding.right(concepto.getDescripcion(), ' ', 40));
				sb.append(StringPadding.left(String.valueOf(concepto.getValorUnitario()), ' ', 18));
				sb.append(StringPadding.left(String.valueOf(concepto.getDescuento()), ' ', 18));
//				sb.append(StringPadding.left(String.valueOf(concepto.getImpuestos().getTraslados()), ' ', 18));
				sb.append(StringPadding.left(String.valueOf(concepto.getImporte()), ' ', 18));
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return sb.toString();
	}

}
