package com.bconnect.common.web.struts.flow;

/**
 *
 * @author floshton
 */
public class DefaultWebFlowManagerFactory {

    public static WebFlowManagerInterface newInstance(){
        return new DefaultWebFlowManager();
    };

}
