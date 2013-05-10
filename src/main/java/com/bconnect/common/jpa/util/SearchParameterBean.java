package com.bconnect.common.jpa.util;

/**
 *
 * @author Jorge Rodriguez
 */
public class SearchParameterBean {

    private Object object;
    private String queryClause;
    private String parameterName;

    public SearchParameterBean(Object object, String queryClause) {
        this.object = object;
        this.queryClause = queryClause;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getQueryClause() {
        return queryClause;
    }

    public void setQueryClause(String queryClause) {
        this.queryClause = queryClause;
    }
}
