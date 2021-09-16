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
@Table(name = "nomina", schema = Constants.SCHEMA)
public class ReciboNomina {

	@Id
	@Column(name = "nom_id")
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nom_id_cfdi", referencedColumnName = "cfdi_id")
	private Cfdis cfdi;

	@Column(name = "nom_patron_rfc")
	private String patronRfc;

	@Column(name = "nom_registro_patronal")
	private String registroPatronal;

	@Column(name = "nom_curp")
	private String curp;

	@Column(name = "nom_nss")
	private String nss;

	@Column(name = "nom_fecha_inicio_laboral")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaInicioLaboral;

	@Column(name = "nom_antiguedad")
	private String antiguedad;

	@Column(name = "nom_tipo_contrato")
	private Integer tipoContrato;

	@Column(name = "nom_tipo_jornada")
	private Integer tipoJornada;

	@Column(name = "nom_tipo_regimen")
	private Integer tipoRegimen;

	@Column(name = "nom_num_empleado")
	private String numEmpleado;

	@Column(name = "nom_departamento")
	private String departamento;

	@Column(name = "nom_puesto")
	private String puesto;

	@Column(name = "nom_riesgo_puesto")
	private Integer riesgoPuesto;

	@Column(name = "nom_periodicidad_pago")
	private Integer periodicidadPago;

	@Column(name = "nom_banco")
	private Integer banco;

	@Column(name = "nom_cuenta_bancaria")
	private String cuentaBancaria;

	@Column(name = "nom_salario_diario_integrado")
	private BigDecimal salarioDiarioIntegrado;

	@Column(name = "nom_tipo_nomina")
	private String tipoNomina;

	@Column(name = "nom_fecha_pago")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaPago;

	@Column(name = "nom_fecha_inicial")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaInicial;

	@Column(name = "nom_fecha_final")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaFinal;

	@Column(name = "nom_num_dias")
	private BigDecimal numDias;

	@Column(name = "nom_total_percepciones")
	private BigDecimal totalPercepciones;

	@Column(name = "nom_total_deducciones")
	private BigDecimal totalDeducciones;

	@Column(name = "nom_total_otros_pagos")
	private BigDecimal totalOtrosPagos;

	@Column(name = "nom_total_sueldos")
	private BigDecimal totalSueldos;

	@Column(name = "nom_total_gravado")
	private BigDecimal totalGravado;

	@Column(name = "nom_total_exento")
	private BigDecimal totalExento;

	@Column(name = "nom_total_otras_deducciones")
	private BigDecimal totalOtrasDeducciones;

	@Column(name = "nom_total_impuestos_retenidos")
	private BigDecimal totalImpuestosRetenidos;

	public ReciboNomina() {
		super();
	}

