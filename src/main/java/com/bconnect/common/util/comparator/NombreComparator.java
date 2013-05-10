package com.bconnect.common.util.comparator;

import com.bconnect.common.logging.CommonLogger;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Jorge Rodriguez
 */
public class NombreComparator implements Comparator {

    Logger logger = CommonLogger.getLogger(NombreComparator.class);

    public int compare(Object object1, Object object2) {
        String nombre1 = null;
        String nombre2 = null;

        try {
            nombre1 = BeanUtils.getProperty(object1, "nombre");
            nombre2 = BeanUtils.getProperty(object2, "nombre");
        } catch (IllegalAccessException iae) {
            logger.error(iae.getMessage());
            iae.printStackTrace();
        } catch (InvocationTargetException ite) {
            logger.error(ite.getMessage());
            ite.printStackTrace();
        } catch (NoSuchMethodException nsme) {
            logger.error(nsme.getMessage());
            nsme.printStackTrace();
        }

        if (nombre1.compareTo(nombre2) > 0) {
            return 1;
        } else if (nombre1.compareTo(nombre2) < 0) {
            return -1;
        } else {
            return 0;
        }

    }
}
