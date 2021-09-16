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
@Table(name = "cfdis_conceptos", schema = Constants.SCHEMA)
public class CfdisConceptos {

	@Id
	@Column(name = "cfc_id")
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cfc_id_cfdi", referencedColumnName = "cfdi_id")
	private Cfdis cfdi;

	@Column(name = "cfc_clave_prod_serv")
	private Integer claveProductoId;

	@Column(name = "cfc_no_identificacion")
	private String noIdentificacion;

	@Column(name = "cfc_cantidad")
	private BigDecimal cantidad;

	@Column(name = "cfc_unidad")
	private String unidad;

	@Column(name = "cfc_descripcion")
	private String descripcion;

	@Column(name = "cfc_valor_unitario")
	private BigDecimal valorUnitario;

	@Column(name = "cfc_importe")
	private BigDecimal importe;

	public CfdisConceptos() {
		super();
	}

	public CfdisConceptos(Integer id, Cfdis cfdi, Integer claveProductoId, String noIdentificacion, BigDecimal cantidad,
			String unidad, String descripcion, BigDecimal valorUnitario, BigDecimal importe) {
		super();
		this.id = id;
		this.cfdi = cfdi;
		this.claveProductoId = claveProductoId;
		this.noIdentificacion = noIdentificacion;
		this.cantidad = cantidad;
		this.unidad = unidad;
		this.descripcion = descripcion;
		this.valorUnitario = valorUnitario;
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

	public Integer getClaveProductoId() {
		return claveProductoId;
	}

	public void setClaveProductoId(Integer claveProductoId) {
		this.claveProductoId = claveProductoId;
	}

	public String getNoIdentificacion() {
		return noIdentificacion;
	}

	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "CfdisConceptos [id=" + id + ", cfdi=" + cfdi.getId() + ", claveProductoId=" + claveProductoId
				+ ", noIdentificacion=" + noIdentificacion + ", cantidad=" + cantidad + ", unidad=" + unidad
				+ ", descripcion=" + descripcion + ", valorUnitario=" + valorUnitario + ", importe=" + importe + "]";
	}

}
