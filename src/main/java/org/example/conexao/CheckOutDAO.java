package org.example.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.example.Hotel.CheckIn;

import java.util.Date;

public class CheckOutDAO {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> checkOutCollection;

    public CheckOutDAO(){
        mongoClient = Connection.connection();
        MongoDatabase HotelPet = mongoClient.getDatabase("HotelPet");
        checkOutCollection = HotelPet.getCollection("check-out");
    }

    public void listar(){
        MongoCursor<Document> cursor = checkOutCollection.find().
                projection(Projections.fields(Projections.include("Nome", "Especie", "Tutor", "Idade","Andar", "Data"))).iterator();

        try{
            while(cursor.hasNext()){
                Document checkIn = cursor.next();
                String nome = checkIn.getString("Nome");
                String especie = checkIn.getString("Especie");
                String tutor = checkIn.getString("Tutor");
                int idade = checkIn.getInteger("Idade");
                int andar = checkIn.getInteger("andar");
                Date data = checkIn.getDate("Data");

                System.out.println("Nome: " + nome + "\n" +
                        "Especie: " + especie + "\n" +
                        "Tutor: " + tutor + "\n" +
                        "Idade: " + idade + "\n" +
                        "Andar: " + andar + "\n" +
                        "Data de check-in: " + data + "\n");
            }
        }finally {
            cursor.close();
        }
    }

    public void inserir(String nomeAnimal){
       CheckInDAO checkInDAO = new CheckInDAO();
       Document dadosCheckIn = checkInDAO.buscarDadosCheckIN(nomeAnimal);

        if (dadosCheckIn != null) {
            Date dataCheckOut = new Date();
            Date dataCheckIn = dadosCheckIn.getDate("Data");
            Document novoAnimalNaCheckOut = new Document("Nome", nomeAnimal)
                    .append("Especie", dadosCheckIn.getString("Especie"))
                    .append("Idade", dadosCheckIn.getInteger("Idade"))
                    .append("Tutor", dadosCheckIn.getString("Tutor"))
                    .append("Andar", dadosCheckIn.getInteger("Andar"))
                    .append("Data", dataCheckIn)
                    .append("Data check-Out", dataCheckOut);

            checkOutCollection.insertOne(novoAnimalNaCheckOut);
            System.out.println("Animal " + nomeAnimal + " foi inserido na coleção de check-outs.");
        } else {
            System.out.println("Animal " + nomeAnimal + " não encontrado nos check-ins.");
        }
    }

    public void listarCheckOut(String name){
        MongoCursor<Document> cursor = checkOutCollection.find(Filters.eq("Nome", name)).
                projection(Projections.fields(Projections.include("Nome", "Especie", "Tutor", "Idade", "Andar", "Data"))).iterator();

        Document checkOut = cursor.next();
        String nome = checkOut.getString("Nome");
        String esp = checkOut.getString("Especie");
        String tutor = checkOut.getString("Tutor");
        int idade = checkOut.getInteger("Idade");
        int andar = checkOut.getInteger("Andar");
        Date data = checkOut.getDate("Data");

        System.out.println("Nome: " + nome + "\n" +
                "Especie: " + esp + "\n" +
                "Tutor: " + tutor + "\n" +
                "Idade: " + idade + "\n" +
                "Especie: " + andar + "\n" +
                "Data Check-in: " + data + "\n");
    }

    public void close(){
        mongoClient.close();
    }
}
