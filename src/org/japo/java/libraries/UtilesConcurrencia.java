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

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesConcurrencia {

    // Valores por Defecto
    private static final int DEF_PUERTO_BLOQUEO = 54321;

    // Constructor Inaccesible
    public UtilesConcurrencia() {
    }

    // Número de Puerto > Activar Instancia Única - Singleton
    public static final boolean activarInstancia(int puerto) {
        // Semaforo Estado
        boolean instanciaOK = false;

        try {
            // Abre un ServerSocket al puerto de bloqueo
            ServerSocket ss = new ServerSocket(puerto);

            // Marca Semáforo
            instanciaOK = true;
        } catch (IOException e) {
            System.out.println("ERROR: Activación ejecución única");
        }

        // Devuelve Estado
        return instanciaOK;
    }

    // Puerto de Bloqueo por Defecto > Activar Instancia Única - Singleton
    public static final boolean activarInstancia() {
        return activarInstancia(DEF_PUERTO_BLOQUEO);
    }

    // Activa Instancia Única
    public static final boolean activarInstancia(String txtPuerto) {
        // Semaforo Estado
        boolean instanciaOK = false;

        try {
            // Conversión numérica
            int puerto = Integer.parseInt(txtPuerto);

            // Abre un ServerSocket al puerto de bloqueo
            instanciaOK = activarInstancia(puerto);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Activación ejecución única");
        }

        // Devuelve Estado
        return instanciaOK;
    }

    // Activa Instancia Única
    public static final boolean activarInstancia(Properties prp) {
        // Obtener dato
        String txtPuerto = prp.getProperty("puerto_bloqueo", DEF_PUERTO_BLOQUEO + "");

        // Abre un ServerSocket al puerto de bloqueo
        return activarInstancia(txtPuerto);
    }

}
