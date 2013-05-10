package com.bconnect.common.util;

/**
 * Implementación del algoritmo de Luhn que revisa la validez de un número de tarjeta de crédito
 * @author jorge.rodriguez
 */
public class AlgoritmoLuhnUtil {

    /**
     * @param ccNumber número a validar
     * @return <b>true</b> Si es válido <b>false</b> otherwise.
     */
    public static boolean check(String ccNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static int generaSumaDeDigitos(String numeroTarjeta) {
        return AlgoritmoLuhnUtil.generaSumaDeDigitos(numeroTarjeta, true);
    }

    public static int generaSumaDeDigitos(String numeroTarjeta, boolean evenPosition) {
        int sum = 0;
        for (int i = numeroTarjeta.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numeroTarjeta.substring(i, i + 1));
            if (evenPosition) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            evenPosition = !evenPosition;
        }
        return sum;
    }

    public static int generaDigitoVerificador(String number) {
        String sumOfDigits = String.valueOf(AlgoritmoLuhnUtil.generaSumaDeDigitos(number, true));
        int unitsDigit = Integer.parseInt(sumOfDigits.substring(sumOfDigits.length() - 1));
        return 10 - unitsDigit;
    }
}
