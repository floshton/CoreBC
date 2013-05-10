package com.bconnect.common.db.util;

import com.bconnect.common.util.CommonConstants;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author floshton
 */
public class CommonResultSet {

    ResultSet rs;
    private final String EMPTY_STRING = "";

    public CommonResultSet(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getResultSet() {
        return rs;
    }

    public void setResultSet(ResultSet rs) {
        this.rs = rs;
    }

    public String getString(int columnIndex) throws SQLException {
        if (rs.getString(columnIndex) != null) {
            return rs.getString(columnIndex).trim();
        } else {
            return EMPTY_STRING;
        }
    }

    public String getString(String columnName) throws SQLException {
        if (rs.getString(columnName) != null) {
            return rs.getString(columnName).trim();
        } else {
            return EMPTY_STRING;
        }
    }

   public boolean getBoolean(String columnName) throws SQLException {
        try {
            boolean valor = rs.getBoolean(columnName);
            return valor;
        } catch (Exception e) {
            if (rs.getString(columnName) == null
                    || rs.getString(columnName).equals(CommonConstants.EMPTY_STRING)
                    || rs.getString(columnName).equals(CommonConstants.DB_BIT_FALSE)) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        }
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        if (rs.getString(columnIndex) == null ||
                rs.getString(columnIndex).equals(CommonConstants.EMPTY_STRING) ||
                rs.getString(columnIndex).equals(CommonConstants.DB_BIT_FALSE)) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public boolean getBoolean2(String columnName) throws SQLException {
        if (rs.getString(columnName) == null ||
                rs.getString(columnName).equals("f")) {
            return Boolean.FALSE;
        } else if(rs.getString(columnName).equals("t")){
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean getBoolean2(int columnIndex) throws SQLException {
        if (rs.getString(columnIndex) == null ||
                rs.getString(columnIndex).equals("f")) {
            return Boolean.FALSE;
        } else if(rs.getString(columnIndex).equals("t")){
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public int getInt(int columnIndex) throws SQLException {
        return rs.getInt(columnIndex);
    }

    public int getInt(String columnName) throws SQLException {
        return rs.getInt(columnName);
    }

    public long getLong(int columnIndex) throws SQLException {
        return rs.getLong(columnIndex);
    }

    public long getLong(String columnName) throws SQLException {
        return rs.getLong(columnName);
    }

    public double getDouble(int columnIndex) throws SQLException {
        return rs.getDouble(columnIndex);
    }

    public double getDouble(String columnName) throws SQLException {
        return rs.getDouble(columnName);
    }

    public float getDecimal(String columnName) throws SQLException {
        return rs.getFloat(columnName);
    }

    public float getDecimal(int columnIndex) throws SQLException {
        return rs.getFloat(columnIndex);
    }

    public java.util.Date getDate(int columnIndex) throws SQLException {
        java.util.Date date = null;
        if (rs.getDate(columnIndex) != null) {
            java.sql.Date object = rs.getDate(columnIndex);
            date = new Date(object.getTime());
        }
        return date;
    }

    public Calendar getCalendar(int columnIndex) throws SQLException {
        Calendar calendar = null;
        java.util.Date date = null;
        if (rs.getDate(columnIndex) != null) {
            java.sql.Date object = rs.getDate(columnIndex);
            date = new Date(object.getTime());

            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    public Calendar getCalendar(String columnName) throws SQLException {
        Calendar calendar = null;
        java.util.Date date = null;
        if (rs.getDate(columnName) != null) {
            java.sql.Date object = rs.getDate(columnName);
            date = new Date(object.getTime());

            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    public java.util.Date getDate(String columnName) throws SQLException {
        java.util.Date date = null;
        if (rs.getDate(columnName) != null) {
            java.sql.Date object = rs.getDate(columnName);
            date = new java.util.Date(object.getTime());
        }

        return date;
    }

    public Statement getCursor() throws SQLException {
        return rs.getStatement();
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return rs.getMetaData();
    }

    public boolean hasColumn(String columnName) throws SQLException {
        boolean hasColumn = false;
        ResultSetMetaData rsmt = rs.getMetaData();
        int rowCount = rsmt.getColumnCount();
        for (int i = 1; i <= rowCount; i++) {
            String column = rsmt.getColumnName(i);
            if (column.equalsIgnoreCase(columnName)) {
                hasColumn = true;
                break;
            }
        }
        return hasColumn;
    }

    public boolean next() throws SQLException {
        return rs.next();
    }

    public java.sql.Array getArray(int columnIndex) throws SQLException {
        return rs.getArray(columnIndex);
    }

    public java.sql.Array getArray(String columnName) throws SQLException {
        return rs.getArray(columnName);
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return rs.getBigDecimal(columnIndex);
    }

    public BigDecimal getBigDecimal(String columnName) throws SQLException {
        return rs.getBigDecimal(columnName);
    }
}
