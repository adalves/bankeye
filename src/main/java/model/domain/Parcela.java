/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Matheus
 */
public class Parcela {
    private int id;
    private double valor;
    private double juro;
    private double total;
    private String vencimento;
    private boolean pago;

    public Parcela() {
    }

    public Parcela(int id, double vlParcela, double vlJuros, double vlTotalParcela, String dtVencimento, boolean pago) {
        this.id = id;
        this.valor = vlParcela;
        this.juro = vlJuros;
        this.total = vlTotalParcela;
        this.vencimento = dtVencimento;
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getJuro() {
        return juro;
    }

    public void setJuro(double juro) {
        this.juro = juro;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    
    public static final DBObject toDBObject(Parcela parcela) {
        BasicDBObject result = new BasicDBObject("valor", parcela.getValor())
                .append("juro", parcela.getJuro())
                .append("total", parcela.getTotal())
                .append("pago", parcela.isPago());
        
        if (parcela.getId() != -1) {
            result.append("_id", parcela.getId());
        }
        
        return result;
    }
}
