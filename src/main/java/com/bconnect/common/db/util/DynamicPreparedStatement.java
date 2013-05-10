package com.bconnect.common.db.util;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class DynamicPreparedStatement {

    StringBuffer query;
    List parameters;
    boolean hasClosingClause;
    String closingClause;

    public DynamicPreparedStatement(String baseQuery) {
        query = new StringBuffer(baseQuery);
        parameters = new ArrayList(0);
    }

    public void addParameter(Object parameter, String clause) {
        if (CommonUtils.hasValue(parameter)) {
            if (parameter instanceof String[]) {
                String[] theParameters = (String[]) parameter;

                for (int i = 0; i < theParameters.length; i++) {
                    parameters.add(theParameters[i]);
                }
                query.append(CommonConstants.SPACE_STRING + clause);
            } else {
                query.append(CommonConstants.SPACE_STRING + clause);
                parameters.add(parameter);
            }
        }
    }

    public void addLikeParameter(Object parameter, String clause) {
        if (CommonUtils.hasValue(parameter)) {
            if (parameter instanceof String[]) {
                String[] theParameters = (String[]) parameter;

                for (int i = 0; i < theParameters.length; i++) {
                    parameters.add(theParameters[i]);
                }
            } else {
                query.append(CommonConstants.SPACE_STRING + clause);
                if (!((String) parameter).contains("%")
                        || ((String) parameter).contains("%") && ((String) parameter).length() > 1) {
                    parameters.add(parameter);
                }
                query.append(CommonConstants.SPACE_STRING + clause);
            }
        }
    }

    public String getQuery() {
        return query.toString();
    }

    public void setQuery(String query) {
        this.query = new StringBuffer(query);
    }

    public List getParameters() {
        return parameters;
    }

    public void setParameters(List parameters) {
        this.parameters = parameters;
    }

    public void setClosingClause(String closingClause) {
        if (CommonUtils.hasValue(closingClause)) {
            this.hasClosingClause = true;
            this.closingClause = closingClause;
            this.query.append(" " + closingClause);
        }
    }
}
