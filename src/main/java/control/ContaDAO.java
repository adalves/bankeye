/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;
import model.Conta;
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
        
        if (conta.getSenha().length() == 0) {
            validacao += "Senha está incorreta.\n";
        }        
        
        if (Integer.toString(conta.getBancoId()).length() == 0) {
            validacao += "Banco esta incorreto.\n";
        }
        
        if (Integer.toString(conta.getClienteId()).length() == 0) {
            validacao += "Cliente esta incorreto.\n";
        }
        
        if (Integer.toString(conta.getNumero()).length() == 0) {
            validacao += "A conta esta incorreta.\n";
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
