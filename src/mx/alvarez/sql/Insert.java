package mx.alvarez.sql;

public class Insert extends Generic {

	public Insert() {
		super();
	}

	public String build() {
		StringBuffer r = new StringBuffer();
		r.append("INSERT INTO " + buildPart(tables, ", "));
		r.append(" (" + buildPart(fields, ", "));
		r.append(") VALUES (" + buildPart(values, ", ") + ")");
		return r.toString();
	}

	public Generic factory() {
		return new Insert();
	}
}
