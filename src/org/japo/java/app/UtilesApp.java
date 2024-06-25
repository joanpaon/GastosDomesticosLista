/*
 * Copyright 2024 José A. Pacheco - japolabs@gmail.com.
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
package org.japo.java.app;

import java.util.List;
import org.japo.java.entities.gasto.Gasto;
import org.japo.java.entities.gasto.UtilesGasto;
import org.japo.java.libraries.UtilesEntrada;

/**
 *
 * @author José A. Pacheco - japolabs@gmail.com
 */
public final class UtilesApp {

    // Mensajes de Error
    public static final String MSG_ERR = "ERROR: Entrada incorrecta";

    // Menú Mantenimiento
    public static final String OPC_MANT = "123450";
    public static final String MNU_MANT = """
            MENÚ DE MANTENIMIENTO
            =====================
            [ 1 ] Altas
            [ 2 ] Bajas
            [ 3 ] Consultas
            [ 4 ] Modificaciones
            [ 5 ] Listados
            ---
            [ 0 ] Menú Anterior
            -
            Opción ...:\s""";

    // Menú Listados
    public static final String OPC_LIST = "12345670";
    public static final String MNU_LIST = """
            MENÚ DE LISTADOS
            ================
            [ 1 ] Listado por Referencia
            [ 2 ] Listado por Nombre
            [ 3 ] Listado por Fecha
            [ 4 ] Listado por Importe
            [ 5 ] Listado por Forma de Pago
            [ 6 ] Listado por Partida
            [ 7 ] Listado por Comercio
            ---
            [ 0 ] Menú Anterior
            -
            Opción ...:\s""";

    // Mensaje Despedida
    public static final String MSG_BYE = """
        Programa Terminado por el Usuario
        Fin de la Sesión de Trabajo
        Gracias por utilizar esta Aplicación""";

    // Constructor Predeterminado ( Oculto )
    private UtilesApp() {
    }

    // Menú Mantenimiento
    public static final void lanzarMenuMant(List<Gasto> lista) {
        // Referencia
        char opcion;

        // Gestión Menú
        do {
            // Interacción
            opcion = UtilesEntrada.leerCaracter(MNU_MANT, MSG_ERR, OPC_MANT);

            // Separador
            System.out.println("---");

            // Procesado
            switch (opcion) {
                case '1' ->
                    UtilesGasto.crear(lista);
                case '2' ->
                    UtilesGasto.borrar(lista);
                case '3' ->
                    UtilesGasto.consultar(lista);
                case '4' ->
                    UtilesGasto.cambiar(lista);
                case '5' ->
                    lanzarMenuList(lista);
            }
        } while (opcion != '0');
    }

    // Listados
    public static final void lanzarMenuList(List<Gasto> lista) {
        // Referencia
        char opcion;

        // Gestión Menu
        do {
            // Interacción
            opcion = UtilesEntrada.leerCaracter(MNU_LIST, MSG_ERR, OPC_LIST);

            // Separador
            System.out.println("---");

            // Procesado
            switch (opcion) {
                case '1' ->
                    UtilesGasto.listarIdd(lista);
                case '2' ->
                    UtilesGasto.listarNom(lista);
                case '3' ->
                    UtilesGasto.listarFec(lista);
                case '4' ->
                    UtilesGasto.listarImp(lista);
                case '5' ->
                    UtilesGasto.listarFrp(lista);
                case '6' ->
                    UtilesGasto.listarPar(lista);
                case '7' ->
                    UtilesGasto.listarCom(lista);
            }
        } while (opcion != '0');
    }
}
