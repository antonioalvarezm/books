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
@Table(name = "cfdis_impuestos", schema = Constants.SCHEMA)
public class CfdisImpuestos {

	@Id
	@Column(name = "cfi_id")
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cfi_id_cfdi", referencedColumnName = "cfdi_id")
	private Cfdis cfdi;

	@Column(name = "cfi_impuesto")
	private Integer impuestoId;

	@Column(name = "cfi_factor")
	private String factor;

	@Column(name = "cfi_tasa_cuota")
	private BigDecimal tasaCuota;

	@Column(name = "cfi_importe")
	private BigDecimal importe;

	public CfdisImpuestos() {
		super();
	}

	public CfdisImpuestos(Integer id, Cfdis cfdi, Integer impuestoId, String factor, BigDecimal tasaCuota,
			BigDecimal importe) {
		super();
		this.id = id;
		this.cfdi = cfdi;
		this.impuestoId = impuestoId;
		this.factor = factor;
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
		return "CfdisImpuestos [id=" + id + ", cfdi=" + cfdi.getId() + ", impuestoId=" + impuestoId + ", factor="
				+ factor + ", tasaCuota=" + tasaCuota + ", importe=" + importe + "]";
	}

}
