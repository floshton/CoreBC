package com.bconnect.common.util;

/**
 * Clase que enlista métodos para efectuar tareas de formato de textos, 
 * principalmente para ser usadas como funciones en archivos JSP, previo
 * registro en un archivo TLD
 * @author Jorge Rodriguez
 */
public class StringUtils {

    /**
     * Regresa un String en mayúsculas
     * @param text el texto a ser transformado
     * @return el texto resultado de la transformación
     */
    public static String toUpperCase(String text) {
        return text != null ? text.toUpperCase() : "";
    }

    /**
     * Regresa un String en minúsculas
     * @param text el texto a ser transformado
     * @return el texto resultado de la transformación
     */
    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    /**
     * Regresa un String que contiene la primer letra de cada palabra en mayúsculas
     * @param text el texto a ser transformado
     * @return el texto resultado de la transformación
     */
    public static String toTitleCase(String text) {
        String[] tokens = text.split(" ");
        StringBuffer newWord = null;
        StringBuffer transformedText = new StringBuffer();

        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];
            newWord = new StringBuffer();
            newWord.append(word.substring(0, 1).toUpperCase());
            newWord.append(word.substring(1).toLowerCase());
            newWord.append(" ");

            transformedText.append(newWord);
        }
        transformedText.setLength(transformedText.length() - 1);

        return transformedText.toString();
    }

    public static String formatCardNumber(String number) {
        StringBuffer formatted = new StringBuffer();

        if (number.length() >= 15) {
            formatted.append(number.substring(0, 4));
            formatted.append(" - ");
            formatted.append(number.substring(4, 8));
            formatted.append(" - ");
            formatted.append(number.substring(8, 12));
            formatted.append(" - ");
            formatted.append(number.substring(12));
        }
        return formatted.toString();
    }

    public static String addInitialZeros(Object text, int totalSize) {
        StringBuffer newText = new StringBuffer(text.toString());

        int zeros = totalSize - newText.length();
        for (int i = 0; i < zeros; i++) {
            newText.insert(i, "0");
        }

        return newText.toString();
    }

    public static String completaConEspacios(Object text, int totalSize) {
        StringBuffer newText = new StringBuffer(text.toString());

        int zeros = totalSize - newText.length();
        for (int i = 0; i < zeros; i++) {
            newText.append(CommonConstants.SPACE_STRING);
        }

        return newText.toString();
    }

    public static String completeString(Object text, int totalSize,
            char charToUse, boolean isRightSide) {
        StringBuffer newText = new StringBuffer(text.toString());

        int charSpaces = totalSize - newText.length();

        if (isRightSide) {
            for (int i = 0; i < charSpaces; i++) {
                newText.append(charToUse);
            }
        } else {
            for (int i = 0; i < charSpaces; i++) {
                newText.insert(i, charToUse);
            }
        }

        return newText.toString();
    }

    public static String removeCharacter(String theChar, String theString) {
        StringBuffer newText = new StringBuffer();
        if (CommonUtils.hasValue(theString)) {
            for (int i = 0; i < theString.length(); i++) {
                String piece = theString.substring(i, i + 1);
                if (!piece.equals(theChar)) {
                    newText.append(piece);
                }
            }
        }
        return newText.toString();
    }

    public static String changeTextSpacing(String text, int spacing) {
        StringBuffer separation = new StringBuffer();
        StringBuffer spacedText = new StringBuffer();
        for (int i = 0; i < spacing; i++) {
            separation.append(CommonConstants.SPACE_STRING);
        }

        char[] tokens = text.toCharArray();
        for (int i = 0; i < tokens.length; i++) {
            char c = tokens[i];
            spacedText.append(c);
            spacedText.append(separation);
        }
        return spacedText.toString().trim();
    }

    public static String getSpaces(int numberOfBlanks) {
        StringBuffer spaces = new StringBuffer();
        for (int i = 0; i < numberOfBlanks; i++) {
            spaces.append(CommonConstants.SPACE_STRING);
        }
        return spaces.toString();
    }

    public static String trimInternal(String string) {
        return string.replace(" ", "");
    }

    public static String completaConEspaciosAnchoFijo(Object text, int totalSize) {
        StringBuilder newText = new StringBuilder(text.toString());

        int zeros = totalSize - newText.length();
        for (int i = 0; i < zeros; i++) {
            newText.append(CommonConstants.SPACE_STRING);
        }

        return newText.toString().substring(0, totalSize);
    }
}
