/*
 * Copyright 2020 José A. Pacheco Ondoño - joanpaon@gmail.com.
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesFicheros {

    // Nombre predeterminado del Fichero
    public static final String DEF_FICHERO = "datos.txt";

    // Constructor Inaccesible
    private UtilesFicheros() {
    }

//    // Linea texto > Fichero (Nuevo)
//    public static final boolean escribirLineaFichero(String dato, String fichero) {
//        // Semáforo
//        boolean procesoOK = true;
//
//        // Proceso Escritura
//        try (FileWriter fw = new FileWriter(fichero)) {
//            fw.append(dato + System.getProperty("line.separator"));
//        } catch (Exception e) {
//            procesoOK = false;
//        }
//
//        // Devolución Semáforo
//        return procesoOK;
//    }
//
    // Linea texto > Fichero (Nuevo)
    public static final boolean escribirLinea(String dato, String fichero) {
        return UtilesFicheros.escribirLinea(dato, fichero, false);
    }

    // Linea texto > Fichero (Nuevo)
    public static final boolean escribirLinea(String dato) {
        return UtilesFicheros.escribirLinea(dato, DEF_FICHERO, false);
    }

    // Linea texto + Semáforo > Fichero
    public static final boolean escribirLinea(String dato, String fichero, boolean append) {
        // Semáforo
        boolean procesoOK = true;

        // Proceso Escritura
        try (FileWriter fw = new FileWriter(fichero, append)) {
            fw.append(dato + System.getProperty("line.separator"));
        } catch (Exception e) {
            procesoOK = false;
        }

        // Devolución Semáforo
        return procesoOK;
    }

    // Linea texto + Semáforo > Fichero
    public static final boolean escribirLinea(String dato, boolean append) {
        return UtilesFicheros.escribirLinea(dato, DEF_FICHERO, append);
    }

    // Array Lineas de Texto > Fichero
    public static final boolean escribirArray(String[] array, String fichero) {
        // Semáforo
        boolean procesoOK = true;

        // Proceso Escritura
        try (FileWriter fw = new FileWriter(fichero)) {
            for (String linea : array) {
                fw.append(linea + System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            procesoOK = false;
        }

        // Devolución Semáforo
        return procesoOK;
    }

    // Array Lineas de Texto > Fichero
    public static final boolean escribirArray(String[] array) {
        return UtilesFicheros.escribirArray(array, DEF_FICHERO);
    }

    // Fichero > Linea de texto
    public static final String leerLinea(String fichero) {
        // Referencia Linea
        String linea;

        // Proceso Lectura
        try (FileReader fr = new FileReader(fichero); //
                 BufferedReader br = new BufferedReader(fr)) {
            linea = br.readLine();
        } catch (Exception e) {
            linea = null;
        }

        // Devolución Linea
        return linea;
    }

    // Fichero > Linea de texto
    public static final String leerLinea() {
        return leerLinea(DEF_FICHERO);
    }

    // Fichero > Múltiples Lineas de texto
    public static final String leerTexto(String fichero) {
        // Referencia texto
        String texto;

        // Almacén de Lineas de Texto
        StringBuilder buffer = new StringBuilder();

        // Proceso Lectura
        try (
                FileReader fr = new FileReader(fichero); BufferedReader br = new BufferedReader(fr)) {
            // Referencia Linea de Texto Actual
            String linea;

            // Bucle Recorrido Lineas Fichero
            do {
                // Lectura Linea Actual
                linea = br.readLine();

                // Incorporación Linea Actual
                if (linea != null) {
                    if (buffer.length() > 0) {
                        buffer.append(System.getProperty("line.separator"));
                    }
                    buffer.append(linea);
                }
            } while (linea != null);

            // Extracción del Texto Acumulado
            texto = buffer.toString();
        } catch (Exception e) {
            texto = null;
        }

        // Devolución Linea
        return texto;
    }

    // Fichero > Múltiples Lineas de texto
    public static final String leerTexto() {
        return leerTexto(DEF_FICHERO);
    }

    // Fichero > Array Lineas de texto
    public static final String[] leerArray(String fichero) {
        // Referencia Array
        String[] array;

        // Lista Lineas de Texto
        List<String> lista = new ArrayList<>();

        // Proceso Lectura
        try (
                FileReader fr = new FileReader(fichero); BufferedReader br = new BufferedReader(fr)) {
            // Referencia Linea de Texto Actual
            String linea;

            // Bucle Recorrido Lineas Fichero
            do {
                // Lectura Linea Actual
                linea = br.readLine();

                // Incorporación Linea Actual
                if (linea != null) {
                    lista.add(linea);
                }
            } while (linea != null);

            // Extracción del Texto Acumulado
            array = lista.toArray(new String[lista.size()]);
        } catch (Exception e) {
            array = null;
        }

        // Devolución Linea
        return array;
    }

    // Fichero > Array Lineas de texto
    public static final String[] leerArray() {
        return UtilesFicheros.leerArray(DEF_FICHERO);
    }
}
