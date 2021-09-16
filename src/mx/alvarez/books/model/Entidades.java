package mx.alvarez.books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import mx.alvarez.commons.Constants;

@Entity
@Table(name = "entidades", schema = Constants.SCHEMA)
@NamedQueries({ @NamedQuery(name = "entidades.all", query = "select e from Entidades e order by e.rfc") })
public class Entidades {

	@Id
	@Column(name = "ent_id")
	private Integer id;

	@Column(name = "ent_rfc")
	private String rfc;

	@Column(name = "ent_razon_social")
	private String razonSocial;

	public Entidades() {
		super();
	}

	public Entidades(Integer id, String rfc, String razonSocial) {
		super();
		this.id = id;
		this.rfc = rfc;
		this.razonSocial = razonSocial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Override
	public String toString() {
		return "Entidades [id=" + id + ", rfc=" + rfc + ", razonSocial=" + razonSocial + "]";
	}

}
