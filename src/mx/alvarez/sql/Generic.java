package mx.alvarez.sql;

import java.sql.Date;
import java.util.Iterator;
import java.util.Vector;

public abstract class Generic {

	protected Vector<Object> tables, where, fields, values;

	public Generic() {
		tables = new Vector<Object>();
		where = new Vector<Object>();
		fields = new Vector<Object>();
		values = new Vector<Object>();
	}

	public void clear() {
		tables = new Vector<Object>();
		where = new Vector<Object>();
		fields = new Vector<Object>();
		values = new Vector<Object>();
	}

	public void addTable(String t) {
		tables.add(t);
	}

	public void addWhere(String t) {
		where.add(t);
	}

	public void addWhereEq(String t, String operador, String v) {
		where.add(t + operador + new Literal(v));
	}

	public void addWhereNotEq(String t, String v) {
		where.add(t + "!=" + new Literal(v));
	}

	public void addWhereEq(String t, String v) {
		where.add(t + "=" + new Literal(v));
	}

	public void addWhereEq(String t, int v) {
		where.add(t + "=" + new Literal(v));
	}

	public void addWhereEq(String t, float v) {
		where.add(t + "=" + new Literal(v));
	}

	public void addWhereEq(String t, double v) {
		where.add(t + "=" + new Literal(v));
	}

	public void addWhereEq(String t, Literal v) {
		where.add(t + "=" + v.toString());
	}

	public void addWhereLike(String t, String v) {
		where.add(t + " LIKE " + new Literal(v));
	}

	public void addWhereLikeField(String t, String v) {
		where.add(t + " LIKE " + v);
	}

	public void addJoin(String t, String t2) {
		where.add(t + "=" + t2);
	}

	public void addBetween(String col, String t, String t2) {
		where.add(col + " BETWEEN " + t + " AND " + t2);
	}

	public void addField(String t, boolean b) {
		fields.add(new Literal(t, b));
	}

	public void addField(String t) {
		fields.add(t);
	}

	public void addFieldSubQry(String t, String As) {
		fields.add("(" + t + ") " + As);
	}

	public void addFieldValues(String f, String v) {
		fields.add(f);
		values.add(new Literal(v));
	}

	public void addFieldValues(String f, int v) {
		fields.add(f);
		values.add(new Literal(v));
	}

	public void addFieldValues(String f, float v) {
		fields.add(f);
		values.add(new Literal(v));
	}

	public void addFieldValues(String f, boolean b) {
		fields.add(f);
		values.add(new Literal(b));
	}

	public void addFieldValues(String f, String v, boolean b) {
		fields.add(f);
		values.add(new Literal(v, b));
	}

	public void addFieldValues(String f, Literal l) {
		fields.add(f);
		values.add(l);
	}

	public void addFieldValues(String f, Date d) {
		fields.add(f);
		values.add(new Literal(d));
	}

	public void addFieldValues(String f, double d) {
		fields.add(f);
		values.add(new Literal(d));
	}

	protected String buildPart(Vector<Object> v, String sep) {
		StringBuffer r = new StringBuffer();
		if (v.isEmpty())
			return r.toString();

		for (Iterator<Object> i = v.iterator(); i.hasNext();) {
			r.append(i.next().toString() + sep);
		}
		return r.substring(0, r.length() - sep.length());
	}

	public boolean equals(Object o) {
		Generic q = (Generic) o;
		if (tables.equals(q.tables) && where.equals(q.where)
				&& fields.equals(q.fields) && values.equals(q.values)) {
			return true;
		}
		return false;
	}

	public abstract String build();

	public abstract Generic factory();
}