/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bconnect.common.web.struts.flow;

/**
 *
 * @author floshton
 */
public interface WebFlowManagerInterface {

    public String getFwdSuccess();

    public String getFwdError();

    public void moveNext();
}
