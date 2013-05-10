package com.bconnect.common.db;

import com.bconnect.common.util.CommonConstants;

/**
 *
 * @author Jorge Rodriguez
 */
public class DBUtils {

    public static CommonDataSource getDefaultCommonDataSource(String dbName,
            String user, String password, String url) {

        CommonDataSource ds = new CommonDataSource(dbName,
                CommonConstants.DB_DRIVER_NAME_INFORMIX,
                user,
                password,
                url,
                CommonConstants.DB_DEFAULT_POOL_SIZE_MIN,
                CommonConstants.DB_DEFAULT_POOL_SIZE_MAX,
                CommonConstants.DB_DEFAULT_ACTIVE_MAX,
                CommonConstants.DB_DEFAULT_IDLE_MAX,
                CommonConstants.DB_DEFAULT_WAIT_MAX);
        return ds;
    }

    public static CommonDataSource getDefaultCommonDataSource(String dbName,
            String user, String password, String url, int minPoolSize, int maxPoolSize) {

        CommonDataSource ds = new CommonDataSource(dbName,
                CommonConstants.DB_DRIVER_NAME_INFORMIX,
                user,
                password,
                url,
                minPoolSize,
                maxPoolSize,
                CommonConstants.DB_DEFAULT_ACTIVE_MAX,
                CommonConstants.DB_DEFAULT_IDLE_MAX,
                CommonConstants.DB_DEFAULT_WAIT_MAX);
        return ds;
    }

    public static String buildInClause(String columnName, int numberPlaceholders,
            boolean isWhereClause) {
        StringBuffer clause = new StringBuffer();
        clause.append(CommonConstants.SPACE_STRING);
        if (isWhereClause) {
            clause.append(CommonConstants.STRING_DB_CLAUSE_WHERE);
        } else {
            clause.append(CommonConstants.STRING_DB_CLAUSE_AND);
        }
        clause.append(CommonConstants.SPACE_STRING);
        clause.append(columnName);
        clause.append(CommonConstants.SPACE_STRING);
        clause.append(" IN ");
        clause.append(CommonConstants.SYMBOL_PARENTHESIS_LEFT);

        for (int i = 0; i < numberPlaceholders; i++) {
            clause.append(CommonConstants.SYMBOL_QUESTION_MARK);
            clause.append(CommonConstants.SYMBOL_COMMA);
        }
        clause.delete(clause.length() - 1, clause.length());
        clause.append(CommonConstants.SYMBOL_PARENTHESIS_RIGHT);

        return clause.toString();
    }

    public static String buildInClause(String columnName, int numberPlaceholders) {
        return DBUtils.buildInClause(columnName, numberPlaceholders, Boolean.FALSE);
    }

    
}
