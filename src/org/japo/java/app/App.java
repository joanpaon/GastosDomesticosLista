/*
 * Copyright 2023 José A. Pacheco - japolabs@gmail.com.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.japo.java.entities.gasto.Gasto;
import org.japo.java.entities.gasto.UtilesGasto;

/**
 *
 * @author José A. Pacheco - japolabs@gmail.com
 */
public final class App {

    // Inventario - Colección
    private final List<Gasto> LISTA = new ArrayList<>();

    // Propiedades
    private final Properties prp;

    // Constructor Parametrizado
    public App(Properties prp) {
        // Propiedades de la Apicación
        this.prp = prp;

        // Fichero CSV > Lista de Artículos
        UtilesGasto.importarDatos(LISTA);
    }

    // Lógica de negocio
    public final void launchApp() {
        // Menú Principal 
        UtilesApp.lanzarMenuMant(LISTA);

        // ExportarCSV
        UtilesGasto.exportarDatos(LISTA);
    }
}
