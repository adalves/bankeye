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
import model.domain.Emprestimo;
import mongo_client.Mongo;
import org.bson.Document;

/**
 *
 * @author Sara Theiss
 */
public class EmprestimoDAO {
    MongoDatabase db = Mongo.getDatabase();
    
    private MongoCollection<Document> getEmprestimos() {
        return db.getCollection("emprestimos");
    }    
    
    public boolean criar(Emprestimo emprestimo) {
        String validacao = "";

        if (emprestimo.getId() == -1) {
            validacao += "O Id do empréstimo está incorreto.\n";
        }

        if (emprestimo.getData().length() == 0) {
            validacao += "A data do empréstimo está incorreta.\n";
        }
        
        if (emprestimo.getParcelas().length == 0) {
            validacao += "As parcelas do empréstimo estão incorretas.\n";
        }
        
        if (emprestimo.getTipo().length() == 0) {
            validacao += "O tipo do empréstimo está incorreto.\n";
        }
        
        if (emprestimo.getValor() == 0) {
            validacao += "O valor do empréstimo está zerado.\n";
        }
        
        if ("".equals(validacao)) {
            getEmprestimos().insertOne((Document)Emprestimo.toDBObject(emprestimo));

            return true;
        }

        JOptionPane.showMessageDialog(null, validacao);
        
        return false;
    }
    
    public Emprestimo requestEmprestimo(int id) {
        System.out.println(getEmprestimos().find(Filters.eq("_id", id)).first());
                
        return null;
    }
}
