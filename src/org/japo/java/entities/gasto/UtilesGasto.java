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
package org.japo.java.entities.gasto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import static org.japo.java.libraries.UtilesCSV.FICHERO_CSV;
import static org.japo.java.libraries.UtilesCSV.MSG_ERR_CSV;
import static org.japo.java.libraries.UtilesCSV.MSG_ERR_LIN;
import static org.japo.java.libraries.UtilesCSV.REG_SEP_ESC;
import static org.japo.java.libraries.UtilesCSV.REG_SEP_LEC;
import org.japo.java.libraries.UtilesEntrada;
import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author José A. Pacheco - japolabs@gmail.com
 */
public final class UtilesGasto {

    // Cabeceras Entrada y Salida
    public static final String CAB_ENT = """
            Entrada de Gasto
            ================""";
    public static final String CAB_SAL = """
            Salida de Gasto
            ===============""";

    // Cabeceras Mantenimiento
    public static final String CAB_ALT = """
            Alta de Gasto
            =============""";
    public static final String CAB_BAJ = """
            Baja de Gasto
            =============""";
    public static final String CAB_CON = """
            Consulta de Gasto
            =================""";
    public static final String CAB_MOD = """
            Modificación de Gasto
            =====================""";
    public static final String CAB_LIS = """
         Idd  Nombre                         Fecha      Importe Forma de Pago        Partida             Comercio
        ----- ------------------------------ ---------- ------- -------------------- -------------------- --------------------""";
    public static final String CAB_LIS_IDD = """
            Listado de Gastos por Referencia
            ================================""";
    public static final String CAB_LIS_NOM = """
            Listado de Gastos por Nombre
            ============================""";
    public static final String CAB_LIS_FEC = """
            Listado de Gastos por Fecha
            ===========================""";
    public static final String CAB_LIS_IMP = """
            Listado de Gastos por Importe
            =============================""";
    public static final String CAB_LIS_FRP = """
            Listado de Gastos por Forma de Pago
            ===================================""";
    public static final String CAB_LIS_PAR = """
            Listado de Gastos por Partida
            ==============================""";
    public static final String CAB_LIS_COM = """
            Listado de Gastos por Comercio
            ==============================""";

    // Mensajes de Usuario por Campo
    public static final String MSG_FLD_IDD = "Id ............: ";
    public static final String MSG_FLD_NOM = "Nombre ........: ";
    public static final String MSG_FLD_FEC = "Fecha .........: ";
    public static final String MSG_FLD_IMP = "Importe .......: ";
    public static final String MSG_FLD_FRP = "Forma de Pago .: ";
    public static final String MSG_FLD_PAR = "Partida .......: ";
    public static final String MSG_FLD_COM = "Comercio ......: ";

    // Mensajes de Error
    public static final String MSG_ERR = "ERROR: Entrada incorrecta";
    public static final String MSG_ERR_IDD = "ERROR: Id incorrecto";
    public static final String MSG_ERR_NOM = "ERROR: Nombre incorrecto";
    public static final String MSG_ERR_FEC = "ERROR: Fecha incorrecta";
    public static final String MSG_ERR_IMP = "ERROR: Importe incorrecto";
    public static final String MSG_ERR_FRP = "ERROR: Forma de Pago incorrecta";
    public static final String MSG_ERR_PAR = "ERROR: Partida incorrecta";
    public static final String MSG_ERR_COM = "ERROR: Comercio incorrecto";

    // Mensajes de Aviso
    public static final String MSG_FND_NO = "ATENCIÓN: Gasto NO existe";
    public static final String MSG_FND_SI = "ATENCIÓN: Gasto YA existe";
    public static final String MSG_DEL_OK = "ATENCIÓN: Gasto ELIMINADO definitivamente";

