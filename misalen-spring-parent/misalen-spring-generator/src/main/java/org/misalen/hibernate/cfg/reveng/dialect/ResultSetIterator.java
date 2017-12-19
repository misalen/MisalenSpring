package org.misalen.hibernate.cfg.reveng.dialect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Iterator over a resultset; intended usage only for metadata reading.
 */
public abstract class ResultSetIterator implements Iterator<Map<String, Object>> {

	private ResultSet rs;

	protected boolean current = false;

	protected boolean endOfRows = false;

	protected ResultSetIterator(ResultSet resultset) {
		this.rs = resultset;
	}

	public boolean hasNext() {
		try {
			advance();
			return !endOfRows;
		} catch (SQLException e) {
			handleSQLException(e);
			return false;
		}
	}

	public Map<String, Object> next() {
		try {
			advance();
			if (endOfRows) {
				throw new NoSuchElementException();
			}
			current = false;
			return convertRow(rs);
		} catch (SQLException e) {
			handleSQLException(e);
			throw new NoSuchElementException("excpetion occurred " + e);
		}

	}

	abstract protected Throwable handleSQLException(SQLException e);

	abstract protected Map<String, Object> convertRow(ResultSet rs) throws SQLException;

	public void remove() {
		throw new UnsupportedOperationException("remove() not possible on ResultSet");
	}

	protected void advance() throws SQLException {
		if (!current && !endOfRows) {
			if (rs.next()) {
				current = true;
				endOfRows = false;
			} else {
				current = false;
				endOfRows = true;
			}
		}
	}

	public void close() {
		try {
			rs.close();
		} catch (SQLException e) {
			handleSQLException(e);
		}
	}
}
