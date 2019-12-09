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
import model.domain.Banco;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class BancoDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getBancos() {
        return db.getCollection("bancos");
    }    
    
    public boolean criar(Banco banco) {
        String validacao = "";

        if (banco.getNome().length() == 0) {
            validacao += "O nome do banco está incorreto.\n";
        }
        
        if (Integer.toString(banco.getMoedaId()).length() == 0) {
            validacao += "A moeda está incorreta.\n";
        }
        
        if (Float.toString(banco.getTaxaConta()).length() == 0) {
            validacao += "A taxa da conta está incorreta.\n";
        }
        
        if(banco.getId() == -1){
            validacao += "O Id do banco está incorreto.\n";
        }
        
        if ("".equals(validacao)) {
            getBancos().insertOne((Document)Banco.toDBObject(banco));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Banco requestBanco(int id) {
        System.out.println(getBancos().find(Filters.eq("_id", id)).first());
                
        return null;
    }
    
}
