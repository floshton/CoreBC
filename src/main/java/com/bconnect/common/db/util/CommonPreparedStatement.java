package com.bconnect.common.db.util;

import com.bconnect.common.exception.DBException;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.CommonUtils;
import com.bconnect.common.util.DateUtil;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class CommonPreparedStatement {

    PreparedStatement statement;

    public CommonPreparedStatement(PreparedStatement cStmt) {
        this.statement = cStmt;
    }

    public CommonPreparedStatement() {
    }

    public PreparedStatement getPreparedStatement() {
        return statement;
    }

    /**
     * Este método realiza el set de un String en un CallableStatement. ANtes de 
     * realizarlo, verifica que el String no sea igual a null, pues de ser así,
     * realiza un setNull en ese campo
     * @param index numero de parámetro
     * @param parameter valor del parámetro
     * @throws java.sql.SQLException
     */
    public void setString(int index, String parameter) throws SQLException {
        if (parameter == null) {
            this.setNullString(index);
        } else {
            statement.setString(index, parameter.trim());
        }
    }

    /**
     * Este método realiza el set de un String (en mayúsculas) en un 
     * CallableStatement. Antes de realizarlo, verifica que el String no sea 
     * igual a null, pues de ser así, realiza un setNull en ese campo
     * @param index numero de parámetro
     * @param parameter valor del parámetro
     * @throws java.sql.SQLException
     */
    public void setStringToUpperCase(int index, String parameter) throws SQLException {
        if (!CommonUtils.hasValue(parameter)) {
            this.setNullString(index);
        } else {
            statement.setString(index, parameter.trim().toUpperCase());
        }
    }

    /**
     * Este método realiza el set de un String (en minúsculas) en un 
     * CallableStatement. Antes de realizarlo, verifica que el String no sea 
     * igual a null, pues de ser así, realiza un setNull en ese campo
     * @param index numero de parámetro
     * @param parameter valor del parámetro
     * @throws java.sql.SQLException
     */
    public void setStringToLowerCase(int index, String parameter) throws SQLException {
        if (parameter == null) {
            this.setNullString(index);
        } else {
            statement.setString(index, parameter.trim().toLowerCase());
        }
    }

    public void setParameter(int index, Object parameter) throws SQLException, DBException {
        if (parameter instanceof String) {
            this.setString(index, ((String) parameter));
        } else if (parameter instanceof Date) {
            this.setDate(index, (Date) parameter);
        } else if (parameter instanceof java.util.Date) {
            this.setDate(index, (java.util.Date) parameter);
        } else if (parameter instanceof Integer) {
            this.setInt(index, (Integer) parameter);
        } else if (parameter instanceof Boolean) {
            this.setBoolean(index, (Boolean) parameter);
        } else if (parameter instanceof Float) {
            this.setDecimal(index, (Float) parameter);
        } else {
            throw new DBException("El valor del parámetro no se puede resolver");
        }
    }

    public void setInt(int index, int parameter) throws SQLException {
        statement.setInt(index, parameter);
    }

    public void setDecimal(int index, float parameter) throws SQLException {
        statement.setFloat(index, parameter);
    }

    public void setBoolean(int index, boolean parameter) throws SQLException {
        statement.setBoolean(index, parameter);
    }

    public void setBit(int index, boolean parameter) throws SQLException {
        String stringValue = null;
        if (parameter) {
            stringValue = CommonConstants.DB_BIT_TRUE;
        } else {
            stringValue = CommonConstants.DB_BIT_FALSE;
        }
        statement.setString(index, stringValue);
    }

    public void setLong(int index, long parameter) throws SQLException {
        statement.setLong(index, parameter);
    }

    public void setDouble(int index, double parameter) throws SQLException {
        statement.setDouble(index, parameter);
    }

    public void setDate(int index, Date parameter) throws SQLException {
        if (parameter == null) {
            this.setNullDate(index);
        } else {
            String stringDate = DateUtil.formatDate(parameter,
                    CommonConstants.FECHA_FORMATO_YYYYMMDD);
            statement.setString(index, stringDate);
        }
    }

    public void setDate(int index, java.util.Date parameter) throws SQLException {
        if (parameter == null) {
            this.setNullDate(index);
        } else {
            String stringDate = DateUtil.formatDate(parameter,
                    CommonConstants.FECHA_FORMATO_YYYYMMDD);
            statement.setString(index, stringDate);
        }
    }

    public void setDateTime(int index, java.util.Date parameter) throws SQLException {
        if (parameter == null) {
            this.setNullDate(index);
        } else {
            String stringDateTime = DateUtil.formatDate(parameter,
                    CommonConstants.FECHA_FORMATO_YYYYMMDD_HH_MM_SS);
            statement.setString(index, stringDateTime);
        }
    }

    public void setNullString(int index) throws SQLException {
        statement.setNull(index, java.sql.Types.VARCHAR);
    }

    public void setNullInt(int index) throws SQLException {
        statement.setNull(index, java.sql.Types.INTEGER);
    }

    public void setNullDecimal(int index) throws SQLException {
        statement.setNull(index, java.sql.Types.FLOAT);
    }

    public void setNullLong(int index) throws SQLException {
        statement.setNull(index, java.sql.Types.INTEGER);
    }

    public void setNullDouble(int index) throws SQLException {
        statement.setNull(index, java.sql.Types.DOUBLE);
    }

    public void setNullDate(int index) throws SQLException {
        statement.setNull(index, java.sql.Types.DATE);
    }

    public void setNull(int index, int type) throws SQLException {
        statement.setNull(index, type);
    }

    public boolean execute() throws SQLException {
        return statement.execute();
    }

    public CommonResultSet executeQuery() throws SQLException {
        ResultSet rs = statement.executeQuery();
        return new CommonResultSet(rs);
    }

    public CommonResultSet executeQuery(String query) throws SQLException {
        ResultSet rs = statement.executeQuery(query);
        return new CommonResultSet(rs);
    }

    public CommonResultSet executeQuery(DynamicPreparedStatement dynamicStatement)
            throws SQLException, DBException {
        List parameters = dynamicStatement.getParameters();

        for (int i = 1; i <= parameters.size(); i++) {
            this.setParameter(i, parameters.get(i - 1));
        }

        ResultSet rs = statement.executeQuery();
        return new CommonResultSet(rs);
    }

    public int executeUpdate() throws SQLException {
        return statement.executeUpdate();
    }

    public int executeUpdate(String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setArray(int index, java.sql.Array array) throws SQLException {
        if (array == null ) {
            this.setNull(index, java.sql.Types.ARRAY);
        } else {
            statement.setArray(index, array);
        }
    }

    public void setObject(int index, Object obj) throws SQLException {
        if (obj == null ) {
            this.setNull(index, java.sql.Types.JAVA_OBJECT);
        } else {
            statement.setObject(index, obj);
        }
    }

    public void setObject(int index, Object obj, int type) throws SQLException {
        if (obj == null ) {
            this.setNull(index, type);
        } else {
            statement.setObject(index, obj, type);
        }
    }
}
