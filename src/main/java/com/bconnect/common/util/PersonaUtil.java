package com.bconnect.common.util;

import com.bconnect.common.bean.personal.PersonaBean;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author Jorge Rodriguez
 */
public class PersonaUtil {  
    
    //Calcula el RFC de una persona física su homoclave incluida.
    public static String calculaRFC(PersonaBean persona) {
        
        String nombre = persona.getNombres();
        String apellidoPaterno = persona.getApellido1();
        String apellidoMaterno = persona.getApellido2();
        
        Date fechaNacimiento = persona.getFechaNacimiento();
        String fecha = DateUtil.formatDate(fechaNacimiento, CommonConstants.FECHA_FORMATO_YYMMDD);
        //Cambiamos todo a mayúsculas
        nombre = nombre.toUpperCase();
        apellidoPaterno = apellidoPaterno.toUpperCase();
        apellidoMaterno = apellidoMaterno.toUpperCase();

        //RFC que se regresa
        StringBuffer rfc = new StringBuffer();

        //Quitamos los espacios al principio y final del nombre y apellidos
        nombre = nombre.trim();
        apellidoPaterno = apellidoPaterno.trim();
        apellidoMaterno = apellidoMaterno.trim();

        //Quitamos los artículos de los apellidos, incluye los de nombres extranjeros
        apellidoPaterno = quitaArticulos(apellidoPaterno);
        apellidoMaterno = quitaArticulos(apellidoMaterno);

        //Agregamos el primer caracter del apellido paterno
        rfc.append(apellidoPaterno.substring(0, 1));

        //Buscamos y agregamos al rfc la primera vocal del primer apellido
        for (int i = 0; i < apellidoPaterno.length(); i++) {
            if (esVocal(apellidoPaterno.charAt(i))) {
                rfc.append(apellidoPaterno.charAt(i));
                break;
            }
        }

        //Agregamos el primer caracter del apellido materno
        rfc.append(apellidoMaterno.substring(0, 1));

        //Agregamos el primer caracter del primer nombre
        rfc.append(ignoraNombreComun(nombre));

        //agregamos la fecha en formato yymmdd
        rfc.append(fecha);

        //Le agregamos la homoclave al rfc
        rfc.append(calculaHomoclave(persona.getNombreCompletoInverso().toUpperCase(), rfc.toString()));

        return rfc.toString();
    }

