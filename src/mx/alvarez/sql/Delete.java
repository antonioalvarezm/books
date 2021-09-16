package mx.alvarez.sql;

public class Delete extends Generic {

	public Delete() {
		super();
	}

	public String build() {
		StringBuffer r = new StringBuffer("DELETE FROM ");
		r.append(buildPart(tables, ", "));

		if (!where.isEmpty()) {
			r.append(" WHERE " + buildPart(where, " AND "));
		}

		return r.toString();
	}

	public Generic factory() {
		return new Delete();
	}
}