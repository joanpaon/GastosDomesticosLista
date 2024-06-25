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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesPRP {

    // Valores por Defecto
    private static final String DEF_FICHERO_PRP = "app.properties";
    private static final String DEF_FICHERO_XML = "app.xml";
    private static final String DEF_RECURSO_PAK = "config";
    private static final String DEF_RECURSO_PRP = DEF_RECURSO_PAK + "/" + DEF_FICHERO_PRP;

    // Constructor Inaccesible
    public UtilesPRP() {
    }

    // Fichero Propiedades > Objeto Propiedades
    public static final Properties importarPropiedadesFichero(String fichero) {
        // Objeto de Propiedades Vacio
        Properties prp = new Properties();

        // Cargar Fichero de Propiedades
        try (FileReader fr = new FileReader(fichero)) {
            prp.load(fr);
        } catch (Exception e) {
            System.out.println("ERROR: Acceso al fichero de propiedades " + fichero);
        }

        // Devolver Propiedades
        return prp;
    }

    // Fichero Propiedades (Por defecto) > Propiedades
    public static final Properties importarPropiedadesFichero() {
        return UtilesPRP.importarPropiedadesFichero(DEF_FICHERO_PRP);
    }

    // Recurso Propiedades > Objeto Propiedades
    public static final Properties importarPropiedadesRecurso(String recurso) {
        // Objeto de Propiedades Vacio
        Properties prp = new Properties();

        // Cargar Fichero de Propiedades
        try (InputStream is = ClassLoader.getSystemResourceAsStream(recurso)) {
            prp.load(is);
        } catch (Exception e) {
            System.out.println("ERROR: Acceso al recurso de propiedades " + recurso);
        }

        // Devolver Propiedades
        return prp;
    }

    // Recurso Propiedades ( Predefinido ) > Objeto Propiedades
    public static final Properties importarPropiedadesRecurso() {
        return importarPropiedadesRecurso(DEF_RECURSO_PRP);
    }

    // Fichero Propiedades XML (Por defecto) > Propiedades
    public static final Properties importarPropiedadesFicheroXML() {
        return importarPropiedadesFicheroXML(DEF_FICHERO_XML);
    }

    // Fichero Propiedades XML > Objeto Propiedades
    public static final Properties importarPropiedadesFicheroXML(String fichero) {
        // Objeto de Propiedades Vacio
        Properties prp = new Properties();

        // Cargar Fichero de Propiedades
        try (FileInputStream fisXml = new FileInputStream(fichero)) {
            // Carga las propiedades
            prp.loadFromXML(fisXml);
        } catch (Exception e) {
            System.out.println("ERROR: Acceso al fichero " + fichero);
        }

        // Devolver Propiedades
        return prp;
    }

    // Fichero de Propiedades (Defecto) + Recurso de Propiedades (Defecto) > Propiedades
    public static final Properties importarPropiedades() {
        Properties prp = new Properties();
        prp.putAll(importarPropiedadesFichero());
        prp.putAll(importarPropiedadesRecurso());
        return prp;
    }

    // Propiedades > Fichero
    public static final boolean exportarPropiedadesFichero(Properties prp, String fichero) {
        // Semáforo Estado
        boolean procesoOK = false;

        // Proceso de salvaguarda de propiedades
        try (FileWriter fw = new FileWriter(fichero)) {
            // Guarda las propiedades
            prp.store(fw, null);

            // Proceso OK
            procesoOK = true;
        } catch (Exception e) {
            // Mensaje de error
            System.out.println("ERROR: Acceso al fichero " + fichero);
        }

        // Devuelve Estado
        return procesoOK;
    }

    // Propiedades > Fichero (Por defecto)
    public static final boolean exportarPropiedadesFichero(Properties prp) {
        return exportarPropiedadesFichero(prp, DEF_FICHERO_PRP);
    }

    // Propiedades > Fichero XML
    public static final boolean exportarPropiedadesXML(Properties prp, String fichero) {
        // Semáforo Estado
        boolean procesoOK = false;

        // Proceso de salvaguarda de propiedades
        try (FileOutputStream fosXml = new FileOutputStream(fichero)) {
            // Guarda las propiedades
            prp.storeToXML(fosXml, null);

            // Proceso OK
            procesoOK = true;
        } catch (Exception e) {
            // Mensaje de error
            System.out.println("ERROR: Acceso al fichero " + fichero);
        }

        // Devuelve Estado
        return procesoOK;
    }

    // Propiedades > Fichero XML (Por defecto)
    public static final boolean exportarPropiedadesXML(Properties prp) {
        return exportarPropiedadesXML(prp, DEF_FICHERO_PRP);
    }
}
