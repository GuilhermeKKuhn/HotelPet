package org.example.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

//classe que faz a conexao com o mongo
public class Connection {
    private static final String CONNECTION = "mongodb://localhost:27017";
    private static MongoClient mongoClient;


    //metodo que garante que vai ter apenas uma instancia da classe
    public static MongoClient connection(){
        if(mongoClient == null){
            mongoClient = MongoClients.create(CONNECTION);
        }
        return mongoClient;
    }

    public void close(){
        mongoClient.close();
    }
}
