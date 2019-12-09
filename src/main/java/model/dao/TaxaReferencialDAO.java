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
import model.domain.TaxaReferencial;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class TaxaReferencialDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getTaxaReferencial() {
        return db.getCollection("taxas_referenciais");
    }    
    
    public boolean criar(TaxaReferencial txReferencial) {
        String validacao = "";

        if (txReferencial.getId() == -1) {
            validacao += "O Id da taxa referencial está incorreto.\n";
        }

        if (txReferencial.getVlTaxa() == 0) {
            validacao += "O valor da taxa referencial está zerado.\n";
        }

        if (txReferencial.getDtVigencia().length() == 0) {
            validacao += "A data de vigência da taxa referencial está incorreta.\n";
        }
        
        if ("".equals(validacao)) {
            getTaxaReferencial().insertOne((Document)TaxaReferencial.toDBObject(txReferencial));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public TaxaReferencial requestTaxaReferencial(int id) {
        System.out.println(getTaxaReferencial().find(Filters.eq("_id", id)).first());
                
        return null;
    }
}
