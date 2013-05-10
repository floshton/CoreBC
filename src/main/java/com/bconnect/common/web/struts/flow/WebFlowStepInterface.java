package com.bconnect.common.web.struts.flow;

/**
 *
 * @author floshton
 */
public interface WebFlowStepInterface {

    public String getFwdSuccess();

    public String getFwdError();

    public boolean isAccomplished();

    public void setAccomplished(boolean accomplished);
}
