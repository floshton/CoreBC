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
public class IdComparator implements Comparator{

    Logger logger = CommonLogger.getLogger(IdComparator.class);

    public int compare(Object object1, Object object2) {

        int id1 = 0;
        int id2 = 0;

        try {
            String id1String = BeanUtils.getProperty(object1, "id");
            String id2String = BeanUtils.getProperty(object2, "id");

            id1 = Integer.parseInt(id1String);
            id2 = Integer.parseInt(id2String);

        } catch (IllegalAccessException iae) {
            logger.error(iae.getMessage());
            iae.printStackTrace();
        } catch (InvocationTargetException ite) {
            logger.error(ite.getMessage());
            ite.printStackTrace();
        } catch (NoSuchMethodException nsme) {
            logger.error(nsme.getMessage());
            nsme.printStackTrace();
        } catch (NumberFormatException nfe) {
            logger.error(nfe.getMessage());
            nfe.printStackTrace();
        }

        if (id1 < id2) {
            return 1;
        } else if (id1 > id2) {
            return -1;
        } else {
            return 0;
        }

    }
}
