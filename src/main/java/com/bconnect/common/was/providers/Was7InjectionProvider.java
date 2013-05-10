package com.bconnect.common.was.providers;

import java.lang.reflect.Method;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import com.sun.faces.spi.DiscoverableInjectionProvider;
import com.sun.faces.spi.InjectionProviderException;
import com.sun.faces.util.Util;

/**
 *
 * @author jorge.rodriguez
 */
public class Was7InjectionProvider extends DiscoverableInjectionProvider {

    private static final String WEBSPHERE_ANNOTATION_HELPER_MANAGER_CLASS = "com.ibm.wsspi.webcontainer.annotation.AnnotationHelperManager";
    private Object runtimeAnnotationHelper;

    /**
     * invoke com.ibm.wsspi.wetainer.annotation.AnnotationHelperManager.getInstance(ServletContext context);
     * then com.ibm.wsspi.wetainer.annotation.AnnotationHelperManager.getAnnotationHelper()
     */
    public Was7InjectionProvider() {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Class clazz = Util.loadClass(WEBSPHERE_ANNOTATION_HELPER_MANAGER_CLASS, Was7InjectionProvider.class);
            Method mGetInstance = clazz.getMethod("getInstance", new Class[]{ServletContext.class});
            Method mGetAnnotationHelper = clazz.getMethod("getAnnotationHelper", new Class[]{});
            Object annotationHelperManager = mGetInstance.invoke(null, (ServletContext) externalContext.getContext());
            this.runtimeAnnotationHelper = mGetAnnotationHelper.invoke(annotationHelperManager, new Object[]{});
        } catch (Exception e) {
        }
    }

    /**
     * invoke com.ibm.wsspi.wetainer.annotation.AnnotationHelper.inject(Object managedBean)
     */
    @Override
    public void inject(Object managedBean) throws InjectionProviderException {
        try {
            Method mInject = runtimeAnnotationHelper.getClass().getMethod("inject", new Class[]{Object.class});
            mInject.invoke(runtimeAnnotationHelper, managedBean);
        } catch (Exception e) {
            throw new InjectionProviderException(e);
        }
    }

    /**
     * invoke com.ibm.wsspi.wetainer.annotation.AnnotationHelper.doPreDestroy(Object managedBean)
     */
    @Override
    public void invokePreDestroy(Object managedBean) throws InjectionProviderException {
        try {
            Method mInject = runtimeAnnotationHelper.getClass().getMethod("doPreDestroy", new Class[]{Object.class});
            mInject.invoke(runtimeAnnotationHelper, managedBean);
        } catch (Exception e) {
            throw new InjectionProviderException(e);
        }
    }

    /**
     * invoke com.ibm.wsspi.wetainer.annotation.AnnotationHelper.doPostConstruct(Object managedBean)
     */
    @Override
    public void invokePostConstruct(Object managedBean) throws InjectionProviderException {
        try {
            Method mInject = runtimeAnnotationHelper.getClass().getMethod("doPostConstruct", new Class[]{Object.class});
            mInject.invoke(runtimeAnnotationHelper, managedBean);
        } catch (Exception e) {
            throw new InjectionProviderException(e);
        }
    }

    public static boolean isInjectionFeatureAvailable(String delegateClass) {
        try {
            Util.loadClass(delegateClass, null);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}