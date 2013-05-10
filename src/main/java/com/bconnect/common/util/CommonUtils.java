package com.bconnect.common.util;

import com.bconnect.common.logging.CommonLogger;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * Esta clase contiene metodos estaticos que son utiles para ejecutar tareas 
 * simples, como manejo de cadenas, etc.
 * @author Jorge Rodriguez
 */
public class CommonUtils {

    /**
     * Verifica que un objeto sea diferente de null y de no ser asi, regresa un 
     * valor default designado
     * @param object el objeto a ser verificado
     * @param returnMsg el valor default en caso de que object sea null
     * @return returnMsg en caso de que object sea igual a null
     */
    public static String checkNullToString(Object object, String returnMsg) {
        return object == null ? returnMsg : object.toString();
    }

    /**
     * Evalua si la representacion String de un objeto es nulo o cadena vacia
     * @param data El objeto a ser evaluado
     * @return true si el objeto es igual a nulo o cadena vacia
     */
    public static boolean hasValue(Object data) {
        boolean hasValue = false;
        if (data instanceof Boolean) {
            hasValue = (Boolean) data;
        } else if (data != null && data.toString()!= null && !data.toString().trim().equals("") && !data.toString().trim().equals("0")) {
            hasValue = true;
        }
        return hasValue;
    }

    /**
     * Recibe una fecha en el formato que la envía el Componente Calendario de
     * JavaScript, usado en las aplicaciones Web, y la parsea para ser enviada
     * a la DB Informix, referenciando el campo comun "intfechacreacion"
     * @param calendarDate la fecha como la envía el Calendario js de Sitel, 
     * usando un formato MM/DD/AAAA
     * @return la fecha para ser enviada como parametro a un SP Informix
     */
    public static String parseCalendarDate(String calendarDate) {
        StringBuffer parsedDate = new StringBuffer();

        parsedDate.append(calendarDate.substring(6, 10));
        parsedDate.append(calendarDate.substring(0, 2));
        parsedDate.append(calendarDate.substring(3, 5));

        return parsedDate.toString();
    }

    public static long intToLong(int theInt) {
        long theLong = 0;
        try {
            theLong = Long.parseLong(String.valueOf(theLong));
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return theLong;
    }

    public static int longToInt(long theLong) {
        int theInt = 0;
        try {
            theInt = Integer.parseInt(String.valueOf(theLong));
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return theInt;
    }

    @Deprecated
    public static String longToNumeroTarjeta(long number) {
        StringBuffer numeroTarjeta = new StringBuffer();
        String numberString = null;
        try {
            numberString = String.valueOf(number);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        int numberZeros = 15 - numberString.length();

        for (int i = 0; i < numberZeros; i++) {
            numeroTarjeta.append("0");
        }
        numeroTarjeta.append(numberString);

        return numeroTarjeta.toString();
    }

    public static String booleanToString(boolean bit) {
        if (bit) {
            return CommonConstants.DB_BIT_TRUE;
        } else {
            return CommonConstants.DB_BIT_FALSE;
        }
    }

    public static boolean stringToBoolean(String bit) {
        boolean bitBoolean = false;
        if (CommonUtils.hasValue(bit)) {
            if (bit.equals(CommonConstants.DB_BIT_TRUE)
                    || bit.equals(CommonConstants.SI)) {
                bitBoolean = true;
            }
        }
        return bitBoolean;
    }

    /**
     * Abre una conexión al URL pasado como parámetro.
     * @param site el URL a accesar
     * @return un DataInputStream del sitio accesado, el cual se puede usar
     * para leer el contenido de dicha URL
     */
    public static DataInputStream openSite(String site) {
        DataInputStream input = null;

        Logger logger = CommonLogger.getLogger(CommonUtils.class);

        try {
            URL siteURL = new URL(site);
            URLConnection urlConnection = siteURL.openConnection();
            input = new DataInputStream(urlConnection.getInputStream());

            InputStream is = siteURL.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String theLine;
            while ((theLine = br.readLine()) != null) {
                logger.debug(theLine);
            }
            br.close();
            isr.close();
            is.close();

        } catch (MalformedURLException mue) {
            logger.error("Error al abrir la conexión para el sitio " + site, mue);
        } catch (IOException ioe) {
            logger.error("Error al abrir la conexión para el sitio " + site, ioe);
        }
        return input;
    }

    public static String openUrlPost(String site) {
        StringBuffer html = new StringBuffer();

        try {
            URL url;
            URLConnection urlConn;
            DataOutputStream printout;
            DataInputStream input;
            // URL of CGI-Bin script.
            url = new URL(site);
            // URL connection channel.
            urlConn = url.openConnection();
            // Let the run-time system (RTS) know that we want input.
            urlConn.setDoInput(true);
            // Let the RTS know that we want to do output.
            urlConn.setDoOutput(true);
            // No caching, we want the real thing.
            urlConn.setUseCaches(false);
            // Specify the content type.
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream());
            printout.flush();
            printout.close();
            // Get response data.
            input = new DataInputStream(urlConn.getInputStream());
            String str;
            while (null != ((str = input.readLine()))) {
                html.append(str);
            }
            input.close();

        } catch (MalformedURLException mue) {
            System.out.println(mue);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return html.toString();
    }

    public static String getPageContent(String site) {
        String result = null;
        URLConnection connection = null;
        URL url = null;
        try {
            url = new URL(site);
            connection = url.openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            result = scanner.next();
        } catch (IOException ex) {
        }
        return result;
    }

    public static String openUrl(String site) {
        StringBuffer html = new StringBuffer();

        Logger logger = CommonLogger.getLogger(CommonUtils.class);

        try {
            URL siteURL = new URL(site);

            InputStream is = siteURL.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String theLine;
            while ((theLine = br.readLine()) != null) {
                html.append(theLine);
            }
            br.close();
            isr.close();
            is.close();

        } catch (MalformedURLException mue) {
            logger.error("Error al abrir la conexión para el sitio " + site, mue);
        } catch (IOException ioe) {
            logger.error("Error al abrir la conexión para el sitio " + site, ioe);
        }
        return html.toString();
    }

    public static boolean isValidEmailAddress(String mailAddress) {
        Pattern pattern = Pattern.compile(CommonConstants.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mailAddress);
        return matcher.matches();
    }

    public static int checkNullToInteger(String text) {
        int number = 0;
        try {
            number = Integer.parseInt(text);
        } catch (Exception e) {
        }
        return number;
    }
    
    public static int getSize(List list){
        return list != null ? list.size() : 0;
    }
    /**
     * Funcion que sirve para convertir a mayusculas.
     * @param object
     * @return 
     */
    public static String toUpperCase(String object){
        return CommonUtils.hasValue(object) ? 
                object.toUpperCase() : CommonConstants.EMPTY_STRING;
    }
}
