package org.example.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.example.Pessoas.Tutor;

import java.util.Collection;

public class TutorDAO {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> tutorCollection;

    public TutorDAO(){
        mongoClient = Connection.connection();
        MongoDatabase HotelPet = mongoClient.getDatabase("HotelPet");
        tutorCollection = HotelPet.getCollection("Tutor");
    }


    public void listar(){
        MongoCursor<Document> cursor = tutorCollection.find().
        projection(Projections.fields(Projections.include("Nome", "CPF"))).iterator();

        try{
            while(cursor.hasNext()){
                Document tutor = cursor.next();
                String nome = tutor.getString("Nome");
                int cpf = tutor.getInteger("CPF");

                System.out.println("Nome: " + nome + ", CPF: " + cpf);
            }
        }finally {
            cursor.close();
        }
    }

    //insere um novo tutor
    public void inserir(Tutor tutor){
        Document newTutor = new Document("Nome", tutor.getNome())
                .append("CPF", tutor.getDocumento())
                .append("tipo", tutor.getTipo());

        tutorCollection.insertOne(newTutor);
    }

    public void editar(String name, String newName, int cpf, int tipo ){
        tutorCollection.updateOne(
            Filters.eq("Nome", name),
            Updates.combine(
                Updates.set("Nome", newName),
                Updates.set("CPF", cpf),
                Updates.set("tipo", tipo)

            )
        );
    }

    public void excluir(String name){
        tutorCollection.deleteOne(Filters.eq("Nome", name));
    }

     public void close(){
        mongoClient.close();
     }

     public boolean verificaPessoa(){
        Document verifica = T

     }
}
