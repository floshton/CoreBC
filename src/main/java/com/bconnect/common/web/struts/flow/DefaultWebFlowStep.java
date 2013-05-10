package com.bconnect.common.web.struts.flow;

import com.bconnect.common.web.struts.flow.util.Attribute;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author floshton
 */
public class DefaultWebFlowStep implements WebFlowStepInterface {

    protected List<Attribute> attributes;
    protected List<String> parameters;
    protected String fwdSuccess;
    protected String fwdError;
    protected boolean accomplished;

    public DefaultWebFlowStep() {
        attributes = new ArrayList<Attribute>();
        parameters = new ArrayList<String>();
        accomplished = false;
    }

    public String getFwdSuccess() {
        return this.fwdSuccess;
    }

    public String getFwdError() {
        return this.fwdError;
    }

    public List getAttributes() {
        return attributes;
    }

    public void setAttributes(List attributes) {
        this.attributes = attributes;
    }

    public List getParameters() {
        return parameters;
    }

    public void setParameters(List parameters) {
        this.parameters = parameters;
    }

    public void setFwdSuccess(String fwdSuccess) {
        this.fwdSuccess = fwdSuccess;
    }

    public void setFwdError(String fwdError) {
        this.fwdError = fwdError;
    }

    public boolean isAccomplished() {
        return accomplished;
    }

    public void setAccomplished(boolean accomplished) {
        this.accomplished = accomplished;
    }

    public void addAttribute(Attribute att) {
        this.attributes.add(att);
    }

    public void addParameter(String param) {
        this.parameters.add(param);
    }
}
