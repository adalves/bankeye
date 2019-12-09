/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mongodb.client.MongoCollection;
import mongo_client.Mongo;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javax.swing.JOptionPane;
import org.bson.Document;
import model.domain.Cliente;

/**
 *
 * @author Jaison Robson Gusava
 */
public class ClienteDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getClientes() {
        return db.getCollection("clientes");
    }    
    
    public boolean criar(Cliente cliente) {
        String validacao = "";

        if (cliente.getId() == -1) {
            validacao += "O Id do cliente está incorreto.\n";
        }

        if (cliente.getNome().length() == 0) {
            validacao += "O nome do cliente está incorreto.\n";
        }
        
        System.out.println(Long.toString(cliente.getCpf()).length());
        
        if (Long.toString(cliente.getCpf()).length() != 11) {
            validacao += "O cpf esta incorreto.\n";
        }
        
        if (cliente.getEstado().length() == 0) {
            validacao += "O estado esta incorreto.\n";
        }
        
        if (cliente.getCidade().length() == 0) {
            validacao += "A cidade esta incorreta.\n";
        }
        
        if (cliente.getPais().length() == 0) {
            validacao += "O pais esta incorreto.\n";
        }
        
        if (Integer.toString(cliente.getTelefone()).length() == 0) {
            validacao += "O telefone esta incorreto.\n";
        }
        
        if (Integer.toString(cliente.getRg()).length() == 0) {
            validacao += "O rg esta incorreto.\n";
        }
        
        if (cliente.getNascimento().length() == 0) {
            validacao += "A data de nascimento esta incorreta.\n";
        }
        
        if ("".equals(validacao)) {
            getClientes().insertOne(Cliente.toDBObject(cliente));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Cliente requestCliente(long cpf) {
        System.out.println(getClientes().find(Filters.eq("cpf", cpf)).first());
                
        return null;
    }
}
