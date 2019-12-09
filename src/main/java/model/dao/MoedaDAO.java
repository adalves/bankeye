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
import model.domain.Moeda;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class MoedaDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getMoedas() {
        return db.getCollection("moedas");
    }    
    
    public boolean criar(Moeda moeda) {
        String validacao = "";

        if (moeda.getId() == -1) {
            validacao += "O Id da moeda está incorreto.\n";
        }

        if (moeda.getNome().length() == 0) {
            validacao += "O nome da moeda está incorreto.\n";
        }

        if (moeda.getCotacao() == 0) {
            validacao += "Cotação da moeda zerada.\n";
        }

        if (moeda.getPais().length() == 0) {
            validacao += "O país da moeda está incorreto.\n";
        }
        
        if ("".equals(validacao)) {
            getMoedas().insertOne((Document)Moeda.toDBObject(moeda));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Moeda requestMoeda(int id) {
        System.out.println(getMoedas().find(Filters.eq("_id", id)).first());
                
        return null;
    }
}
