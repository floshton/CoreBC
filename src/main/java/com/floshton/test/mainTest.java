package com.floshton.test;

import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.DateUtil;
import com.bconnect.common.util.StringUtils;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author Jorge Rodriguez
 */
public class mainTest {

    public static void main(String[] args) {
        try {
            String original = "http://172.16.30.209:8080/SegurosAmex/faces/mail/alertaCancelacion.xhtml?idCliente=284&nombreCsp=SAID GUERRERO&nombreValidador=SAID GUERRERO&motivo=YA CUENTA CON EL SEGURO&producto=AMEX GUARD&nota=nooo";
            String finalUrl = URLEncoder.encode(original, "UTF8");
            System.out.println(finalUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
