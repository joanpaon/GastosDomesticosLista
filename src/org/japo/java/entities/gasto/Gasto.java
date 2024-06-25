/*
 * Copyright 2024 JAPO Labs - japolabs@gmail.com.
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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public class Gasto implements Serializable {

    private Long idd;
    private String nom;
    private LocalDate fec;
    private Double imp;
    private String frp;
    private String par;
    private String com;

    public Gasto() {
    }

    public Gasto(Long idd, String nom, LocalDate fec, Double imp,
            String frp, String par, String com) {
        this.idd = idd;
        this.nom = nom;
        this.fec = fec;
        this.imp = imp;
        this.frp = frp;
        this.par = par;
        this.com = com;
    }

    public Long getIdd() {
        return idd;
    }

    public void setIdd(Long idd) {
        this.idd = idd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getFec() {
        return fec;
    }

    public void setFec(LocalDate fec) {
        this.fec = fec;
    }

    public Double getImp() {
        return imp;
    }

    public void setImp(Double imp) {
        this.imp = imp;
    }

    public String getFrp() {
        return frp;
    }

    public void setFrp(String frp) {
        this.frp = frp;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, 
                "%5d %-30s %s %7.2f %-20s %-20s %-20s",
                idd, nom, fec, imp, frp, par, com);
    }

}