    // Objetos Comparator
    public static final Comparator<Gasto> CMP_IDD
            = (a1, a2) -> a1.getIdd().compareTo(a2.getIdd());
    public static final Comparator<Gasto> CMP_NOM
            = (a1, a2) -> a1.getNom().compareTo(a2.getNom());
    public static final Comparator<Gasto> CMP_FEC
            = (a1, a2) -> a1.getFec().compareTo(a2.getFec());
    public static final Comparator<Gasto> CMP_IMP
            = (a1, a2) -> a1.getImp().compareTo(a2.getImp());
    public static final Comparator<Gasto> CMP_FRP
            = (a1, a2) -> a1.getFrp().compareTo(a2.getFrp());
    public static final Comparator<Gasto> CMP_PAR
            = (a1, a2) -> a1.getPar().compareTo(a2.getPar());
    public static final Comparator<Gasto> CMP_COM
            = (a1, a2) -> a1.getCom().compareTo(a2.getCom());

    // Valores Predeterminados
    public static final Long DEF_IDD = 0L;
    public static final String DEF_NOM = "No Definido";
    public static final LocalDate DEF_FEC = LocalDate.of(1970, 1, 1);
    public static final Double DEF_IMP = 0.0;
    public static final String DEF_FRP = "No Definida";
    public static final String DEF_PAR = "No Definida";
    public static final String DEF_COM = "No Definido";

    // Expresiones Regulares
    public static final String REG_IDD = "0|[1-9]\\d{0,4}";
    public static final String REG_NOM = "[\\wÁÉÍÓÚÜÑáéíóúüñ \\.\\-\\,()]{2,20}";
    public static final String REG_FEC = ".*";
    public static final String REG_IMP = "(0|[1-9]\\d?)\\.(0|[1-9]\\d?|\\d[1-9])";
    public static final String REG_FRP = "[\\wÁÉÍÓÚÜÑáéíóúüñ \\.\\-\\,()]{2,20}";
    public static final String REG_PAR = "[\\wÁÉÍÓÚÜÑáéíóúüñ \\.\\-\\,()]{2,20}";
    public static final String REG_COM = "[\\wÁÉÍÓÚÜÑáéíóúüñ \\.\\-\\,()]{2,20}";

    // Valores Límite - Stock
    public static final int STK_MIN = 0;
    public static final int STK_MAX = 99;

    private UtilesGasto() {
    }

    // Validación - Id
    public static final boolean validarId(Long id) {
        return UtilesValidacion.validar(id.toString(), REG_IDD);
    }

    // Validación - Nombre
    public static final boolean validarNombre(String nombre) {
        return UtilesValidacion.validar(nombre, REG_NOM);
    }

    // Validación - Fecha
    public static final boolean validarFecha(LocalDate fecha) {
        return UtilesValidacion.validar(fecha.toString(), REG_FEC);
    }

    // Validación - Importe
    public static final boolean validarImporte(Double importe) {
        return UtilesValidacion.validar(importe.toString(), REG_IMP);
    }

    // Validación - Forma de Pago
    public static final boolean validarFormaPago(String formaPago) {
        return UtilesValidacion.validar(formaPago, REG_FRP);
    }

    // Validación - Partida
    public static final boolean validarPartida(String partida) {
        return UtilesValidacion.validar(partida, REG_PAR);
    }

    // Validación - Comercio
    public static final boolean validarComercio(String comercio) {
        return UtilesValidacion.validar(comercio, REG_COM);
    }

    // Lista de Gastos + Id > Posición Gasto
    public static final int buscarId(List<Gasto> lista, Long id) {
        // Ordenar Lista - Id
        lista.sort(CMP_IDD);

        // Referencia
        int index;

        // Búsqueda
        try {
            // Objeto de Búsqueda
            Gasto a = generarGastoId(id);

            // Buscar
            index = Collections.binarySearch(lista, a, CMP_IDD);
        } catch (Exception e) {
            // No Encontrado
            index = -1;
        }

        // Retorno
        return index;
    }

    // Gasto > Consola - Formato Registro
    public static final void mostrarGasto(Gasto g) {
        System.out.printf("%s%d%n", MSG_FLD_IDD, g.getIdd());
        System.out.printf("%s%s%n", MSG_FLD_NOM, g.getNom());
        System.out.printf("%s%s%n", MSG_FLD_FEC, g.getFec());
        System.out.printf(Locale.ENGLISH, "%s%.2f%n", MSG_FLD_IMP, g.getImp());
        System.out.printf("%s%s%n", MSG_FLD_FRP, g.getFrp());
        System.out.printf("%s%s%n", MSG_FLD_PAR, g.getPar());
        System.out.printf("%s%s%n", MSG_FLD_COM, g.getCom());
    }

