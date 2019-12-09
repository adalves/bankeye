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
import model.domain.Parcela;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class ParcelaDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getParcelas() {
        return db.getCollection("parcelas");
    }    
    
    public boolean criar(Parcela parcela) {
        String validacao = "";

        if (parcela.getId() == -1) {
            validacao += "O Id da parcela está incorreto.\n";
        }
        
        if (parcela.getValor() == 0) {
            validacao += "O valor da parcela está zerado.\n";
        }
        
        if (parcela.getJuro() < 0) {
            validacao += "O valor de juros da parcela está incorreto.\n";
        }
        
        if (parcela.getTotal() == 0) {
            validacao += "O valor total da parcela está zerado.\n";
        }
        
        if (parcela.getVencimento().length() == 0) {
            validacao += "O vencimento da parcela está incorreto.\n";
        }
        
        if ("".equals(validacao)) {
            getParcelas().insertOne((Document)Parcela.toDBObject(parcela));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Parcela requestParcela(int id) {
        System.out.println(getParcelas().find(Filters.eq("_id", id)).first());
                
        return null;
    }
}
