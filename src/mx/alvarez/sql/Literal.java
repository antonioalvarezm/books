package mx.alvarez.sql;

import java.sql.Date;
import java.util.Calendar;

public class Literal {
	interface quotable {
		String quote(Literal l);
	}

	class StringValue implements quotable {
		public String quote(Literal l) {
			return "'" + l.data.toString().replaceAll("'", "''") + "'";
		}
	}

	class IntValue implements quotable {
		public String quote(Literal l) {
			return l.data.toString();
		}
	}

	class DateValue implements quotable {
		public String quote(Literal l) {
			Date d = (Date) l.data;
			String r = d.toString();
			return "str_to_date('" + r + "', '%Y-%m-%d')";
		}
	}

	class FloatValue implements quotable {
		public String quote(Literal l) {
			return l.data.toString();
		}
	}

	class LiteralValue implements quotable {
		public String quote(Literal l) {
			return l.data.toString();
		}
	}

	class DoubleValue implements quotable {
		public String quote(Literal l) {
			return l.data.toString();
		}
	}

	Object data;
	quotable q;

	@SuppressWarnings("unused")
	private Literal() {
		// Jam�s debe entrar aqu�
	}

	public Literal(String value) {
		data = value;
		q = new StringValue();
	}

	public Literal(int value) {
		data = new Integer(value);
		q = new IntValue();
	}

	public Literal(float value) {
		data = new Float(value);
		q = new FloatValue();
	}

	public Literal(double value) {
		data = new Double(value);
		q = new DoubleValue();
	}

	public Literal(boolean value) {
		data = new Boolean(value);
		q = new LiteralValue();
	}

	public Literal(String value, boolean t) {
		data = value;
		q = new LiteralValue();
	}

	public Literal(Calendar value) {
		data = value;
		q = new DateValue();
	}

	public Literal(Date value) {
		data = value;
		q = new DateValue();
	}

	public String toString() {
		return q.quote(this);
	}

}