    private static Gasto generarGastoId(Long id) throws Exception {
        return new Gasto(
                id,
                DEF_NOM,
                DEF_FEC,
                DEF_IMP,
                DEF_FRP,
                DEF_PAR,
                DEF_COM);
    }

    // Listado - Id
    public static final void listarIdd(List<Gasto> lista) {
        // Ordenar - Id
        lista.sort(CMP_IDD);

        // Cabecera - Id
        System.out.println(CAB_LIS_IDD);

        // Listar
        listar(lista);
    }

    // Listado - Nombre
    public static final void listarNom(List<Gasto> lista) {
        // Ordenar - Nombre
        lista.sort(CMP_NOM);

        // Cabecera - Nombre
        System.out.println(CAB_LIS_NOM);

        // Listar
        listar(lista);
    }

    // Listado - Fecha
    public static final void listarFec(List<Gasto> lista) {
        // Ordenar - Fecha
        lista.sort(CMP_FEC);

        // Cabecera - Fecha
        System.out.println(CAB_LIS_FEC);

        // Listar
        listar(lista);
    }

    // Listado - Importe
    public static final void listarImp(List<Gasto> lista) {
        // Ordenar - Importe
        lista.sort(CMP_IMP);

        // Cabecera - Importe
        System.out.println(CAB_LIS_IMP);

        // Listar
        listar(lista);
    }

    // Listado - Forma de Pago
    public static final void listarFrp(List<Gasto> lista) {
        // Ordenar - Forma de Pago
        lista.sort(CMP_FRP);

        // Cabecera - Forma de Pago
        System.out.println(CAB_LIS_FRP);

        // Listar
        listar(lista);
    }

    // Listado - Partida
    public static final void listarPar(List<Gasto> lista) {
        // Ordenar - Partida
        lista.sort(CMP_PAR);

        // Cabecera - Partida
        System.out.println(CAB_LIS_PAR);

        // Listar
        listar(lista);
    }

    // Listado - Comercio
    public static final void listarCom(List<Gasto> lista) {
        // Ordenar - Comercio
        lista.sort(CMP_COM);

        // Cabecera - Comercio
        System.out.println(CAB_LIS_COM);

        // Listar
        listar(lista);
    }

    // Lista Gastos > Listado Consola
    public static final void listar(List<Gasto> lista) {
        try {
            // Cabecera
            System.out.println(CAB_LIS);

            // Listado
            for (Gasto a : lista) {
                System.out.println(a);
            }
        } catch (Exception e) {
            System.out.println("AVISO: Nada que listar");
        } finally {
            // Pausa
            UtilesEntrada.hacerPausa();
        }
    }

    // Altas
    public static final void crear(List<Gasto> lista) {
        // Cabecera
        System.out.println(CAB_ALT);

        // Entrada Referencia Gasto
        Long id = leerId();

        // Separación
        System.out.println("---");

        // Posición Gasto
        int index = buscarId(lista, id);

        // Procesar Posición
        if (index > -1) {
            // Gasto YA Existe
            System.out.println(MSG_FND_SI);
        } else {
            // Consola > Nuevos Valores Campos
            String nombre = leerNombre();
            LocalDate fecha = leerFecha();
            Double importe = leerImporte();
            String formaPago = leerFormaPago();
            String partida = leerPartida();
            String comercio = leerComercio();

            // Separación
            System.out.println("---");

            // Crea Gasto
            Gasto a = new Gasto();
            a.setIdd(id);
            a.setNom(nombre);
            a.setFec(fecha);
            a.setImp(importe);
            a.setFrp(formaPago);
            a.setPar(partida);
            a.setCom(comercio);

            // Gasto > Lista
            lista.add(a);

            // Mostrar Gasto
            mostrarGasto(a);
        }

        // Pausa
        UtilesEntrada.hacerPausa();
    }