	public ReciboNomina(Integer id, Cfdis cfdi, String patronRfc, String registroPatronal, String curp, String nss,
			Calendar fechaInicioLaboral, String antiguedad, Integer tipoContrato, Integer tipoJornada,
			Integer tipoRegimen, String numEmpleado, String departamento, String puesto, Integer riesgoPuesto,
			Integer periodicidadPago, Integer banco, String cuentaBancaria, BigDecimal salarioDiarioIntegrado,
			String tipoNomina, Calendar fechaPago, Calendar fechaInicial, Calendar fechaFinal, BigDecimal numDias,
			BigDecimal totalPercepciones, BigDecimal totalDeducciones, BigDecimal totalOtrosPagos,
			BigDecimal totalSueldos, BigDecimal totalGravado, BigDecimal totalExento, BigDecimal totalOtrasDeducciones,
			BigDecimal totalImpuestosRetenidos) {
		super();
		this.id = id;
		this.cfdi = cfdi;
		this.patronRfc = patronRfc;
		this.registroPatronal = registroPatronal;
		this.curp = curp;
		this.nss = nss;
		this.fechaInicioLaboral = fechaInicioLaboral;
		this.antiguedad = antiguedad;
		this.tipoContrato = tipoContrato;
		this.tipoJornada = tipoJornada;
		this.tipoRegimen = tipoRegimen;
		this.numEmpleado = numEmpleado;
		this.departamento = departamento;
		this.puesto = puesto;
		this.riesgoPuesto = riesgoPuesto;
		this.periodicidadPago = periodicidadPago;
		this.banco = banco;
		this.cuentaBancaria = cuentaBancaria;
		this.salarioDiarioIntegrado = salarioDiarioIntegrado;
		this.tipoNomina = tipoNomina;
		this.fechaPago = fechaPago;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.numDias = numDias;
		this.totalPercepciones = totalPercepciones;
		this.totalDeducciones = totalDeducciones;
		this.totalOtrosPagos = totalOtrosPagos;
		this.totalSueldos = totalSueldos;
		this.totalGravado = totalGravado;
		this.totalExento = totalExento;
		this.totalOtrasDeducciones = totalOtrasDeducciones;
		this.totalImpuestosRetenidos = totalImpuestosRetenidos;
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

	public String getPatronRfc() {
		return patronRfc;
	}

	public void setPatronRfc(String patronRfc) {
		this.patronRfc = patronRfc;
	}

	public String getRegistroPatronal() {
		return registroPatronal;
	}

	public void setRegistroPatronal(String registroPatronal) {
		this.registroPatronal = registroPatronal;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Calendar getFechaInicioLaboral() {
		return fechaInicioLaboral;
	}

	public void setFechaInicioLaboral(Calendar fechaInicioLaboral) {
		this.fechaInicioLaboral = fechaInicioLaboral;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Integer getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(Integer tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public Integer getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(Integer tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public Integer getTipoRegimen() {
		return tipoRegimen;
	}

	public void setTipoRegimen(Integer tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}

	public String getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Integer getRiesgoPuesto() {
		return riesgoPuesto;
	}

	public void setRiesgoPuesto(Integer riesgoPuesto) {
		this.riesgoPuesto = riesgoPuesto;
	}

	public Integer getPeriodicidadPago() {
		return periodicidadPago;
	}

	public void setPeriodicidadPago(Integer periodicidadPago) {
		this.periodicidadPago = periodicidadPago;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public BigDecimal getSalarioDiarioIntegrado() {
		return salarioDiarioIntegrado;
	}

	public void setSalarioDiarioIntegrado(BigDecimal salarioDiarioIntegrado) {
		this.salarioDiarioIntegrado = salarioDiarioIntegrado;
	}

	public String getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public Calendar getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Calendar fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Calendar getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Calendar fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Calendar getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Calendar fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public BigDecimal getNumDias() {
		return numDias;
	}

	public void setNumDias(BigDecimal numDias) {
		this.numDias = numDias;
	}

	public BigDecimal getTotalPercepciones() {
		return totalPercepciones;
	}

	public void setTotalPercepciones(BigDecimal totalPercepciones) {
		this.totalPercepciones = totalPercepciones;
	}

	public BigDecimal getTotalDeducciones() {
		return totalDeducciones;
	}

	public void setTotalDeducciones(BigDecimal totalDeducciones) {
		this.totalDeducciones = totalDeducciones;
	}

	public BigDecimal getTotalOtrosPagos() {
		return totalOtrosPagos;
	}

	public void setTotalOtrosPagos(BigDecimal totalOtrosPagos) {
		this.totalOtrosPagos = totalOtrosPagos;
	}

	public BigDecimal getTotalSueldos() {
		return totalSueldos;
	}

	public void setTotalSueldos(BigDecimal totalSueldos) {
		this.totalSueldos = totalSueldos;
	}

	public BigDecimal getTotalGravado() {
		return totalGravado;
	}

	public void setTotalGravado(BigDecimal totalGravado) {
		this.totalGravado = totalGravado;
	}

	public BigDecimal getTotalExento() {
		return totalExento;
	}

	public void setTotalExento(BigDecimal totalExento) {
		this.totalExento = totalExento;
	}

	public BigDecimal getTotalOtrasDeducciones() {
		return totalOtrasDeducciones;
	}

	public void setTotalOtrasDeducciones(BigDecimal totalOtrasDeducciones) {
		this.totalOtrasDeducciones = totalOtrasDeducciones;
	}

	public BigDecimal getTotalImpuestosRetenidos() {
		return totalImpuestosRetenidos;
	}

	public void setTotalImpuestosRetenidos(BigDecimal totalImpuestosRetenidos) {
		this.totalImpuestosRetenidos = totalImpuestosRetenidos;
	}

	@Override
	public String toString() {
		return "ReciboNomina [id=" + id + ", cfdi=" + cfdi.getId() + ", patronRfc=" + patronRfc + ", registroPatronal="
				+ registroPatronal + ", curp=" + curp + ", nss=" + nss + ", fechaInicioLaboral="
				+ Utils.calendarToString(fechaInicioLaboral) + ", antiguedad=" + antiguedad + ", tipoContrato="
				+ tipoContrato + ", tipoJornada=" + tipoJornada + ", tipoRegimen=" + tipoRegimen + ", numEmpleado="
				+ numEmpleado + ", departamento=" + departamento + ", puesto=" + puesto + ", riesgoPuesto="
				+ riesgoPuesto + ", periodicidadPago=" + periodicidadPago + ", banco=" + banco + ", cuentaBancaria="
				+ cuentaBancaria + ", salarioDiarioIntegrado=" + salarioDiarioIntegrado + ", tipoNomina=" + tipoNomina
				+ ", fechaPago=" + Utils.calendarToString(fechaPago) + ", fechaInicial="
				+ Utils.calendarToString(fechaInicial) + ", fechaFinal=" + Utils.calendarToString(fechaFinal)
				+ ", numDias=" + numDias + ", totalPercepciones=" + totalPercepciones + ", totalDeducciones="
				+ totalDeducciones + ", totalOtrosPagos=" + totalOtrosPagos + ", totalSueldos=" + totalSueldos
				+ ", totalGravado=" + totalGravado + ", totalExento=" + totalExento + ", totalOtrasDeducciones="
				+ totalOtrasDeducciones + ", totalImpuestosRetenidos=" + totalImpuestosRetenidos + "]";
	}

}
