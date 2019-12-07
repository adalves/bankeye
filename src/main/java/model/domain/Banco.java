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
 * @author Jaison Robson Gusava
 */
public class Banco {
    private int id; // Utilizar o id do banco para saber o codigo do banco no momento da transferencia, isso encurta um campo no backend
    private String nome;
    private float taxaConta;
    private int moedaId;
    
    public Banco() {}

    public Banco(int id, String nome, float taxaConta, int moeda) {
        setId(id);
        setNome(nome);
        setTaxaConta(taxaConta);
        setMoedaId(moeda);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTaxaConta(float taxaConta) {
        this.taxaConta = taxaConta;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getTaxaConta() {
        return taxaConta;
    }

    public int getMoedaId() {
        return moedaId;
    }

    public void setMoedaId(int moedaId) {
        this.moedaId = moedaId;
    }    
    
    public static final DBObject toDBObject(Banco banco) {
        return new BasicDBObject("_id", banco.getId())
                .append("nome", banco.getNome())
                .append("taxa_conta", banco.getTaxaConta())
                .append("moeda_id", banco.getMoedaId());
    }
}
