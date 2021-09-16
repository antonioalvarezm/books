package mx.alvarez.books.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.alvarez.commons.Constants;

@Entity
@Table(name = "cfdis_conceptos_impuestos", schema = Constants.SCHEMA)
public class CfdisConceptosImpuestos {

	@Id
	@Column(name = "cci_id")
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cci_id_cfdi", referencedColumnName = "cfdi_id")
	private Cfdis cfdi;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cci_id_concepto", referencedColumnName = "cfc_id")
	private CfdisConceptos concepto;

	@Column(name = "cci_impuesto")
	private Integer impuestoId;

	@Column(name = "cci_factor")
	private String factor;

	@Column(name = "cci_base")
	private BigDecimal base;

	@Column(name = "cci_tasa_cuota")
	private BigDecimal tasaCuota;

	@Column(name = "cci_importe")
	private BigDecimal importe;

	public CfdisConceptosImpuestos() {
		super();
	}

	public CfdisConceptosImpuestos(Integer id, Cfdis cfdi, CfdisConceptos concepto, Integer impuestoId, String factor,
			BigDecimal base, BigDecimal tasaCuota, BigDecimal importe) {
		super();
		this.id = id;
		this.cfdi = cfdi;
		this.concepto = concepto;
		this.impuestoId = impuestoId;
		this.factor = factor;
		this.base = base;
		this.tasaCuota = tasaCuota;
		this.importe = importe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cfdis getCfdi() {
		return cfdi;
	}

	public void setCfdi(Cfdis cfdi) {
		this.cfdi = cfdi;
	}

	public CfdisConceptos getConcepto() {
		return concepto;
	}

	public void setConcepto(CfdisConceptos concepto) {
		this.concepto = concepto;
	}

	public Integer getImpuestoId() {
		return impuestoId;
	}

	public void setImpuestoId(Integer impuestoId) {
		this.impuestoId = impuestoId;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public BigDecimal getTasaCuota() {
		return tasaCuota;
	}

	public void setTasaCuota(BigDecimal tasaCuota) {
		this.tasaCuota = tasaCuota;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "CfdisConceptosImpuestos [id=" + id + ", cfdi=" + cfdi.getId() + ", concepto=" + concepto.getId() + ", impuestoId="
				+ impuestoId + ", factor=" + factor + ", base=" + base + ", tasaCuota=" + tasaCuota + ", importe="
				+ importe + "]";
	}
	
}
