/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongo_client;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import control.*;

/**
 *
 * @author Jaison Robson Gusava
 */
public class Mongo {
    private static MongoClient client;


    public static synchronized MongoClient getInstance(String uri) {
        if (client == null) {
            client = MongoClients.create(uri);
            Globals.comment("Criou mongo client");
        }

        return client;
    }
    
    public static synchronized MongoClient getInstance() {
        return client;
    }
    
    public static MongoDatabase getDatabase() {
        return Mongo.getInstance().getDatabase("sistemaBancario");
    }
}
