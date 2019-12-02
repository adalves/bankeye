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
public class Poupanca {
    private int id;
    private double valor;
    private String data;
    
    public Poupanca() {}

    public Poupanca(int id, double valor, String data) {
        this.id = id;
        this.valor = valor;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public static final DBObject toDBObject(Poupanca poupanca) {
        BasicDBObject result = new BasicDBObject("valor", poupanca.getValor())
                .append("data", poupanca.getData());
        
        if (poupanca.getId() != -1) {
            result.append("_id", poupanca.getId());
        }
        
        return result;
    }
}
