package com.bconnect.common.db.util;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.CommonUtils;

/**
 *
 * @author Jorge Rodriguez
 */
public class DBUtils {

    public static String buildInClause(String columnName, int numberPlaceholders,
            boolean isWhereClause) {
        return DBUtils.buildInClause(columnName, numberPlaceholders, isWhereClause, null);
    }

    public static String buildInClause(String columnName, int numberPlaceholders,
            boolean isWhereClause, String endingClause) {
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
        clause.append(CommonConstants.SPACE_STRING);
        if (CommonUtils.hasValue(endingClause)) {
            clause.append(endingClause);
        }
        clause.append(CommonConstants.SPACE_STRING);

        return clause.toString();
    }

    public static String buildInClause(String columnName, int numberPlaceholders) {
        return DBUtils.buildInClause(columnName, numberPlaceholders, Boolean.FALSE);
    }
}
