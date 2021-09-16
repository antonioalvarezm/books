package mx.alvarez.books.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.alvarez.commons.Constants;
import mx.alvarez.utils.Utils;

@Entity
@Table(name = "cfdis", schema = Constants.SCHEMA)
public class Cfdis {

	@Id
	@Column(name = "cfdi_id")
	private Integer id;

	@Column(name = "cfdi_emisor")
	private String emisor;

	@Column(name = "cfdi_regimen_fiscal")
	private Integer regimenFiscal;

	@Column(name = "cfdi_receptor")
	private String receptor;

	@Column(name = "cfdi_uso_cfdi")
	private String uso;

	@Column(name = "cfdi_tipo_comprobante")
	private String tipoComprobante;

	@Column(name = "cfdi_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha;

	@Column(name = "cfdi_uuid")
	private String uuid;

	@Column(name = "cfdi_estatus")
	private Integer estatus;

	@Column(name = "cfdi_serie")
	private String serie;

	@Column(name = "cfdi_folio")
	private String folio;

	@Column(name = "cfdi_metodo_pago")
	private String metodoPago;

	@Column(name = "cfdi_forma_pago")
	private Integer formaPago;

	@Column(name = "cfdi_moneda")
	private String moneda;

	@Column(name = "cfdi_subtotal")
	private BigDecimal subtotal;

	@Column(name = "cfdi_descuento")
	private BigDecimal descuento;

	@Column(name = "cfdi_impuestos_retenidos")
	private BigDecimal impuestosRetenidos;

	@Column(name = "cfdi_impuestos_trasladados")
	private BigDecimal impuestosTrasladados;

	@Column(name = "cfdi_total")
	private BigDecimal total;

	@Column(name = "cfdi_tc")
	private BigDecimal tipoCambio;

	public Cfdis() {
		super();
	}

	public Cfdis(Integer id, String emisor, Integer regimenFiscal, String receptor, String uso, String tipoComprobante,
			Calendar fecha, String uuid, Integer estatus, String serie, String folio, String metodoPago,
			Integer formaPago, String moneda, BigDecimal subtotal, BigDecimal descuento, BigDecimal impuestosRetenidos,
			BigDecimal impuestosTrasladados, BigDecimal total, BigDecimal tipoCambio) {
		super();
		this.id = id;
		this.emisor = emisor;
		this.regimenFiscal = regimenFiscal;
		this.receptor = receptor;
		this.uso = uso;
		this.tipoComprobante = tipoComprobante;
		this.fecha = fecha;
		this.uuid = uuid;
		this.estatus = estatus;
		this.serie = serie;
		this.folio = folio;
		this.metodoPago = metodoPago;
		this.formaPago = formaPago;
		this.moneda = moneda;
		this.subtotal = subtotal;
		this.descuento = descuento;
		this.impuestosRetenidos = impuestosRetenidos;
		this.impuestosTrasladados = impuestosTrasladados;
		this.total = total;
		this.tipoCambio = tipoCambio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public Integer getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(Integer regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getImpuestosRetenidos() {
		return impuestosRetenidos;
	}

	public void setImpuestosRetenidos(BigDecimal impuestosRetenidos) {
		this.impuestosRetenidos = impuestosRetenidos;
	}

	public BigDecimal getImpuestosTrasladados() {
		return impuestosTrasladados;
	}

	public void setImpuestosTrasladados(BigDecimal impuestosTrasladados) {
		this.impuestosTrasladados = impuestosTrasladados;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	@Override
	public String toString() {
		return "Cfdis [id=" + id + ", emisor=" + emisor + ", regimenFiscal=" + regimenFiscal + ", receptor=" + receptor
				+ ", uso=" + uso + ", tipoComprobante=" + tipoComprobante + ", fecha=" + Utils.calendarToString(fecha)
				+ ", uuid=" + uuid + ", estatus=" + estatus + ", serie=" + serie + ", folio=" + folio + ", metodoPago="
				+ metodoPago + ", formaPago=" + formaPago + ", moneda=" + moneda + ", subtotal=" + subtotal
				+ ", descuento=" + descuento + ", impuestosRetenidos=" + impuestosRetenidos + ", impuestosTrasladados="
				+ impuestosTrasladados + ", total=" + total + ", tipoCambio=" + tipoCambio + "]";
	}

}
