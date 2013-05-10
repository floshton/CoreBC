package com.bconnect.common.util;

import com.bconnect.common.bean.personal.UsuarioBean;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.jsf.ValidationBean;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author floshton
 */
public class JsfUtils {

    private static Logger logger;

    static {
        logger = CommonLogger.getLogger(JsfUtils.class);
    }

    public JsfUtils() {
    }

    public static List getOptions(Collection originalCollection, String valueProperty,
            String labelProperty) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        List options = new ArrayList();

        Iterator itr = originalCollection.iterator();
        while (itr.hasNext()) {
            Object theBean = itr.next();
            String label = BeanUtils.getProperty(theBean, labelProperty);
            String value = BeanUtils.getProperty(theBean, valueProperty);
            options.add(new SelectItem(value, label));
        }
        return options;
    }

    public static List getOptions(List originalList, String valueProperty,
            String labelProperty, boolean useSelectText) {
        List options = new ArrayList();
        if (useSelectText) {
            options.add(new SelectItem("0", "---"));
        }
        try {
            Iterator itr = originalList.iterator();
            while (itr.hasNext()) {
                Object theBean = itr.next();
                String label = BeanUtils.getProperty(theBean, labelProperty);
                String value = BeanUtils.getProperty(theBean, valueProperty);
                options.add(new SelectItem(value, label));
            }
        } catch (Exception e) {
            logger.error("Error al transformar la lista de opciones Jsf", e);
        }

        return options;
    }

    public static List getOptions(List originalList, String valueProperty,
            String labelProperty) {
        return JsfUtils.getOptions(originalList, valueProperty, labelProperty, true);
    }

    public static List getOptions(List originalList, String property) {
        return JsfUtils.getOptions(originalList, property, property);
    }

    public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    public static int getRequestParameterInt(String name) {
        int result;
        try {
            result = Integer.valueOf(JsfUtils.getRequestParameter(name));
        } catch (NumberFormatException nfe) {
            result = 0;
        }
        return result;
    }

    public static Object getSessionMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public static Object removeSessionMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static Object getContextValue(String key) {
        return ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getAttribute(key);
    }

    public static void setContextValue(String key, Object value) {
        ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).setAttribute(key, value);
    }

    public static void removeContextValue(String key) {
        ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).removeAttribute(key);
    }

    public static Object getRequestMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(key);
    }

    public static Object getViewMapValue(String key) {
        return FacesContext.getCurrentInstance().getViewRoot().getAttributes().get(key);
    }

//    public static Object getViewMapValue(String key) {
//        return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
//    }
    public static String getCreadoPor() {
        UsuarioBean usuario = (UsuarioBean) JsfUtils.getSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);
        return usuario != null ? usuario.getLogin() : null;
    }

    public static long getIdCreadoPor() {
        UsuarioBean usuario = (UsuarioBean) JsfUtils.getSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);
        return usuario != null ? usuario.getIdUsuario() : null;
    }

    public static String getExtension() {
        UsuarioBean usuario = (UsuarioBean) JsfUtils.getSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);
        return usuario != null ? usuario.getExtension() : null;
    }

    public static String getUserProfile() {
        UsuarioBean usuario = (UsuarioBean) JsfUtils.getSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);
        return usuario.getCvePerfil();
    }

    public static boolean isUsuarioBconnect() {
        UsuarioBean usuario = (UsuarioBean) JsfUtils.getSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);
        return usuario.getIdEmpresa() == CommonConstants.ID_EMPRESA_BCONNECT;
    }

    public static void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    public static Object getApplicationMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
    }

    public static void setApplicationMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
    }

    public static void addInfoMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary, detail));
    }

    public static void addInfoMessage(String summary) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary, null));
    }

    public static void addWarningMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                summary, detail));
    }

    public static void addErrorMessage(String summary) {
        addErrorMessage(summary, summary);
    }

    public static void addErrorMessage(String summary, String detail) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        facesCtx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                summary, detail));
    }

    public static void addFatalMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                summary, detail));
    }

    public static void addErrorMessageIfEmpty(Object object, String message) {
        if (!CommonUtils.hasValue(object)) {
            addErrorMessage(message, message);
        }
    }

    public static boolean validateObjects(Collection validationBeans) {
        boolean validFlag = true;

        Iterator itr = validationBeans.iterator();
        while (itr.hasNext()) {
            ValidationBean element = (ValidationBean) itr.next();
            if (!CommonUtils.hasValue(element.getObject())) {
                validFlag = false;
                addErrorMessage(element.getValidationMessage(), element.getValidationMessage());
            }
        }

        return validFlag;
    }

    public static SelectItem[] getOptions(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static boolean esSesionEmpleadoBC() {
        UsuarioBean usuario = (UsuarioBean) JsfUtils.getSessionMapValue(CommonConstants.ATRIBUTO_USUARIO);
        return usuario.getIdEmpresa() == 1;
    }

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static SelectItem[] getSelectItemsDate(List<?> entities, boolean selectOne,
            String formatoEtiqueta, String formatoValor) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            Date fecha = (Date) x;
            String etiqueta = DateUtil.formatDate(fecha, formatoEtiqueta);
            String valor = DateUtil.formatDate(fecha, formatoValor);
            items[i++] = new SelectItem(valor, etiqueta);
        }
        return items;
    }

    public static void ensureAddErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtils.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static <T> List<T> arrayToList(T[] arr) {
        if (arr == null) {
            return new ArrayList<T>();
        }
        return Arrays.asList(arr);
    }

    public static <T> Set<T> arrayToSet(T[] arr) {
        if (arr == null) {
            return new HashSet<T>();
        }
        return new HashSet(Arrays.asList(arr));
    }

    public static Object[] collectionToArray(Collection<?> c) {
        if (c == null) {
            return new Object[0];
        }
        return c.toArray();
    }

    public static <T> List<T> collectionToList(Collection c) {
        if (c == null) {
            return new ArrayList<T>();
        }
        return new ArrayList<T>(c);
    }

    public static <T> List<T> setToList(Set<T> set) {
        return new ArrayList<T>(set);
    }

    public static String getAsConvertedString(Object object, Converter converter) {
        return converter.getAsString(FacesContext.getCurrentInstance(), null, object);
    }

    public static String getCollectionAsString(Collection<?> collection) {
        if (collection == null || collection.size() == 0) {
            return "(No Items)";
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Object item : collection) {
            if (i > 0) {
                sb.append("<br />");
            }
            sb.append(item);
            i++;
        }
        return sb.toString();
    }

    public static String redirectResponse(String uri) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
        } catch (Exception e) {
            System.out.println("Error al redirigir al recurso: " + uri);
        }
        return null;
    }
}
