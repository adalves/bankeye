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
 * @author Jaison Robson Gusava
 */
public class Moeda {
    private int id;
    private String nome;
    private float cotacao;
    private String pais;
    
    public Moeda() {}

    public Moeda(int id, String nome, float cotacao, String pais) {
        setId(id);
        setNome(nome);
        setCotacao(cotacao);
        setPais(pais);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCotacao(float cotacao) {
        this.cotacao = cotacao;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }    

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getCotacao() {
        return cotacao;
    }

    public String getPais() {
        return pais;
    }
    
    public static final DBObject toDBObject(Moeda moeda) {
        return new BasicDBObject("_id", moeda.getNome())
                .append("nome", moeda.getNome())
                .append("cotacao", moeda.getCotacao())
                .append("pais", moeda.getPais());
    }
}
