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

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesIdioma {

    // Dias de la Semana
    public static final int LUNES = 1;
    public static final int MARTES = 2;
    public static final int MIERCOLES = 3;
    public static final int JUEVES = 4;
    public static final int VIERNES = 5;
    public static final int SABADO = 6;
    public static final int DOMINGO = 7;

    // Código
    public static final int CODIGO_ESP = 34;
    public static final int CODIGO_ING = 44;
    public static final int CODIGO_ALE = 49;

    // Idiomas
    public static final String NOMBRE_ESP = "Español";
    public static final String NOMBRE_ING = "Inglés";
    public static final String NOMBRE_ALE = "Alemán";

    // Códigos de Idioma
    public static final int[] LISTA_IDIOMAS = {
        UtilesIdioma.CODIGO_ESP, UtilesIdioma.CODIGO_ING, UtilesIdioma.CODIGO_ALE
    };

    // Dias - Español
    public static final String[] DIAS_ESP = {
        "Lunes", "Martes", "Miércoles", "Jueves",
        "Viernes", "Sábado", "Domingo"
    };

    // Dias - Inglés
    public static final String[] DIAS_ING = {
        "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"};

    // Dias - Alemán
    public static final String[] DIAS_ALE = {
        "Montag", "Dienstag", "Mittwoch", "Donnerstag",
        "Freitag", "Samstag", "Sontag"};

    // Constructor Inaccesible
    private UtilesIdioma() {
    }

    // ATENCION >>> Lunes: 1 - Domingo: 7 <<<
    public static final String generarNombreDia(int dia, int idioma) {
        // Día actual
        String nombre;

        // Análisis idioma
        try {
            nombre = switch (idioma) {
                case CODIGO_ESP -> String.format("%s (%s)",
                        DIAS_ESP[dia - 1], NOMBRE_ESP);
                case CODIGO_ING -> String.format("%s (%s)",
                        DIAS_ING[dia - 1], NOMBRE_ING);
                case CODIGO_ALE -> String.format("%s (%s)",
                        DIAS_ALE[dia - 1], NOMBRE_ALE);
                default -> "Desconocido";
            };
        } catch (Exception e) {
            nombre = "Desconocido";
        }

        // Devuelve nombre
        return nombre;
    }
}
