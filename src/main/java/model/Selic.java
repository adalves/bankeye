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
public class Selic {
    private int id = -1;
    private double txSelic;
    private double txSelicAno;
    private String dtInicioVigencia;
    private String dtFimVigencia;

    public Selic(int id, double txSelic, double txSelicAno, String dtInicioVigencia, String dtFimVigencia) {
        this.id = id;
        this.txSelic = txSelic;
        this.txSelicAno = txSelicAno;
        this.dtInicioVigencia = dtInicioVigencia;
        this.dtFimVigencia = dtFimVigencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTxSelic() {
        return txSelic;
    }

    public void setTxSelic(double txSelic) {
        this.txSelic = txSelic;
    }

    public double getTxSelicAno() {
        return txSelicAno;
    }

    public void setTxSelicAno(double txSelicAno) {
        this.txSelicAno = txSelicAno;
    }

    public String getDtInicioVigencia() {
        return dtInicioVigencia;
    }

    public void setDtInicioVigencia(String dtInicioVigencia) {
        this.dtInicioVigencia = dtInicioVigencia;
    }

    public String getDtFimVigencia() {
        return dtFimVigencia;
    }

    public void setDtFimVigencia(String dtFimVigencia) {
        this.dtFimVigencia = dtFimVigencia;
    }
    
    public static DBObject toDBObject(Selic selic) {
        BasicDBObject result = new BasicDBObject("tx_selic", selic.getTxSelic())
                .append("tx_selic_ano", selic.getTxSelicAno())
                .append("dt_inicio_vigencia", selic.getDtInicioVigencia())
                .append("dt_fim_vigencia", selic.getDtFimVigencia());
        
        if (selic.getId() != -1) {
            result.append("_id", selic.getId());
        }
        
        return result;
    }
}
