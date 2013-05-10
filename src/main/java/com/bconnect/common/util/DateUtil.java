package com.bconnect.common.util;

import com.bconnect.common.bean.GenericBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Esta clase enlista varios metodos estaticos para manipulacion de fechas
 * a lo largo de la aplicacion, tales como formateo, parseo y transformacion
 * @author Jorge Rodriguez
 */
public class DateUtil {

    public static final int MILISEGUNDOS_POR_HORA = 3600000;
    public static final int MILISEGUNDOS_POR_MINUTO = 60000;
    public static final int MILISEGUNDOS_POR_SEGUNDO = 1000;

    @Deprecated
    public static double calendarDateToDouble(String calendarDate) {
        double parsed = 0;
        StringBuffer format = new StringBuffer();

        format.append(calendarDate.substring(6));
        format.append(calendarDate.substring(0, 2));
        format.append(calendarDate.substring(3, 5));

        try {
            parsed = Double.parseDouble(format.toString());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        return parsed;
    }

    /**
     * Recibe una fecha con formato MM/DD/YYYY y lo regresa como YYYY-MM-DD
     * @return
     */
    public static String calendarDateToDBFormat(String calendarDate) {
        if (CommonUtils.hasValue(calendarDate)) {
            StringBuffer newDate = new StringBuffer();

            newDate.append(calendarDate.substring(6));
            newDate.append("-");
            newDate.append(calendarDate.substring(0, 2));
            newDate.append("-");
            newDate.append(calendarDate.substring(3, 5));

            return newDate.toString();
        } else {
            return calendarDate;
        }
    }

    @Deprecated
    public static double calendarDateToIntFecha(String calendarDate) {
        double parsed = 0;
        StringBuffer format = new StringBuffer();
        format.append(calendarDate.substring(6));
        format.append(calendarDate.substring(3, 5));
        format.append(calendarDate.substring(0, 2));
        try {
            parsed = Double.parseDouble(format.toString());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return parsed;
    }

    /**
     * Construye una fecha en base a un String y un formato o patron
     * @param value la cadena que representa la fecha
     * @param El patron/formato destino
     * @return un String transformado como instancia de Date
     * @throws ParseException
     */
    public static java.util.Date parseDate(String value, String pattern) throws ParseException {
        if (!CommonUtils.hasValue(value)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        java.util.Date tempDate = format.parse(value);
        return new java.util.Date(tempDate.getTime());
    }

    /**
     * Da formato a una instancia Date de acuerdo a un patron especificado
     * @param date El objeto Date a ser formateado
     * @param pattern el formato deseado de salida
     * @return la fecha como String en el formato especificado
     */
    public static String formatDate(java.util.Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
            return format.format(date);
        }
        return "";
    }

    /**
     * Crea una instancia de Date con la fecha actual
     * @return una instancia de Date que representa la fecha del sistema
     */
    public static java.util.Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        java.util.Date sysDate = cal.getTime();
        return sysDate;
    }

    public static java.util.Date transformCalendarDate(String calendarDate) {
        java.util.Date transformedDate = null;
        try {
            transformedDate = parseDate(calendarDate,
                    CommonConstants.FECHA_FORMATO_DDMMYYYY);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return transformedDate;
    }

    public static java.util.Date transformDBDate(String dbDate) {
        java.util.Date transformedDate = null;
        try {
            transformedDate = parseDate(dbDate,
                    CommonConstants.FECHA_FORMATO_DB);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return transformedDate;
    }

    public static java.util.Date getDate(Object dateObject) {
        java.util.Date date = null;

        if (dateObject instanceof String) {
            if (CommonUtils.hasValue((String) dateObject)) {
                String dateString = (String) dateObject;
                // Si la fecha incluye "/", proviene del calendario Sitel
                if (dateString.indexOf("/") > 0) {
                    date = DateUtil.transformCalendarDate(dateString);
                    // es fecha de DB, producto de un query
                } else {
                    date = DateUtil.transformDBDate(dateString);
                }
            }
        } else if (dateObject instanceof java.util.Date) {
            date = (java.util.Date) dateObject;
        }

        return date;
    }

    public static int findDifferenceYears(java.util.Date date1, java.util.Date date2) {
        return DateUtil.findDifference(date1, date2, Calendar.YEAR);
    }

    public static int findDifferenceMonths(java.util.Date date1, java.util.Date date2) {
        return DateUtil.findDifference(date1, date2, Calendar.MONTH);
    }

    public static int findDifferenceDays(java.util.Date date1, java.util.Date date2) {
        return DateUtil.findDifference(date1, date2, Calendar.DAY_OF_MONTH);
    }

    public static int findDifference(java.util.Date date1, java.util.Date date2, int field) {
        int difference = 0;
        java.util.Date afterDate = null;
        java.util.Date beforeDate = null;

        if (date1.after(date2)) {
            afterDate = date1;
            beforeDate = date2;
        } else {
            afterDate = date2;
            beforeDate = date1;
        }
        Calendar calendarFirst = Calendar.getInstance();
        Calendar calendarLast = Calendar.getInstance();

        calendarFirst.setTime(beforeDate);
        calendarLast.setTime(afterDate);

        difference = calendarLast.get(field) - calendarFirst.get(field);

        return difference;
    }

    public static long findDiffereceHours(Calendar fechaInicio, Calendar fechaFin) {
        long differenceHours = 0;
        long differenceMinutes = DateUtil.findDiffereceMinutes(fechaInicio, fechaFin);

        differenceHours = differenceMinutes / 60;
        return differenceHours;
    }

    public static long findDiffereceMinutes(Calendar fechaInicio, Calendar fechaFin) {
        long differenceMinutes = 0;
        long differenceSeconds = DateUtil.findDiffereceSeconds(fechaInicio, fechaFin);

        differenceMinutes = differenceSeconds / 60;
        return differenceMinutes;
    }

    public static long findDiffereceMinutes(java.util.Date fechaInicio, java.util.Date fechaFin) {
        long differenceMinutes = 0;
        Calendar fecha1 = Calendar.getInstance();
        fecha1.setTime(fechaInicio);
        Calendar fecha2 = Calendar.getInstance();
        fecha2.setTime(fechaFin);
        long differenceSeconds = DateUtil.findDiffereceSeconds(fecha1, fecha2);

        differenceMinutes = differenceSeconds / 60;
        return differenceMinutes;
    }

    public static long findDiffereceSeconds(Calendar fechaInicio, Calendar fechaFin) {
        long differenceSeconds = 0;
        long differenceMilliseconds = DateUtil.findDiffereceMilliseconds(fechaInicio, fechaFin);

        differenceSeconds = differenceMilliseconds / 1000;
        return differenceSeconds;
    }

    public static long findDiffereceMilliseconds(Calendar fechaInicio, Calendar fechaFin) {
        long difference = 0;

        int yearInicio = fechaInicio.get(Calendar.YEAR);
        int diaInicio = fechaInicio.get(Calendar.DAY_OF_YEAR);
        int horasInicio = fechaInicio.get(Calendar.HOUR_OF_DAY);
        int minutosInicio = fechaInicio.get(Calendar.MINUTE);
        int segundosInicio = fechaInicio.get(Calendar.SECOND);
        int milisegundosInicio = fechaInicio.get(Calendar.MILLISECOND);

        int yearFin = fechaFin.get(Calendar.YEAR);
        int diaFin = fechaFin.get(Calendar.DAY_OF_YEAR);
        int horasFin = fechaFin.get(Calendar.HOUR_OF_DAY);
        int minutosFin = fechaFin.get(Calendar.MINUTE);
        int segundosFin = fechaFin.get(Calendar.SECOND);
        int milisegundosFin = fechaFin.get(Calendar.MILLISECOND);

        if (yearInicio != yearFin) {
            int diferenciaYears = yearFin - yearInicio;
            diaFin += diferenciaYears * 365;
        }
        if (diaInicio != diaFin) {
            int diferenciaDias = diaFin - diaInicio;
            horasFin += diferenciaDias * 24;
        }
        if (horasInicio != horasFin) {
            if (horasFin > horasInicio && minutosFin > minutosInicio) {
                int diferenciaHoras = horasFin - horasInicio;
                minutosFin = diferenciaHoras * 60;
            }
        }
        horasInicio *= DateUtil.MILISEGUNDOS_POR_HORA;
        minutosInicio *= DateUtil.MILISEGUNDOS_POR_MINUTO;
        segundosInicio *= DateUtil.MILISEGUNDOS_POR_SEGUNDO;

        horasFin *= DateUtil.MILISEGUNDOS_POR_HORA;
        minutosFin *= DateUtil.MILISEGUNDOS_POR_MINUTO;
        segundosFin *= DateUtil.MILISEGUNDOS_POR_SEGUNDO;

        difference += horasFin - horasInicio;
        difference += minutosFin - minutosInicio;
        difference += segundosFin - segundosInicio;
        difference += milisegundosFin - milisegundosInicio;

        return difference;
    }

    public static int calculaEdad(java.util.Date fechaNacimiento) {
        Calendar now = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaNacimiento);

        int edad = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
                || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }

    /**
     * 
     * @param birthDate en formato DD/MM/YYYY
     * @return
     */
    public static String getAge(String fechaNacimientoString) {
        java.util.Date fechaActual = DateUtil.getSystemDate();
        java.util.Date fechaNacimiento = null;
        try {
            fechaNacimiento = DateUtil.parseDate(fechaNacimientoString, "dd/MM/yyyy");
        } catch (Exception e) {
        }

        Integer yearsDifference = findDifferenceYears(fechaActual, fechaNacimiento);
        Integer monthsDifference = findDifferenceMonths(fechaActual, fechaNacimiento);
        Integer daysDifference = findDifferenceDays(fechaActual, fechaNacimiento);

        if (daysDifference < 0) {
            monthsDifference--;
            daysDifference = 0;
        }
        if (monthsDifference < 0) {
            yearsDifference--;
            monthsDifference = 0;
        }

        StringBuffer age = new StringBuffer();
        age.append(StringUtils.addInitialZeros(yearsDifference, 2));
        age.append(StringUtils.addInitialZeros(monthsDifference, 2));
        age.append(StringUtils.addInitialZeros(daysDifference, 2));

        return age.toString();
    }

    public static List getListaDias(int diaDeLaSemana, int repeticiones,
            int periodicidad, boolean pasado) {
        List dias = new ArrayList();
        int numeroSuma = pasado ? -1 : 1;

        Calendar baseCalendar = Calendar.getInstance();
        baseCalendar.setTime(DateUtil.getSystemDate());

        boolean esDiaBuscado = false;
        while (!esDiaBuscado) {
            if (baseCalendar.get(Calendar.DAY_OF_WEEK) == diaDeLaSemana) {
                esDiaBuscado = true;
                break;
            } else {
                baseCalendar.add(Calendar.DAY_OF_YEAR, numeroSuma);
            }
        }
        for (int f = 1; f <= repeticiones; f++) {
            GenericBean dia = new GenericBean();
            java.util.Date time = baseCalendar.getTime();

            dia.setClave(DateUtil.formatDate(time,
                    CommonConstants.FECHA_FORMATO_DDMMYYYY));
            dia.setNombre(DateUtil.formatDate(time,
                    CommonConstants.FECHA_FORMATO_WEB_MMM_D_YYYY));

            baseCalendar.add(periodicidad, numeroSuma);
            dias.add(dia);
        }
        return dias;
    }

    public static java.util.Date getPrimerDiaMes() {
        java.util.Date primerDia = null;
        Calendar calendarioInicio = Calendar.getInstance();
        calendarioInicio.set(Calendar.DAY_OF_MONTH, 1);

        primerDia = calendarioInicio.getTime();

        return primerDia;
    }

    public static java.util.Date getDiaVencido() {
        java.util.Date diaVencido = null;
        Calendar calendarFin = Calendar.getInstance();
        int diaHoy = calendarFin.get(Calendar.DAY_OF_YEAR);
        calendarFin.set(Calendar.DAY_OF_YEAR, diaHoy - 1);

        diaVencido = calendarFin.getTime();

        return diaVencido;
    }

    public static boolean isWeekend(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return DateUtil.isWeekend(calendar);
    }

    public static boolean isWeekend(Calendar calendar) {
        boolean isWeekend = false;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY
                || dayOfWeek == Calendar.SUNDAY) {
            isWeekend = true;
        }
        return isWeekend;
    }

    public static boolean isValidRange(java.util.Date date1, java.util.Date date2) {
        return date1.before(date2);
    }

    /**
     * Recibe una fecha con formato DD/MM/YYYY y lo regresa como YYYY-MM-DD
     * @return
     */
    public static String calendarDateToDBFormatJQUERY(String calendarDate) {
        if (CommonUtils.hasValue(calendarDate)) {
            StringBuffer newDate = new StringBuffer();

            newDate.append(calendarDate.substring(6));
            newDate.append("-");
            newDate.append(calendarDate.substring(3, 5));
            newDate.append("-");
            newDate.append(calendarDate.substring(0, 2));


            return newDate.toString();
        } else {
            return calendarDate;
        }
    }

    public static Calendar switchTimeZone(Calendar originalCalendar, TimeZone targetTimeZone) {
        Calendar newCalendar = Calendar.getInstance(targetTimeZone);


        newCalendar.set(Calendar.DAY_OF_YEAR, originalCalendar.get(Calendar.DAY_OF_YEAR));
        newCalendar.set(Calendar.YEAR, originalCalendar.get(Calendar.YEAR));

        newCalendar.set(Calendar.HOUR_OF_DAY, originalCalendar.get(Calendar.HOUR_OF_DAY));
        newCalendar.set(Calendar.MINUTE, originalCalendar.get(Calendar.MINUTE));

        System.out.println("local mili " + originalCalendar.getTimeInMillis());
        System.out.println("new mili " + newCalendar.getTimeInMillis());

//        int originalOffset = originalCalendar.getTimeZone().getOffset(originalCalendar.getTime().getTime());
//        int newOffset = newCalendar.getTimeZone().getOffset(originalCalendar.getTime().getTime());

        int originalOffset = originalCalendar.getTimeZone().getRawOffset();
        int newOffset = newCalendar.getTimeZone().getRawOffset();

        System.out.println("local offset " + originalOffset + " " + originalCalendar.getTimeZone().getDisplayName());
        System.out.println("new offset " + newOffset + " " + newCalendar.getTimeZone().getDisplayName());

        int diffOffset = newOffset - originalOffset;

        System.out.println("diff offset " + diffOffset);

        newCalendar.add(Calendar.MILLISECOND, diffOffset);

        return newCalendar;
    }

    public static Calendar switchTimeZone(Calendar originalCalendar, String idTargetTz) {
        return DateUtil.switchTimeZone(originalCalendar, TimeZone.getTimeZone(idTargetTz));
    }

    public static Calendar switchTimeZone(Calendar originalCalendar) {
        return DateUtil.switchTimeZone(originalCalendar, TimeZone.getDefault());
    }

    public static java.util.Date getInicioDia(java.util.Date fecha) {
        Calendar inicioDia = Calendar.getInstance();
        inicioDia.setTime(fecha);
        inicioDia.set(Calendar.HOUR_OF_DAY, 0);
        inicioDia.set(Calendar.MINUTE, 0);
        inicioDia.set(Calendar.SECOND, 0);
        inicioDia.set(Calendar.MILLISECOND, 0);
        return inicioDia.getTime();
    }

    public static java.util.Date getFinDia(java.util.Date fecha) {
        Calendar inicioDia = Calendar.getInstance();
        inicioDia.setTime(fecha);
        inicioDia.set(Calendar.HOUR_OF_DAY, 0);
        inicioDia.set(Calendar.MINUTE, 0);
        inicioDia.set(Calendar.SECOND, 0);
        inicioDia.set(Calendar.MILLISECOND, 0);

        inicioDia.add(Calendar.SECOND, -1);
        return inicioDia.getTime();
    }

    public static Integer getDiaDeLaSemana(java.util.Date fecha) {
        Calendar fechaSeleccionada = Calendar.getInstance();
        fechaSeleccionada.setTime(fecha);
        return fechaSeleccionada.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isDateBetween(java.util.Date dateToCompare,
            java.util.Date minDate, java.util.Date maxDate) {
        return dateToCompare.after(minDate) && dateToCompare.before(maxDate);
    }

    /**
     * Este método busca la fecha correspondiente al día de la semana que se requiere,
     * para una fecha proporcionada.
     * Ejemplo: si recibo un 10 de Marzo y un día Jueves, el método busca los días
     * correspondientes a la semana del 10 de marzo, y regresa la fecha del jueves de
     * la misma semana
     * @param fecha fecha base
     * @param diaRequerido día de la semana que hay que buscar
     * @return 
     */
    public static Date getFechaDeDiaMismaSemana(Date fechaBase, Integer diaRequerido, Integer primerDiaSemana) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(fechaBase);
        boolean primerDiaDeSemanaLocalizado = false;
        while (!primerDiaDeSemanaLocalizado) {
            if (primerDiaSemana == DateUtil.getDiaDeLaSemana(fecha.getTime())) {
                primerDiaDeSemanaLocalizado = true;
            } else {
                fecha.add(Calendar.DATE, -1);
            }
        }
        
        boolean diaRequeridoLocalizado = false;
        int diasRestantesPorRevisar = 6;
        while(!diaRequeridoLocalizado && diasRestantesPorRevisar >= 0){
            if(fecha.get(Calendar.DAY_OF_WEEK) == diaRequerido){
                diaRequeridoLocalizado = true;
            } else{
                fecha.add(Calendar.DATE, 1);
                diasRestantesPorRevisar--;
            }
        }
        return fecha.getTime();
    }
}
