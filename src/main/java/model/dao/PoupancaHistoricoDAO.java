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
import model.domain.PoupancaHistorico;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class PoupancaHistoricoDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getPoupancaHistoricos() {
        return db.getCollection("poupanca_historicos");
    }    
    
    public boolean criar(PoupancaHistorico poupancaHistorico) {
        String validacao = "";

        if (poupancaHistorico.getId() == -1) {
            validacao += "O Id do histórico da poupança está incorreto.\n";
        }

        if (poupancaHistorico.getValorTotal() == 0) {
            validacao += "O valor total do hitórico da poupança está zerado.\n";
        }

        if (poupancaHistorico.getMes().length() == 0) {
            validacao += "O mês do hitórico da poupança está incorreto.\n";
        }
        
        if ("".equals(validacao)) {
            getPoupancaHistoricos().insertOne((Document)PoupancaHistorico.toDBObject(poupancaHistorico));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public PoupancaHistorico requestPoupancaHistorico(int id) {
        System.out.println(getPoupancaHistoricos().find(Filters.eq("_id", id)).first());
                
        return null;
    }
}
