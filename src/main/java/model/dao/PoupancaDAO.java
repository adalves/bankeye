/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javax.swing.JOptionPane;
import model.domain.Poupanca;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class PoupancaDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getPoupancas() {
        return db.getCollection("poupancas");
    }    
    
    public boolean criar(Poupanca poupanca) {
        String validacao = "";

        if (poupanca.getId() == -1) {
            validacao += "O Id da poupança está incorreto.\n";
        }

        if (poupanca.getData().length() == 0) {
            validacao += "A data da poupança está incorreta.\n";
        }
        
        if ("".equals(validacao)) {
            getPoupancas().insertOne((Document)Poupanca.toDBObject(poupanca));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Poupanca requestPoupanca(int id) {
        System.out.println(getPoupancas().find(Filters.eq("_id", id)).first());
                
        return null;
    }
}
