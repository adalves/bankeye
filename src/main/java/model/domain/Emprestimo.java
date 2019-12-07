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
public class Emprestimo {
    
    private int id = -1;
    private double valor;
    private String data;
    private String tipo;
    private int[] parcelas;

    public Emprestimo() {}

    public Emprestimo(int id, double vlEmprestimo, String dtEmprestimo, String ieTipoEmprestimo, int[] parcelas) {
        this.id = id;
        this.valor = vlEmprestimo;
        this.data = dtEmprestimo;
        this.tipo = ieTipoEmprestimo;
        this.parcelas = parcelas;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int[] getParcelas() {
        return parcelas;
    }

    public void setParcelas(int[] parcelas) {
        this.parcelas = parcelas;
    }

    public static final DBObject toDBObject(Emprestimo emprestimo) {
        BasicDBObject result = new BasicDBObject("valor", emprestimo.getValor())
                .append("data", emprestimo.getData())
                .append("tipo", emprestimo.getTipo())
                .append("parcelas", emprestimo.getParcelas());
        
        if (emprestimo.getId() != -1) {
            result.append("_id", emprestimo.getId());
        }
        
        return result;
    }
}
