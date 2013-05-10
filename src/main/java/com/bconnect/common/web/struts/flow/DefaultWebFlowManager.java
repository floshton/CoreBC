package com.bconnect.common.web.struts.flow;

import com.bconnect.common.web.struts.flow.util.Attribute;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author floshton
 */
public class DefaultWebFlowManager implements WebFlowManagerInterface {

    protected List<WebFlowStepInterface> steps;
    protected int currentStep;
    protected String name;

    public DefaultWebFlowManager() {
        steps = new ArrayList<WebFlowStepInterface>();
    }

    public String getFwdSuccess() {
        WebFlowStepInterface lastStep = this.steps.get(this.currentStep);
        return lastStep.getFwdSuccess();
    }

    public String getFwdError() {
        WebFlowStepInterface lastStep = this.steps.get(this.currentStep);
        return lastStep.getFwdError();
    }

    public boolean canGoNextStep(HttpServletRequest req) {
        DefaultWebFlowStep lastStep = (DefaultWebFlowStep) this.steps.get(this.currentStep);

        return this.areValidAttributes(lastStep.getAttributes(), req) &&
                this.areValidParameters(lastStep.getParameters(), req);
    }

    public String getNextFwd(HttpServletRequest req) {
        String nextFwd = null;
        if (canGoNextStep(req)) {
            nextFwd = getFwdSuccess();
        } else {
            nextFwd = getFwdSuccess();
        }
        return nextFwd;
    }

    public void moveNext() {
        this.steps.get(this.currentStep).setAccomplished(Boolean.TRUE);
        currentStep++;
    }

    public List getSteps() {
        return steps;
    }

    public void setSteps(List steps) {
        this.steps = steps;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStep(DefaultWebFlowStep step) {
        steps.add(step);
    }

    private boolean areValidParameters(List parameters, HttpServletRequest req) {
        boolean areValid = true;
        Iterator itr = parameters.iterator();
        while (itr.hasNext()) {
            String param = (String) itr.next();
            if (!this.isParameterPresent(param, req)) {
                areValid = false;
                break;
            }
        }
        return areValid;
    }

    private boolean areValidAttributes(List attributes, HttpServletRequest request) {
        boolean areValid = true;
        Iterator itr = attributes.iterator();
        while (itr.hasNext()) {
            Attribute att = (Attribute) itr.next();
            if (!this.isAttributePresent(att, request)) {
                areValid = false;
                break;
            }
        }
        return areValid;
    }

    private boolean isParameterPresent(String param, HttpServletRequest req) {
        boolean isPresent = false;
        Enumeration paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if (param.equals(paramName)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    private boolean isAttributePresent(Attribute att, HttpServletRequest req) {
        String name = att.getName();
        String scope = att.getScope();
        boolean isPresent = false;
        if ("request".equals(scope)) {
            Enumeration attributeNames = req.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = (String) attributeNames.nextElement();
                if (name.equals(attributeName)) {
                    isPresent = true;
                    break;
                }
            }
        } else if ("session".equals(scope)) {
            HttpSession session = req.getSession();
            Enumeration attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = (String) attributeNames.nextElement();
                if (name.equals(attributeName)) {
                    isPresent = true;
                    break;
                }
            }
        } else if ("context".equals(scope)) {
            ServletContext context = req.getSession().getServletContext();
            Enumeration attributeNames = context.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = (String) attributeNames.nextElement();
                if (name.equals(attributeName)) {
                    isPresent = true;
                    break;
                }
            }
        }
        return isPresent;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
