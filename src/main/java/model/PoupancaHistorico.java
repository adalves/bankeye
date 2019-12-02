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
public class PoupancaHistorico {

    private int id;
    private double valorTotal;
    private String mes;

    public PoupancaHistorico(int id, double vlTotalPoupanca, String mesPoupanca) {
        this.id = id;
        this.valorTotal = vlTotalPoupanca;
        this.mes = mesPoupanca;
    }

    public PoupancaHistorico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    
    public static final DBObject toDBObject(PoupancaHistorico ph) {
        BasicDBObject result = new BasicDBObject("valor_total", ph.getValorTotal())
                .append("mes", ph.getMes());
        
        if (ph.getId() != -1) {
            result.append("_id", ph.getId());
        }
        
        return result;
    }
}
