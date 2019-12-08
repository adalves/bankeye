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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.domain.Conta;
import model.domain.Emprestimo;
import model.domain.Funcoes;
import model.domain.Parcela;
import model.domain.Poupanca;
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
    public static void main(String[] args) throws ParseException {
        
//        //teste das funcoes//
//        Conta c = new Conta(1, 1, 0, "1234", 1234, 600.00);
//        Funcoes f = new Funcoes();
//        System.out.println(c.getSaldo());
//        f.realizarDeposito(c, 1250.00);
//        System.out.println(c.getSaldo());
//        f.realizarSaque(c, 150.50);
//        System.out.println(c.getSaldo());
//        Calendar today = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        today.setTime(df.parse("07/08/2019"));
//        Poupanca p = f.criarPoupanca(50, 500.00, today);
//        System.out.println(p.getValor());
//        System.out.println(f.estimativaPoupanca(p));
//        int[] parcelas = new int[3];
//        Parcela p0 = new Parcela(1, 500, 0, 500, "01/06/2019", false);
//        Parcela p1 = new Parcela(1, 500, 0, 500, "01/10/2019", false);
//        Parcela p2 = new Parcela(1, 500, 0, 500, "01/02/2020", false);
//        parcelas[0] = p0.getId();
//        parcelas[1] = p1.getId();
//        parcelas[2] = p2.getId();
//        ArrayList<Parcela> ps = new ArrayList<>();
//        ps.add(p0); ps.add(p1); ps.add(p2);
//        Emprestimo e = new Emprestimo(4, 1500, "28/03/2019", "Consignado", parcelas);
//        System.out.println(c.getSaldo());
//        f.pagarParcelas(c, e, ps);
//        System.out.println(c.getSaldo());
//        System.out.println(p0.isPago());
//        System.out.println(p1.isPago());
//        System.out.println(p2.isPago());
//        f.pagarParcela(c, p2);
//        System.out.println(c.getSaldo());
//        System.out.println(p2.isPago());
//        Emprestimo e2 = f.realizarEmprestimo(c, 6, 2000, 0, "02/10/2019", "Consignado", 3);
//        System.out.println(e2.getValor());
//        System.out.println(c.getSaldo());
        
        
        
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
