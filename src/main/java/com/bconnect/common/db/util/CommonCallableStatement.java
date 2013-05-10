package com.bconnect.common.db.util;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author jorge.rodriguez
 * Esta clase porporciona varias utilidades. Encapsula un CallableStatement para 
 * ejecutar tareas comunes de validación antes de hacer el set de valores. Cuando 
 * es necesario, los metodos hacen un setNull del tipo correspondiente.
 */
public class CommonCallableStatement {

    CallableStatement cStmt;

    public CommonCallableStatement(CallableStatement cStmt) {
        this.cStmt = cStmt;
    }

    public CallableStatement getCallableStatement() {
        return cStmt;
    }

    public String getString(int index) throws SQLException {
        return cStmt.getString(index);
    }

    public String getString(String paramName) throws SQLException {
        return cStmt.getString(paramName);
    }

    public int getInt(int index) throws SQLException {
        return cStmt.getInt(index);
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
            cStmt.setString(index, parameter.trim());
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
        if (parameter == null) {
            this.setNullString(index);
        } else {
            cStmt.setString(index, parameter.trim().toUpperCase());
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
            cStmt.setString(index, parameter.trim().toLowerCase());
        }
    }

    public void setBit(int index, boolean parameter) throws SQLException {
        String stringValue = null;
        if (parameter) {
            stringValue = CommonConstants.DB_BIT_TRUE;
        } else {
            stringValue = CommonConstants.DB_BIT_FALSE;
        }
        cStmt.setString(index, stringValue);
    }

    public void setBoolean(int index, boolean parameter) throws SQLException {
        cStmt.setBoolean(index, parameter);
    }

    public void setInt(int index, int parameter) throws SQLException {
        cStmt.setInt(index, parameter);
    }

    public void setIntNotZero(int index, int parameter) throws SQLException {
        if (parameter == 0) {
            this.setNullInt(index);
        } else {
            cStmt.setInt(index, parameter);
        }
    }

    public void setLong(int index, long parameter) throws SQLException {
        cStmt.setLong(index, parameter);
    }

    public void setDouble(int index, double parameter) throws SQLException {
        cStmt.setDouble(index, parameter);
    }

    public void setDate(int index, Date parameter) throws SQLException {
        if (parameter == null) {
            this.setNullDate(index);
        } else {
            String stringDate = DateUtil.formatDate(parameter,
                    CommonConstants.FECHA_FORMATO_YYYYMMDD);
            cStmt.setString(index, stringDate);
        }
    }

    public void setDateTime(int index, Date parameter) throws SQLException {
        if (parameter == null) {
            this.setNullDate(index);
        } else {
            String stringDateTime = DateUtil.formatDate(parameter,
                    CommonConstants.FECHA_FORMATO_YYYYMMDD_HH_MM_SS);
            cStmt.setString(index, stringDateTime);
        }
    }

    public void setDecimal(int index, float parameter) throws SQLException {
        cStmt.setFloat(index, parameter);
    }

    public void setNullString(int index) throws SQLException {
        cStmt.setNull(index, java.sql.Types.VARCHAR);
    }

    public void setNullInt(int index) throws SQLException {
        cStmt.setNull(index, java.sql.Types.INTEGER);
    }

    public void setNullLong(int index) throws SQLException {
        cStmt.setNull(index, java.sql.Types.INTEGER);
    }

    public void setNullDouble(int index) throws SQLException {
        cStmt.setNull(index, java.sql.Types.DOUBLE);
    }

    public void setNullDate(int index) throws SQLException {
        cStmt.setNull(index, java.sql.Types.DATE);
    }

    public void setNull(int index, int type) throws SQLException {
        cStmt.setNull(index, type);
    }

    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        cStmt.registerOutParameter(parameterName, sqlType);
    }

    public void registerOutParameter(int index, int sqlType) throws SQLException {
        cStmt.registerOutParameter(index, sqlType);
    }

    public void registerOutString(String parameterName) throws SQLException {
        cStmt.registerOutParameter(parameterName, java.sql.Types.CHAR);
    }

    public void registerOutString(int index) throws SQLException {
        cStmt.registerOutParameter(index, java.sql.Types.CHAR);
    }

    public void registerOutInt(String parameterName) throws SQLException {
        cStmt.registerOutParameter(parameterName, java.sql.Types.INTEGER);
    }

    public void registerOutInt(int index) throws SQLException {
        cStmt.registerOutParameter(index, java.sql.Types.INTEGER);
    }

    public void registerOutLong(String parameterName) throws SQLException {
        cStmt.registerOutParameter(parameterName, java.sql.Types.INTEGER);
    }

    public void registerOutLong(int index) throws SQLException {
        cStmt.registerOutParameter(index, java.sql.Types.INTEGER);
    }

    public void registerOutDouble(String parameterName) throws SQLException {
        cStmt.registerOutParameter(parameterName, java.sql.Types.DOUBLE);
    }

    public void registerOutDouble(int index) throws SQLException {
        cStmt.registerOutParameter(index, java.sql.Types.DOUBLE);
    }

    public void registerOutDecimal(String parameterName) throws SQLException {
        cStmt.registerOutParameter(parameterName, java.sql.Types.DECIMAL);
    }

    public void registerOutDecimal(int index) throws SQLException {
        cStmt.registerOutParameter(index, java.sql.Types.DECIMAL);
    }

    public void registerOutDate(String parameterName) throws SQLException {
        cStmt.registerOutParameter(parameterName, java.sql.Types.DATE);
    }

    public void registerOutDate(int parameterName) throws SQLException {
        cStmt.registerOutParameter(parameterName, java.sql.Types.DATE);
    }

    public boolean execute() throws SQLException {
        return cStmt.execute();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        return cStmt.executeQuery(sql);
    }

    public CommonResultSet executeCommonQuery(String sql) throws SQLException {
        ResultSet resultSet = cStmt.executeQuery(sql);
        return new CommonResultSet(resultSet);
    }

    public ResultSet executeQuery() throws SQLException {
        return cStmt.executeQuery();
    }

    public CommonResultSet executeCommonQuery() throws SQLException {
        ResultSet resultSet = cStmt.executeQuery();
        return new CommonResultSet(resultSet);
    }

    public int executeUpdate() throws SQLException {
        return cStmt.executeUpdate();
    }

    public int executeUpdate(String sql) throws SQLException {
        return cStmt.executeUpdate(sql);
    }

    public void setObject(int index, Object parameter) throws SQLException {
        cStmt.setObject(index, parameter);
    }

    public Object getObject(int index) throws SQLException {
        return cStmt.getObject(index);
    }
}
