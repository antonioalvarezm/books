package mx.alvarez.sql;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Select extends Generic {

	private Vector<Object> group, extras, order, pivot;
	private HashMap<Object, Object> whereIn;

	public Select() {
		super();
		extras = new Vector<Object>();
		pivot = new Vector<Object>();
		group = new Vector<Object>();
		order = new Vector<Object>();
		whereIn = new HashMap<Object, Object>();
	}

	public void clear() {
		super.clear();
		extras = new Vector<Object>();
		pivot = new Vector<Object>();
		group = new Vector<Object>();
		order = new Vector<Object>();
		whereIn = new HashMap<Object, Object>();
	}

	public String build() {
		StringBuffer r = new StringBuffer();
		r.append("SELECT " + buildPart(fields, ", "));
		r.append(" FROM " + buildPart(tables, ", "));
		if (!pivot.isEmpty()) {
			r.append(" " + buildPart(pivot, " "));
		}
		if (!where.isEmpty() || !whereIn.isEmpty()) {
			r.append(" WHERE " + buildPart(where, " AND "));
			if (!whereIn.isEmpty()) {
				if (!where.isEmpty()) {
					r.append(" AND ");
				}
				r.append(buildPartWhereIn());
			}
		}
		if (!group.isEmpty()) {
			r.append(" GROUP BY " + buildPart(group, ", "));
		}
		if (!order.isEmpty()) {
			r.append(" ORDER BY " + buildPart(order, ", "));
		}
		if (!extras.isEmpty()) {
			r.append(" " + buildPart(extras, " "));
		}
		return r.toString();
	}

	public void addPivot(String t) {
		pivot.add(t);
	}

	public void addExtra(String t) {
		extras.add(t);
	}

	public void addGroup(String t) {
		group.add(t);
	}

	public void addOrder(String t) {
		order.add(t);
	}

	public void addWhereIn(String key, String value) {
		addWhereInWrapped(key, new Literal(value));
	}

	public void addWhereIn(String key, int value) {
		addWhereInWrapped(key, new Literal(value));
	}

	@SuppressWarnings("unchecked")
	void addWhereInWrapped(String key, Object o) {
		Vector<Object> vec;
		if (whereIn.containsKey(key)) {
			vec = (Vector<Object>) whereIn.get(key);
			vec.add(o);
		} else {
			vec = new Vector<Object>();
			vec.add(o);
			whereIn.put(key, vec);
		}

	}

	@SuppressWarnings("unchecked")
	private String buildPartWhereIn() {
		StringBuffer r = new StringBuffer();

		if (whereIn.isEmpty()) {
			return r.toString();
		}
		for (Iterator<Object> i = whereIn.keySet().iterator(); i.hasNext();) {
			Object o = i.next();
			r.append((String) o + " IN ("
					+ buildPart((Vector<Object>) whereIn.get(o), ", ")
					+ ") AND ");
		}
		return r.substring(0, r.length() - 5);
	}

	public Generic factory() {
		return new Select();
	}
}
