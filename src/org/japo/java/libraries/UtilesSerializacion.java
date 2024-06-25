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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesSerializacion {

    // Constructor Inaccesible
    private UtilesSerializacion() {
    }

    // Objeto > Serialización Binaria
    public static final void serializarBin(Object objeto, String archivo) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(archivo); //
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // Escribe el objeto
            oos.writeObject(objeto);

            // Vacia Buffers
            oos.flush();
        }
    }

    // Objeto > Deserialización Binaria
    public static final Object deserializarBin(String archivo)
            throws Exception {

        // Referencia Objeto
        Object objeto;

        try (FileInputStream fis = new FileInputStream(archivo); //
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
            objeto = ois.readObject();
        }

        return objeto;
    }

    // Objeto > Serialización XML
    public static final void serializarXML(Object objeto, String archivo)
            throws Exception {
        try (FileOutputStream fos = new FileOutputStream(archivo); //
                 XMLEncoder salida = new XMLEncoder(fos)) {
            // Escribe el objeto
            salida.writeObject(objeto);

            // Vacia Buffers
            salida.flush();
        }
    }

    // Objeto > Deserialización XML
    public static final Object deserializarXML(String archivo)
            throws Exception {

        // Referencia Objeto
        Object objeto;

        try (FileInputStream fis = new FileInputStream(archivo); //
                 XMLDecoder entrada = new XMLDecoder(fis)) {
            objeto = entrada.readObject();
        }

        return objeto;
    }
}
