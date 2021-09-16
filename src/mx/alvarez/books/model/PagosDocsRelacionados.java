package mx.alvarez.books.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.alvarez.commons.Constants;

@Entity
@Table(name = "pagos_docs_relacionados", schema = Constants.SCHEMA)
public class PagosDocsRelacionados {

	@Id
	@Column(name = "pdr_id")
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pdr_id_pago", referencedColumnName = "pag_id")
	private Pagos pagos;

	@Column(name = "pdr_uuid_relacionado")
	private String uuidRelacionado;

	@Column(name = "pdr_moneda")
	private String moneda;

	@Column(name = "pdr_metodo_pago")
	private String metodoPago;

	@Column(name = "pdr_parcialidad")
	private BigInteger parcialidad;

	@Column(name = "pdr_saldo_anterior")
	private BigDecimal saldoAnterior;

	@Column(name = "pdr_importe_pagado")
	private BigDecimal importePagado;

	@Column(name = "pdr_saldo_insoluto")
	private BigDecimal saldoInsoluto;

	public PagosDocsRelacionados() {
		super();
	}

	public PagosDocsRelacionados(Integer id, Pagos pagos, String uuidRelacionado, String moneda, String metodoPago,
			BigInteger parcialidad, BigDecimal saldoAnterior, BigDecimal importePagado, BigDecimal saldoInsoluto) {
		super();
		this.id = id;
		this.pagos = pagos;
		this.uuidRelacionado = uuidRelacionado;
		this.moneda = moneda;
		this.metodoPago = metodoPago;
		this.parcialidad = parcialidad;
		this.saldoAnterior = saldoAnterior;
		this.importePagado = importePagado;
		this.saldoInsoluto = saldoInsoluto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pagos getPagos() {
		return pagos;
	}

	public void setPagos(Pagos pagos) {
		this.pagos = pagos;
	}

	public String getUuidRelacionado() {
		return uuidRelacionado;
	}

	public void setUuidRelacionado(String uuidRelacionado) {
		this.uuidRelacionado = uuidRelacionado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public BigInteger getParcialidad() {
		return parcialidad;
	}

	public void setParcialidad(BigInteger parcialidad) {
		this.parcialidad = parcialidad;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public BigDecimal getImportePagado() {
		return importePagado;
	}

	public void setImportePagado(BigDecimal importePagado) {
		this.importePagado = importePagado;
	}

	public BigDecimal getSaldoInsoluto() {
		return saldoInsoluto;
	}

	public void setSaldoInsoluto(BigDecimal saldoInsoluto) {
		this.saldoInsoluto = saldoInsoluto;
	}

	@Override
	public String toString() {
		return "PagosDocsRelacionados [id=" + id + ", pagos=" + pagos.getId() + ", uuidRelacionado=" + uuidRelacionado
				+ ", moneda=" + moneda + ", metodoPago=" + metodoPago + ", parcialidad=" + parcialidad
				+ ", saldoAnterior=" + saldoAnterior + ", importePagado=" + importePagado + ", saldoInsoluto="
				+ saldoInsoluto + "]";
	}

}
