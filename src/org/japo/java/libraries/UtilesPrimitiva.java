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

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesPrimitiva {

    // Referencias
    public static final int DEF_NUM_BOLAS = 49;
    public static final int DEF_NUM_EQUIS = 6;

    // Constructor Inaccesible
    private UtilesPrimitiva() {
    }

    // Bombo Loteria Primitiva + Array
    public static final int[] generarBomboArray() {
        // Definir bombo
        int[] bombo = new int[DEF_NUM_BOLAS];

        // Llenar bombo - Primer Número: 1
        for (int i = 0; i < bombo.length; i++) {
            bombo[i] = i + 1;
        }

        // Devolver Bombo
        return bombo;
    }

    // Apuesta Aleatoria Loteria Primitiva + Array
    public static final int[] generarApuestaArray() {
        // Definir Apuesta
        int[] apuesta = new int[DEF_NUM_EQUIS];

        // Definir bombo
        int[] bombo = generarBomboArray();

        // Generar apuesta (Método Estanteria)
        for (int i = 0; i < apuesta.length; i++) {
            // Genera la posición actual
            int posicion = UtilesAleatorios.generarEntero(0, DEF_NUM_BOLAS - i);

            // Extrae la bola de la posición actual
            apuesta[i] = bombo[posicion];

            // Rellena el hueco
            bombo[posicion] = bombo[DEF_NUM_BOLAS - i - 1];
        }

        // Devolver Apuesta
        return apuesta;
    }

    // Apuesta Aleatoria Loteria Primitiva + ArrayList
    public static final ArrayList<Integer> generarApuestaLista() {
        // Definir Apuesta
        ArrayList<Integer> apuesta = new ArrayList<>();

        // Definir bombo
        ArrayList<Integer> bombo = generarBomboLista();

        // Generar apuesta
        for (int i = 0; i < DEF_NUM_EQUIS; i++) {
            apuesta.add(bombo.remove(UtilesAleatorios.generarEntero(0, bombo.size() - 1)));
        }

        // Devolver Apuesta
        return apuesta;
    }

    // Bombo Loteria Primitiva + ArrayList
    public static final ArrayList<Integer> generarBomboLista() {
        // Definir bombo
        ArrayList<Integer> bombo = new ArrayList<>();

        // Llenar bombo - Primer Número: 1
        for (int i = 0; i < DEF_NUM_BOLAS; i++) {
            bombo.add(i, i + 1);
        }

        // Devolver Bombo
        return bombo;
    }
}
