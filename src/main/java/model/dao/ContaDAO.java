/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;
import model.domain.Conta;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Jaison Robson Gusava
 */
public class ContaDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getContas() {
        return db.getCollection("contas");
    }    
    
    public boolean criar(Conta conta) {
        String validacao = "";
        
        if (conta.getId() == -1) {
            validacao += "O Id da conta está incorreto.\n";
        }        
        
        if (conta.getClienteId() == -1) {
            validacao += "O Id do cliente está incorreto.\n";
        }        
        
        if (conta.getBancoId() == -1) {
            validacao += "O Id do banco está incorreto.\n";
        }        
        
        if (conta.getEmprestimoId() == -1) {
            validacao += "O Id do empréstimo está incorreto.\n";
        }        
        
        if (conta.getSenha().length() == 0) {
            validacao += "Senha está incorreta.\n";
        }        
        
        if (conta.getNumero() == 0) {
            validacao += "O número da conta está incorreto.\n";
        }
        
        if ("".equals(validacao)) {
            getContas().insertOne((Document)Conta.toDBObject(conta));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Conta requestConta(int numero) {
        System.out.println(getContas().find(Filters.eq("numero", numero)).first().toJson());
                
        return null;
    }
}
