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
public class Conta {
    private int id = -1;
    private int clienteId;
    private int bancoId;
    private int emprestimoId;
    private String senha;
    private int numero;
    private int saldo;
    
    public Conta() {}

    public Conta(int clienteId, int bancoId, int emprestimoId, String senha, int numero, int saldo) {
        this.clienteId = clienteId;
        this.bancoId = bancoId;
        this.emprestimoId = emprestimoId;
        this.senha = senha;
        this.numero = numero;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getBancoId() {
        return bancoId;
    }

    public void setBancoId(int bancoId) {
        this.bancoId = bancoId;
    }

    public int getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(int emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    public static final DBObject toDBObject(Conta conta) {
        BasicDBObject result = new BasicDBObject("numero", conta.getNumero())
                .append("cliente_id", conta.getClienteId())
                .append("banco_id", conta.getBancoId())
                .append("emprestimo_id", conta.getEmprestimoId())
                .append("senha", conta.getSenha())
                .append("saldo", conta.getSaldo());
        
        if (conta.getId() != -1) {
            result.append("_id", conta.getId());
        }
        
        return result;
    }
}
