/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;

/**
 *
 * @author Jaison Robson Gusava
 */
public class Cliente {
    private int id = -1;
    private String nome;
    private long cpf;
    private String estado;
    private String cidade;
    private String pais;
    private int telefone;
    private int rg;
    private String nascimento;

    public Cliente() {}
    
    public Cliente(int id, String nome, long cpf, String estado, String cidade, String pais, int telefone, int rg, String nascimento) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setEstado(estado);
        setCidade(cidade);
        setPais(pais);
        setTelefone(telefone);
        setRg(rg);
        setNascimento(nascimento);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }    

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public long getCpf() {
        return cpf;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getPais() {
        return pais;
    }

    public int getTelefone() {
        return telefone;
    }

    public int getRg() {
        return rg;
    }

    public String getNascimento() {
        return nascimento;
    }
    
    public static final Document toDBObject(Cliente cliente) {
        Document result = new Document("nome", cliente.getNome())
                .append("cpf", cliente.getCpf())
                .append("estado", cliente.getEstado())
                .append("cidade", cliente.getCidade())
                .append("pais", cliente.getPais())
                .append("telefone", cliente.getTelefone())
                .append("rg", cliente.getRg())
                .append("nascimento", cliente.getNascimento());
        
        if (cliente.getId() != -1) {
            result.append("_id", cliente.getId());
        }
        
        return result;
    }
}
