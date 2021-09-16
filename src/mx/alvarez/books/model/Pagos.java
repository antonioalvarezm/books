package mx.alvarez.books.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.alvarez.commons.Constants;
import mx.alvarez.utils.Utils;

@Entity
@Table(name = "pagos", schema = Constants.SCHEMA)
public class Pagos {

	@Id
	@Column(name = "pag_id")
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pag_id_cfdi", referencedColumnName = "cfdi_id")
	private Cfdis cfdi;

	@Column(name = "pag_fecha_pago")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha;

	@Column(name = "pag_id_forma_pago")
	private Integer formaPago;

	@Column(name = "pag_moneda")
	private String moneda;

	@Column(name = "pag_monto")
	private BigDecimal monto;

	public Pagos() {
		super();
	}

	public Pagos(Integer id, Cfdis cfdi, Calendar fecha, Integer formaPago, String moneda, BigDecimal monto) {
		super();
		this.id = id;
		this.cfdi = cfdi;
		this.fecha = fecha;
		this.formaPago = formaPago;
		this.moneda = moneda;
		this.monto = monto;
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

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "Pagos [id=" + id + ", cfdi=" + cfdi.getId() + ", fecha=" + Utils.calendarToString(fecha)
				+ ", formaPago=" + formaPago + ", moneda=" + moneda + ", monto=" + monto + "]";
	}

}