    // Calcula la homoclave
    private static String calculaHomoclave(String nombreCompleto, String rfc) {

        //Guardara el nombre en su correspondiente numérico
        StringBuilder nombreEnNumero = new StringBuilder();

        //La suma de la secuencia de números de nombreEnNumero
        long valorSuma = 0;

        //Tablas para calcular la homoclave
        Hashtable tablaRFC1 = new Hashtable();
        tablaRFC1.put("&", "10");
        tablaRFC1.put("Ñ", "10");
        tablaRFC1.put("A", "11");
        tablaRFC1.put("B", "12");
        tablaRFC1.put("C", "13");
        tablaRFC1.put("D", "14");
        tablaRFC1.put("E", "15");
        tablaRFC1.put("F", "16");
        tablaRFC1.put("G", "17");
        tablaRFC1.put("H", "18");
        tablaRFC1.put("I", "19");
        tablaRFC1.put("J", "21");
        tablaRFC1.put("K", "22");
        tablaRFC1.put("L", "23");
        tablaRFC1.put("M", "24");
        tablaRFC1.put("N", "25");
        tablaRFC1.put("O", "26");
        tablaRFC1.put("P", "27");
        tablaRFC1.put("Q", "28");
        tablaRFC1.put("R", "29");
        tablaRFC1.put("S", "32");
        tablaRFC1.put("T", "33");
        tablaRFC1.put("U", "34");
        tablaRFC1.put("V", "35");
        tablaRFC1.put("W", "36");
        tablaRFC1.put("X", "37");
        tablaRFC1.put("Y", "38");
        tablaRFC1.put("Z", "39");
        tablaRFC1.put("0", "0");
        tablaRFC1.put("1", "1");
        tablaRFC1.put("2", "2");
        tablaRFC1.put("3", "3");
        tablaRFC1.put("4", "4");
        tablaRFC1.put("5", "5");
        tablaRFC1.put("6", "6");
        tablaRFC1.put("7", "7");
        tablaRFC1.put("8", "8");
        tablaRFC1.put("9", "9");

        //TablaRFC 2
        Hashtable tablaRFC2 = new Hashtable();
        tablaRFC2.put("0", "1");
        tablaRFC2.put("1", "2");
        tablaRFC2.put("2", "3");
        tablaRFC2.put("3", "4");
        tablaRFC2.put("4", "5");
        tablaRFC2.put("5", "6");
        tablaRFC2.put("6", "7");
        tablaRFC2.put("7", "8");
        tablaRFC2.put("8", "9");
        tablaRFC2.put("9", "A");
        tablaRFC2.put("10", "B");
        tablaRFC2.put("11", "C");
        tablaRFC2.put("12", "D");
        tablaRFC2.put("13", "E");
        tablaRFC2.put("14", "F");
        tablaRFC2.put("15", "G");
        tablaRFC2.put("16", "H");
        tablaRFC2.put("17", "I");
        tablaRFC2.put("18", "J");
        tablaRFC2.put("19", "K");
        tablaRFC2.put("20", "L");
        tablaRFC2.put("21", "M");
        tablaRFC2.put("22", "N");
        tablaRFC2.put("23", "P");
        tablaRFC2.put("24", "Q");
        tablaRFC2.put("25", "R");
        tablaRFC2.put("26", "S");
        tablaRFC2.put("27", "T");
        tablaRFC2.put("28", "U");
        tablaRFC2.put("29", "V");
        tablaRFC2.put("30", "W");
        tablaRFC2.put("31", "X");
        tablaRFC2.put("32", "Y");

        //TablaRFC 3
        Hashtable tablaRFC3 = new Hashtable();
        tablaRFC3.put("A", "10");
        tablaRFC3.put("B", "11");
        tablaRFC3.put("C", "12");
        tablaRFC3.put("D", "13");
        tablaRFC3.put("E", "14");
        tablaRFC3.put("F", "15");
        tablaRFC3.put("G", "16");
        tablaRFC3.put("H", "17");
        tablaRFC3.put("I", "18");
        tablaRFC3.put("J", "19");
        tablaRFC3.put("K", "20");
        tablaRFC3.put("L", "21");
        tablaRFC3.put("M", "22");
        tablaRFC3.put("N", "23");
        tablaRFC3.put("O", "25");
        tablaRFC3.put("P", "26");
        tablaRFC3.put("Q", "27");
        tablaRFC3.put("R", "28");
        tablaRFC3.put("S", "29");
        tablaRFC3.put("T", "30");
        tablaRFC3.put("U", "31");
        tablaRFC3.put("V", "32");
        tablaRFC3.put("W", "33");
        tablaRFC3.put("X", "34");
        tablaRFC3.put("Y", "35");
        tablaRFC3.put("Z", "36");
        tablaRFC3.put("0", "0");
        tablaRFC3.put("1", "1");
        tablaRFC3.put("2", "2");
        tablaRFC3.put("3", "3");
        tablaRFC3.put("4", "4");
        tablaRFC3.put("5", "5");
        tablaRFC3.put("6", "6");
        tablaRFC3.put("7", "7");
        tablaRFC3.put("8", "8");
        tablaRFC3.put("9", "9");
        tablaRFC3.put("", "24");
        tablaRFC3.put(" ", "37");

        //agregamos un cero al inicio de la representación númerica del nombre
        nombreEnNumero.append(0);

        //Recorremos el nombre y vamos convirtiendo las letras en su valor numérico
        for (int i = 0; i < nombreCompleto.length(); i++) {
            if (tablaRFC1.containsKey(nombreCompleto.charAt(i) + "")) {
                nombreEnNumero.append(tablaRFC1.get(nombreCompleto.charAt(i) + ""));
            } else {
                nombreEnNumero.append("00");
            }
        }

        //Calculamos la suma de la secuencia de números
        //calculados anteriormente
        //la formula es:
        //( (el caracter actual multiplicado por diez)
        //mas el valor del caracter siguiente )
        //(y lo anterior multiplicado por el valor del caracter siguiente)
        for (int i = 0; i < nombreEnNumero.length() - 1; i++) {
            valorSuma += ((Integer.parseInt("" + nombreEnNumero.charAt(i)) * 10) + 
                    Integer.parseInt("" + nombreEnNumero.charAt(i + 1))) * 
                    Integer.parseInt("" + nombreEnNumero.charAt(i + 1));
        }

        int div = 0, mod = 0;
        div = (int) valorSuma % 1000;
        mod = div % 34;
        div = (div - mod) / 34;

        int indice = 0;
        StringBuffer homoclave = new StringBuffer(); //los dos primeros caracteres de la homoclave
        while (indice <= 1) {
            if (tablaRFC2.containsKey("" + ((indice == 0) ? div : mod))) {
                homoclave.append(tablaRFC2.get("" + ((indice == 0) ? div : mod)));
            } else {
                homoclave.append("Z");
            }
            indice++;
        }

        //Agregamos al RFC los dos primeros caracteres de la homoclave
        

        //Aqui empieza el calculo del digito verificador basado en lo que tenemos del RFC
        int rfcAnumeroSuma = 0, sumaParcial = 0;
        String rfcParcial = rfc.concat(homoclave.toString());
        for (int i = 0; i < rfcParcial.length(); i++) {
            if (tablaRFC3.containsKey(("" + rfcParcial.charAt(i)))) {
                rfcAnumeroSuma = Integer.parseInt((String) tablaRFC3.get("" + rfcParcial.charAt(i)));
                sumaParcial += (rfcAnumeroSuma * (14 - (i + 1)));
            }
        }

        int moduloVerificador = sumaParcial % 11;
        if (moduloVerificador == 0) {
            homoclave.append("0");
        } else {
            sumaParcial = 11 - moduloVerificador;
            if (sumaParcial == 10) {
                homoclave.append("A");
            } else {
                homoclave.append(sumaParcial);
            }
        }

        return homoclave.toString();
    }

    // Verifica si el caracter pasado es una vocal
    private static boolean esVocal(char letra) {
        if (letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U') {
            return true;
        }
        return false;
    }

    // Remplaza los artículos comúnes en los apellidos en México con caracter vacío ("").
    private static String quitaArticulos(String palabra) {
        return palabra.replace("DEL ", "").replace("LAS ", "").replace("DE ", "").
                replace("LA ", "").replace("Y ", "").replace("A ", "").
                replace("MC ", "").replace("LOS ", "").replace("VON ", "").
                replace("VAN ", "");
    }

    //Regresa la primera vocal del primer nombre ignorando los nombres comunes como José o María
    private static char ignoraNombreComun(String nombre) {

        String nombres[] = nombre.split("\\s");
        if (nombres.length > 1) {
            if (nombres[0].equals("JOSE") ||
                    nombres[0].equals("MARIA") ||
                    nombres[0].equals("MA.") ||
                    nombres[0].equals("MA")) {
                return nombres[1].charAt(0);
            }
        }
        return nombre.charAt(0);
    }
}
