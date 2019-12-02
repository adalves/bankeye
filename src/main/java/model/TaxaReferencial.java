/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Matheus
 */
public class TaxaReferencial {    
    private int id = -1;
    private double vlTaxa;
    private String dtVigencia;

    public TaxaReferencial(int id, double vlTaxa, String dtVigencia) {
        this.id = id;
        this.vlTaxa = vlTaxa;
        this.dtVigencia = dtVigencia;
    }

    public TaxaReferencial() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVlTaxa() {
        return vlTaxa;
    }

    public void setVlTaxa(double vlTaxa) {
        this.vlTaxa = vlTaxa;
    }

    public String getDtVigencia() {
        return dtVigencia;
    }

    public void setDtVigencia(String dtVigencia) {
        this.dtVigencia = dtVigencia;
    }
    
    public static final DBObject toDBObject(TaxaReferencial taxa) {
        BasicDBObject result = new BasicDBObject("vl_taxa", taxa.getVlTaxa())
                .append("dt_vigencia", taxa.getDtVigencia());
        
        if (taxa.getId() != -1) {
            result.append("_id", taxa.getId());
        }
        
        return result;
    }
}
