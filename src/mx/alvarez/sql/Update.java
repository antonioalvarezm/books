package mx.alvarez.sql;

import java.util.Enumeration;

public class Update extends Generic {

	public Update() {
		super();
	}

	private String build_part_fv() {
		StringBuffer r = new StringBuffer();
		if (fields.isEmpty())
			return r.toString();
		for (Enumeration<Object> e = fields.elements(), f = values.elements(); e
				.hasMoreElements() && f.hasMoreElements();) {
			r.append((String) e.nextElement() + "="
					+ f.nextElement().toString() + ", ");
		}
		return r.substring(0, r.length() - 2);
	}

	public String build() {
		StringBuffer r = new StringBuffer();
		r.append("UPDATE " + buildPart(tables, ", "));
		r.append(" SET " + build_part_fv());
		if (!where.isEmpty()) {
			r.append(" WHERE " + buildPart(where, " and "));
		}
		return r.toString();
	}

	public Generic factory() {
		return new Update();
	}
}