/*
 * Copyright 2019 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.libraries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesTemporales {

    // Patrones de fecha
    public static final String FMT_DATE_ESP = "dd/MM/yyyy";
    public static final String FMT_DATE_ENG = "yyyy-MM-dd";

    // Patrones de hora
    public static final String FMT_HOUR_LONG = "hh:mm:ss";

    // Formateadores de Fecha Simples
    public static final SimpleDateFormat SDF_DATE_ESP = new SimpleDateFormat(FMT_DATE_ESP);
    public static final SimpleDateFormat SDF_DATE_ING = new SimpleDateFormat(FMT_DATE_ESP);

    // Formateadores de Fecha y Hora
    public static final DateTimeFormatter DTF_DATE_ESP = DateTimeFormatter.ofPattern(FMT_DATE_ESP);
    public static final DateTimeFormatter DTF_DATE_ING = DateTimeFormatter.ofPattern(FMT_DATE_ENG);

    // Nombres de los dias de la semana
    public static final String[] NOMBRE_DIA = {
        "lunes", "martes", "miércoles", "jueves",
        "viernes", "sábado", "domingo"};

    // Nombres de los meses del año
    public static final String[] NOMBRE_MES = {
        "enero", "febrero", "marzo", "abril", "mayo",
        "junio", "julio", "agosto", "septiembre",
        "octubre", "noviembre", "diciembre"};

    // Nombres de las estaciones
    public static final String[] NOMBRE_ESTACION = {
        "primavera", "verano", "otoño", "invierno"};

    // Constructor Inaccesible
    private UtilesTemporales() {
    }

    // Obtener el número de dias del mes del año
    public static final int obtenerDiasMes(int mes, int any) {
        // Número de dias del mes
        int numDias;

        // Análisis
        numDias = switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 ->
                31;
            case 4, 6, 9, 11 ->
                30;
            case 2 ->
                validarBisiesto(any) ? 29 : 28;
            default ->
                0;
        };

        // Devolución resultado
        return numDias;
    }

    // Día (Número) > Día (Nombre)
    public static final String obtenerNombreDia(int dia) {
        return dia >= 1 && dia <= NOMBRE_DIA.length ? NOMBRE_DIA[dia - 1] : "indefinido";
    }

    // Mes (Número) > Mes (Nombre)
    public static final String obtenerNombreMes(int mes) {
        return mes >= 1 && mes <= NOMBRE_MES.length ? NOMBRE_MES[mes - 1] : "indefinido";
    }

    // Fecha (String) > dia (int)
    public static final int obtenerDiaFecha(String fecha) {
        // Dia de la fecha
        int dia;

        try {
            // Desglosa los campos de la fecha
            String[] campo = fecha.split(UtilesExpReg.ER_SEP_FECHA);

            // Convierte el dia a número
            dia = Integer.parseInt(campo[0]);
        } catch (NumberFormatException e) {
            dia = -1;
        }

        // Devuelve el dia
        return dia;
    }

    // Fecha (String) > mes (int)
    public static final int obtenerMesFecha(String fecha) {
        // Mes de la fecha
        int mes;

        try {
            // Desglosa los campos de la fecha
            String[] campo = fecha.split(UtilesExpReg.ER_SEP_FECHA);

            // Convierte el mes a número
            mes = Integer.parseInt(campo[1]);
        } catch (NumberFormatException e) {
            mes = -1;
        }

        // Devuelve el mes
        return mes;
    }

    // Fecha (String) > año (int)
    public static final int obtenerAnyFecha(String fecha) {
        // Año de la fecha
        int any;

        try {
            // Desglosa los campos de la fecha
            String[] campo = fecha.split(UtilesExpReg.ER_SEP_FECHA);

            // Convierte el dia a número
            any = Integer.parseInt(campo[2]);
        } catch (NumberFormatException e) {
            any = -1;
        }

        // Devuelve el año
        return any;
    }

    // Número del Dia del Mes de Hoy
    public static final int obtenerDiaHoy() {
        return new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
    }

    // Número del Mes de Hoy
    public static final int obtenerMesHoy() {
        return new GregorianCalendar().get(Calendar.MONTH) + 1;
    }

    // Número del Año de Hoy
    public static final int obtenerAnyHoy() {
        return new GregorianCalendar().get(Calendar.YEAR);
    }

    // Fecha de Hoy - dd/mm/aaaa
    public static final String obtenerFechaHoy() {
        // Obtiene Fecha Hoy
        Date fechaHoy = new Date();

        // Formatea y devuelve la Fecha
        return SDF_DATE_ESP.format(fechaHoy);
    }

    // Validación Fecha - Texto sin gesglosar - Expresión Regular
    public static final boolean validarFecha(String fecha) {
        return UtilesValidacion.validar(fecha, UtilesExpReg.ER_FECHA);
    }

    // Validación Fecha - Campos Separados - Expresión Regular
    public static final boolean validarFecha(int dia, int mes, int any) {
        // Construye la fecha a partir de sus componentes
        String fecha = String.format("%02d/%02d/%d", dia, mes, any);

        // Devuelve la validación de la fecha
        return UtilesValidacion.validar(fecha, UtilesExpReg.ER_FECHA);
    }

    // Validación Fecha - Date [min..max]
    public static final boolean validarFecha(Date d, Date min, Date max) {
        return true
                && d != null && min != null & max != null
                && d.getTime() >= min.getTime() && d.getTime() <= max.getTime();
    }

    // Comprobar si el año es bisiesto
    public static final boolean validarBisiesto(int any) {
        return any % 400 == 0 || any % 100 != 0 && any % 4 == 0;
    }

    // Calendar > Fecha dd/mm/aaaa ( String )
    public static final String convertir(Calendar c) {
        return c != null ? String.format("%02d/%02d/%d",
                c.get(Calendar.DATE),
                c.get(Calendar.MONTH),
                c.get(Calendar.YEAR)) : "dd/mm/aaaa";
    }

    // Date > Fecha dd/mm/aaaa ( String )
    public static final String convertir(Date d) {
        // Objeto Calendar
        Calendar c = Calendar.getInstance();

        // Date > Calendar
        c.setTime(d);

        // Representación Fecha
        return UtilesTemporales.convertir(c);
    }

    // LocalDate > Fecha dd/mm/aaaa ( String )
    public static final String convertir(LocalDate ld) {
        return ld.format(DTF_DATE_ESP);
    }

    // LocalDate > Fecha dd/mm/aaaa ( String )
    public static final String convertir(LocalDate ld, DateTimeFormatter dtf) {
        return ld.format(dtf);
    }

    // Fecha ( String ) > Fecha ( Date )
    public static final Date convertir(String fecha) {
        return convertir(fecha, SDF_DATE_ESP);
    }

    // Fecha ( String ) > Fecha ( Date ) - Formato Español
    public static final Date convertir(String fecha, SimpleDateFormat sdf) {
        // Referencia
        Date d = null;

        // Convierte Fecha
        try {
            d = sdf.parse(fecha);
        } catch (ParseException e) {
            System.out.println("ERROR: Conversión cancelada - " + e.getMessage());
        }

        // Retorno
        return d;
    }

    // Fecha ( String ) > Fecha ( LocalDate ) - Formato Español
    public static final LocalDate convertirLocal(String fecha, DateTimeFormatter dtf) {
        // Referencia
        LocalDate ld = null;

        // Convierte Fecha
        try {
            ld = LocalDate.parse(fecha, dtf);
        } catch (Exception e) {
            System.out.println("ERROR: Conversión cancelada - " + e.getMessage());
        }

        // Retorno
        return ld;
    }

    // Fecha ( String ) > Fecha ( LocalDate ) - Formato Español
    public static final LocalDate convertirLocal(String fecha) {
        return convertirLocal(fecha, DTF_DATE_ESP);
    }

    // Calendar > Hora hh:mm:ss ( String )
    public static final String obtenerHora(Calendar c) {
        return c != null ? String.format("%02d:%02d:%2d",
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND)) : "hh:mm:ss";
    }

    // Date > Hora hh:mm:ss ( String )
    public static final String obtenerHora(Date d) {
        // Objeto Calendar
        Calendar c = Calendar.getInstance();

        // Date > Calendar
        c.setTime(d);

        // Representación Fecha
        return UtilesTemporales.obtenerHora(c);
    }

    // Calcular dias entre fechas
    public static final int obtenerDistancia(String fechaIni, String fechaFin)
            throws ParseException {
        // Objetos Date
        Date dateIni = convertir(fechaIni);
        Date dateFin = convertir(fechaFin);

        // Distancia Fechas >> ms
        long ms = dateFin.getTime() - dateIni.getTime();

        // ms >> dias
        return (int) (ms / 1000 / 3600 / 24);

//        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    // Calcular dias entre fechas
    public static final String obtenerFechaHoy2() {
        // Fecha del sistema
        Calendar c = Calendar.getInstance();

        // Representación texto
        return String.format("%02d/%02d/%d",
                c.get(Calendar.DATE),
                c.get(Calendar.MONTH),
                c.get(Calendar.YEAR));
    }

    public static final boolean validarHora(String hora) {
        return UtilesValidacion.validar(hora, UtilesExpReg.ER_HORA);
    }

    // SQL Date > String (dd/MM/yyyy)
    public static final String convertirSQLDate2String(java.sql.Date sqlDate) {
        return new SimpleDateFormat(FMT_DATE_ESP).format(sqlDate);
    }

    // SQL Date + Pattern > String
    public static final String convertirSQLDate2String(java.sql.Date sqlDate,
            String pattern) {
        return new SimpleDateFormat(pattern).format(sqlDate);
    }

    // Date > SQL Date
    public static final java.sql.Date convertirDate2SQL(Date date) {
        return new java.sql.Date(date.getTime());
    }
}
