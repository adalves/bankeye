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
import model.domain.Selic;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class SelicDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getSelic() {
        return db.getCollection("selic");
    }    
    
    public boolean criar(Selic selic) {
        String validacao = "";

        if (selic.getId() == -1) {
            validacao += "O Id do selic está incorreto.\n";
        }

        if (selic.getTxSelic() == 0) {
            validacao += "Taxa selic está zerada.\n";
        }

        if (selic.getTxSelicAno()== 0) {
            validacao += "Taxa anual selic está zerada.\n";
        }

        if (selic.getDtInicioVigencia().length() == 0) {
            validacao += "Data de início da vigência do selic está incorreta.\n";
        }

        if (selic.getDtFimVigencia().length() == 0) {
            validacao += "Data de fim da vigência do selic está incorreta.\n";
        }
        
        if ("".equals(validacao)) {
            getSelic().insertOne((Document)Selic.toDBObject(selic));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Selic requestSelic(int id) {
        System.out.println(getSelic().find(Filters.eq("_id", id)).first());
                
        return null;
    }
}
