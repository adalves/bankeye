package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.BasicDBObject;
import mongo_client.Mongo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import view.loggedout.LoginView;

/**
 *
 * @author Jaison Robson Gusava
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MongoClient client = Mongo.getInstance("mongodb://localhost:27017");
        
        MongoDatabase db = client.getDatabase("sistemaBancario");
        
        MongoCollection<Document> moedas = db.getCollection("moedas");
        
        //Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        //mongoLogger.setLevel(Level.SEVERE);
        
        moedas.insertOne(
            new Document("_id", 1)
            .append("nome", "Real")
            .append("cotacao", 4.23)
            .append("pais", "Brasil")
        );        
        
        MongoCollection<Document> bancos = db.getCollection("bancos");
        
        ArrayList<Document> bancosDocs = new ArrayList<>();
        
        bancosDocs.add(
                new Document("_id", 1)
                .append("nome", "Banco do Brasil")
                .append("taxa_conta", 14.50)
                .append("moeda_id", 1)
        );
        
        bancosDocs.add(
                new Document("_id", 2)
                .append("nome", "Itau")
                .append("taxa_conta", 10.00)
                .append("moeda_id", 1)
        );
        
        bancos.insertMany(bancosDocs);
        
        new LoginView().setVisible(true);
    }
    
}
