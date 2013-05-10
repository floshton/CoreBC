package com.bconnect.common.web.struts.flow.util;

/**
 *
 * @author floshton
 */
public class Attribute {

    protected String name;
    protected String scope;
    protected Class type;

    public Attribute() {
    }

    public Attribute(String name, String scope) {
        this.name = name;
        this.scope = scope;
    }

    public Attribute(String name, String scope, Class type) {
        this.name = name;
        this.scope = scope;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