    // Bajas
    public static final void borrar(List<Gasto> lista) {
        // Cabecera
        System.out.println(CAB_BAJ);

        // Consola > Referencia Gasto
        Long id = leerId();

        // Separación
        System.out.println("---");

        // Búsqueda Gasto
        int index = buscarId(lista, id);

        // Procesar Búsqueda
        if (index <= -1) {
            // Gasto NO Existe
            System.out.println(MSG_FND_NO);
        } else {
            // Mostrar Gasto Encontrado
            mostrarGasto(lista.get(index));

            // TODO: Confirmar Eliminación Gasto
            // Eliminando Gasto
            lista.remove(index);

            // Mensaje Gasto Eliminado
            System.out.println(MSG_DEL_OK);
        }

        // Pausa
        UtilesEntrada.hacerPausa();
    }

    // Consultas
    public static final void consultar(List<Gasto> lista) {
        // Cabecera
        System.out.println(CAB_CON);

        // Entrada Referencia Gasto
        Long id = leerId();

        // Separación
        System.out.println("---");

        // Búsqueda Gasto
        int index = buscarId(lista, id);

        // Procesar Búsqueda
        if (index <= -1) {
            // Gasto NO Existe
            System.out.println(MSG_FND_NO);
        } else {
            // Mostrar Gasto Encontrado
            mostrarGasto(lista.get(index));
        }

        // Pausa
        UtilesEntrada.hacerPausa();
    }

    // Modificaciones
    public static final void cambiar(List<Gasto> lista) {
        // Cabecera
        System.out.println(CAB_MOD);

        // Entrada Referencia Gasto
        Long id = leerId();

        // Separación
        System.out.println("---");

        // Posición Gasto
        int index = buscarId(lista, id);

        // Procesar Posición
        if (index <= -1) {
            // Gasto NO Existe
            System.out.println(MSG_FND_NO);
        } else {
            // Lista + Posición > Actículo
            Gasto a = lista.get(index);

            // Mostrar Gasto
            mostrarGasto(a);

            // Separación
            System.out.println("---");

            // Consola > Nuevos Valores Campos
            String nombre = leerNombre(a.getNom());
            LocalDate fecha = leerFecha(a.getFec());
            Double importe = UtilesGasto.leerImporte(a.getImp());
            String formaPago = leerFormaPago(a.getFrp());
            String partida = leerPartida(a.getPar());
            String comercio = leerComercio(a.getCom());

            // Separación
            System.out.println("---");

            // Actualiza Gasto
            a.setNom(nombre);
            a.setFec(fecha);
            a.setImp(importe);
            a.setFrp(formaPago);
            a.setPar(partida);
            a.setCom(comercio);

            // Mostrar Gasto
            mostrarGasto(a);
        }

        // Pausa
        UtilesEntrada.hacerPausa();
    }

    // Entrada - Referencia
    public static final Long leerId() {
        return (long) UtilesEntrada.leerEntero(MSG_FLD_IDD, MSG_ERR, REG_IDD);
    }

    // Entrada - Nombre
    public static final String leerNombre() {
        return UtilesEntrada.leerTexto(MSG_FLD_NOM, MSG_ERR, REG_NOM);
    }

    // Entrada - Nombre (Sugerencia)
    public static final String leerNombre(String dato) {
        return UtilesEntrada.leerTexto(MSG_FLD_NOM, MSG_ERR, REG_NOM, dato);
    }

    // Entrada - Fecha
    public static final LocalDate leerFecha() {
        return UtilesEntrada.leerFechaLocal(MSG_FLD_FEC, MSG_ERR);
    }

    // Entrada - Fecha (Sugerencia)
    public static final LocalDate leerFecha(LocalDate dato) {
        return UtilesEntrada.leerFechaLocal(MSG_FLD_FEC, MSG_ERR, dato);
    }

    // Entrada - Importe
    public static final double leerImporte() {
        return UtilesEntrada.leerReal(MSG_FLD_IMP, MSG_ERR, REG_IMP);
    }

