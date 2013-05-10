package com.bconnect.common.util;

import com.bconnect.common.logging.CommonLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Jorge Rodriguez
 */
public class BeanUtils {

    private static Logger logger;

    static {
        logger = CommonLogger.getLogger(BeanUtils.class);
    }

    public static void changePropToUpperCase(Object bean, String property) {
        try {
            Object prop = PropertyUtils.getProperty(bean, property);
            if (prop instanceof String && CommonUtils.hasValue(prop)) {
                PropertyUtils.setSimpleProperty(bean, property, ((String) prop).toUpperCase());
            }
        } catch (Exception e) {
            logger.error("Error al convertir a mayúsculas la propiedad \"" + property + "\" del bean " + bean, e);
        }
    }

    public static void changePropsToUpperCase(Object bean, Iterator props) {
        while (props.hasNext()) {
            BeanUtils.changePropToUpperCase(bean, (String) props.next());
        }
    }

    public static void changeAllPropsToUpperCase(Object bean) {
        try {
            Map description = PropertyUtils.describe(bean);
            Iterator itr = description.keySet().iterator();

            BeanUtils.changePropsToUpperCase(bean, itr);
        } catch (Exception ex) {
            logger.error("Error al crear el mapa de propiedades del bean " + bean, ex);
        }
    }

    public static void changeAllPropsToUpperCaseExcept(Object bean, List<String> props) {
        try {
            Map description = PropertyUtils.describe(bean);
            Iterator itr = description.keySet().iterator();
            List<String> propNames = new ArrayList<String>();

            while (itr.hasNext()) {
                propNames.add((String) itr.next());
            }

            propNames.removeAll(props);
            BeanUtils.changePropsToUpperCase(bean, propNames.iterator());
        } catch (Exception ex) {
            logger.error("Error al crear el mapa de propiedades del bean " + bean, ex);
        }
    }
}
