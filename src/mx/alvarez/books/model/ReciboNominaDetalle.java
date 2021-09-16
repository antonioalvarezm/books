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
@Table(name = "nomina_detalle", schema = Constants.SCHEMA)
public class ReciboNominaDetalle {

	@Id
	@Column(name = "ndt_id")
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ndt_id_nomina", referencedColumnName = "nom_id")
	private ReciboNomina reciboNomina;

	@Column(name = "ndt_clase")
	private String clase;

	@Column(name = "ndt_tipo")
	private String tipo;

	@Column(name = "ndt_clave")
	private String clave;

	@Column(name = "ndt_concepto")
	private String concepto;

	@Column(name = "ndt_importe_gravado")
	private BigDecimal importeGravado;

	@Column(name = "ndt_importe_exento")
	private BigDecimal importeExento;

	@Column(name = "ndt_importe")
	private BigDecimal importe;

	public ReciboNominaDetalle() {
		super();
	}

	public ReciboNominaDetalle(Integer id, ReciboNomina reciboNomina, String clase, String tipo, String clave,
			String concepto, BigDecimal importeGravado, BigDecimal importeExento, BigDecimal importe) {
		super();
		this.id = id;
		this.reciboNomina = reciboNomina;
		this.clase = clase;
		this.tipo = tipo;
		this.clave = clave;
		this.concepto = concepto;
		this.importeGravado = importeGravado;
		this.importeExento = importeExento;
		this.importe = importe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReciboNomina getReciboNomina() {
		return reciboNomina;
	}

	public void setReciboNomina(ReciboNomina reciboNomina) {
		this.reciboNomina = reciboNomina;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public BigDecimal getImporteGravado() {
		return importeGravado;
	}

	public void setImporteGravado(BigDecimal importeGravado) {
		this.importeGravado = importeGravado;
	}

	public BigDecimal getImporteExento() {
		return importeExento;
	}

	public void setImporteExento(BigDecimal importeExento) {
		this.importeExento = importeExento;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "ReciboNominaDetalle [id=" + id + ", reciboNomina=" + reciboNomina.getId() + ", clase=" + clase
				+ ", tipo=" + tipo + ", clave=" + clave + ", concepto=" + concepto + ", importeGravado="
				+ importeGravado + ", importeExento=" + importeExento + ", importe=" + importe + "]";
	}

}