    // Entrada - Importe (Sugerencia)
    public static final double leerImporte(Double sugerencia) {
        return UtilesEntrada.leerReal(MSG_FLD_IMP, MSG_ERR, REG_IMP, sugerencia);
    }

    // Entrada - Forma de Pago
    public static final String leerFormaPago() {
        return UtilesEntrada.leerTexto(MSG_FLD_FRP, MSG_ERR, REG_FRP);
    }

    // Entrada - Forma de Pago (Sugerencia)
    public static final String leerFormaPago(String sugerencia) {
        return UtilesEntrada.leerTexto(MSG_FLD_FRP, MSG_ERR, REG_FRP, sugerencia);
    }

    // Entrada - Partida
    public static final String leerPartida() {
        return UtilesEntrada.leerTexto(MSG_FLD_FRP, MSG_ERR, REG_FRP);
    }

    // Entrada - Partida (Sugerencia)
    public static final String leerPartida(String sugerencia) {
        return UtilesEntrada.leerTexto(MSG_FLD_PAR, MSG_ERR, REG_PAR, sugerencia);
    }

    // Entrada - Comercio
    public static final String leerComercio() {
        return UtilesEntrada.leerTexto(MSG_FLD_COM, MSG_ERR, REG_COM);
    }

    // Entrada - Comercio (Sugerencia)
    public static final String leerComercio(String sugerencia) {
        return UtilesEntrada.leerTexto(MSG_FLD_COM, MSG_ERR, REG_COM, sugerencia);
    }

    // Fichero CSV > Lista Gastos
    public static final void importarDatos(List<Gasto> lista, String fichero) {
        try (FileReader fr = new FileReader(fichero); //
                 BufferedReader br = new BufferedReader(fr)) {
            // Referencia Linea Actual
            String linea;

            // Bucle de Acceso al Fichero
            do {
                // Fichero > Linea
                linea = br.readLine();

                try {
                    // Validación de la linea
                    if (linea != null) {
                        // Linea > Tokens
                        String[] tokens = linea.split(REG_SEP_LEC);

                        // Tokens > Datos
                        Long id = Long.valueOf(tokens[0]);
                        String nombre = tokens[1];
                        LocalDate fecha = LocalDate.parse(tokens[2]);
                        Double importe = Double.valueOf(tokens[3]);
                        String formaPago = tokens[4];
                        String partida = tokens[5];
                        String comercio = tokens[6];

                        // Datos > Gasto
                        Gasto g = new Gasto(id, nombre, fecha, importe,
                                formaPago, partida, comercio);

                        // Gasto > Lista
                        lista.add(g);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(MSG_ERR_LIN);
                }
            } while (linea != null);
        } catch (Exception e) {
            System.out.println(MSG_ERR_CSV);
        }
    }

    // Fichero CSV > Lista Artículos ( Ejemplo )
    public static final void importarDatos(List<Gasto> lista) {
        importarDatos(lista, FICHERO_CSV);
    }

    // Lista Artículos > Fichero CSV
    public static final void exportarDatos(List<Gasto> lista, String fichero) {
        try (FileWriter fw = new FileWriter(fichero)) {
            for (Gasto a : lista) {
                // Campos > Linea
                StringBuilder linea = new StringBuilder();
                linea.append(a.getIdd()).append(REG_SEP_ESC);
                linea.append(a.getNom()).append(REG_SEP_ESC);
                linea.append(a.getFec()).append(REG_SEP_ESC);
                linea.append(a.getImp()).append(REG_SEP_ESC);
                linea.append(a.getFrp()).append(REG_SEP_ESC);
                linea.append(a.getPar()).append(REG_SEP_ESC);
                linea.append(a.getCom()).append("\n");

                // Linea > Fichero
                fw.write(linea.toString());
            }
        } catch (Exception e) {
            System.out.println(MSG_ERR_CSV);
        }
    }

    // Lista Artículos > Fichero CSV ( Ejemplo )
    public static final void exportarDatos(List<Gasto> lista) {
        exportarDatos(lista, FICHERO_CSV);
    }

}
