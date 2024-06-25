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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesExpReg {
    // ExpReg - Hora - [ 0 - 23 ]

    public static final String ER_HOR = "([01]\\d|2[0123])";

    // ExpReg - Minutos - [ 0 - 59 ]
    public static final String ER_MIN = "([012345]\\d)";

    // ExpReg - Fecha
    public static final String ER_HORA = ER_HOR + ":" + ER_MIN + ":" + ER_MIN;

    // ExpReg - Día del mes hasta 28 - [1..28] / [01..28]
    public static final String ER_DIA28 = "(0?[1-9]|1[0-9]|2[0-8])";

    // ExpReg - Mes del año - [1..12] / [01..12]
    public static final String ER_MES = "(0?[1-9]|1[0-2])";

    // ExpReg - Año - [0..9999]
    public static final String ER_ANY = "([0-9]|[1-9][0-9]|[1-9][0-9]{2}|[1-9][0-9]{3})";

    // ExpReg - Separador de campos de fecha: "/" o "-"
    public static final String ER_SEP_FECHA = "[/-]";

    // ExpReg - Fecha válida entre el 1 y el 28 de cualquier mes
    public static final String ER_FECHA_DIA28
            = "(" + ER_DIA28 + ER_SEP_FECHA + ER_MES + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Años SI divisibles por 400 (Hasta 4 digitos)
    public static final String ER_ANYS_MOD400
            = "(" + "0?[48]00|[13579][26]00|[2468][048]00" + ")";

    // ExpReg - Años NO divisibles por 100 pero SI divisibles por 4 (Hasta 4 dígitos)
    public static final String ER_ANYS_MOD4_NO100
            = "(" + "[0-9]{0,2}" + "((0?|[2468])[48]|[13579][26]|[2468]0)" + ")";    // Desde 4 hasta 96

    // ExpReg - Años Bisiestos (Hasta 4 digitos)
    public static final String ER_ANYS_BISIESTOS
            = "(" + ER_ANYS_MOD400 + "|" + ER_ANYS_MOD4_NO100 + ")";

    // ExpReg - Fecha válida para 29 de Febreros BISIESTOS
    public static final String ER_FECHA_DIA29_BISIESTO
            = "(" + "29" + ER_SEP_FECHA + "(2|02)" + ER_SEP_FECHA + ER_ANYS_BISIESTOS + ")";

    // ExpReg - Meses que tienen 30 dias (Todos menos Febrero)
    public static final String ER_MESES_30DIAS = "(0?[13456789]|1[012])";

    // ExpReg - Fecha válida para el 29 de cualquier Mes EXCEPTO Febrero
    public static final String ER_FECHA_DIA29_NORMAL
            = "(" + "29" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Fecha válida para el 29 de cualquier Mes INCLUIDO Febrero
    public static final String ER_FECHA_DIA29
            = "(" + ER_FECHA_DIA29_BISIESTO + "|" + ER_FECHA_DIA29_NORMAL + ")";

    // ExpReg - Fecha válida para el 30 de cualquier Mes
    public static final String ER_FECHA_DIA30
            = "(" + "30" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Meses que tienen 31 dias
    public static final String ER_MESES_31DIAS = "(0?[13578]|1[02])";

    // ExpReg - Fecha válida para el 31 de cualquier Mes
    public static final String ER_FECHA_DIA31
            = "(" + "31" + ER_SEP_FECHA + ER_MESES_31DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Fecha válida (Cualquiera)
    public static final String ER_FECHA
            = "(" + ER_FECHA_DIA28 + "|" + ER_FECHA_DIA29 + "|" + ER_FECHA_DIA30 + "|" + ER_FECHA_DIA31 + ")";

    // Constructor Inalcanzable
    private UtilesExpReg() {
    }

    // ER > Pattern
    public static final Pattern compilar(String er) {
        return compilar(er, 0);
    }

    // ER + Modificadores > Pattern
    public static final Pattern compilar(String er, int modificadores) {
        Pattern p = null;
        try {
            p = Pattern.compile(er, modificadores);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    // ER + Texto > Matcher
    public static final Matcher enlazar(String texto, String er) {
        Matcher m = null;
        try {
            m = compilar(er).matcher(texto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return m;
    }

    // Texto + Expresión Regular > Coincidencia
    public static final boolean validarCoincidencia(String dato, String er) {
        return UtilesExpReg.enlazar(dato, er).matches();
    }

    // Texto + Expresión Regular > Existencia
    public static final boolean validarExistencia(String dato, String er) {
        return UtilesExpReg.enlazar(dato, er).lookingAt();
    }

    // Texto + Expresión Regular > Lista Coincidencias
    public static final List<String> obtenerCoincidencias(String dato, String er) {
        List<String> lista = new ArrayList<>();

        Matcher m = enlazar(dato, er);

        while (m.find()) {
            String s = m.group(1);
            lista.add(s);
//            System.out.println(m.group());
//            System.out.println(m.group(1));
        }

        return lista;
    }

    // Texto + Expresión Regular > Número Coincidencias
    public static final int contarCoincidencias(String dato, String er) {
        return obtenerCoincidencias(dato, er).size();
    }
}
