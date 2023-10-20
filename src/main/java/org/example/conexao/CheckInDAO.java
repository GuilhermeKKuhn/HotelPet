package org.example.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;


import java.util.Date;

public class CheckInDAO {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> checkInCollection;

    public CheckInDAO(){
        mongoClient = Connection.connection();
        MongoDatabase HotelPet = mongoClient.getDatabase("HotelPet");
        checkInCollection = HotelPet.getCollection("Check-in");
    }

    public void listar(){
        MongoCursor<Document> cursor = checkInCollection.find().
                projection(Projections.fields(Projections.include("Nome", "Especie", "Idade", "Tutor","Andar", "Data Check-in"))).iterator();

        try{
            while(cursor.hasNext()){
                Document checkIn = cursor.next();
                String nome = checkIn.getString("Nome");
                String especie = checkIn.getString("Especie");
                int idade = checkIn.getInteger("Idade");
                String tutor = checkIn.getString("Tutor");
                int andar = checkIn.getInteger("Andar");
                Date data = checkIn.getDate("Data Check-in");

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
        AnimalDAO animal = new AnimalDAO();
        Document dadosAnimal = animal.buscarDadosDoAnimal(nomeAnimal);
        Date dataCheckIn = new Date();
        Document novoAnimalNaCheckIn = new Document("Nome", nomeAnimal)
                .append("Especie", dadosAnimal.getString("Especie"))
                .append("Idade", dadosAnimal.getInteger("Idade"))
                .append("Tutor", dadosAnimal.getString("Tutor"))
                .append("Andar", dadosAnimal.getInteger("Andar")).
                append("Data Check-In", dataCheckIn);

        checkInCollection.insertOne(novoAnimalNaCheckIn);
        System.out.println("Animal " + nomeAnimal + " fez o check-in.");
    }

    public void close(){
        mongoClient.close();
    }

    public void remover(String name){
        if(verificaPessoa(name)){
            checkInCollection.deleteOne(Filters.eq("Nome", name));
        }else{
            System.out.println("a reserva no nome de: " + name + " não existe no banco");
        }
    }

    //verifica se a pessoa existe no banco
    public boolean verificaPessoa(String nome){
        Document verifica = checkInCollection.find(Filters.eq("Nome", nome)).
                projection(Projections.include("Nome")).first();
        return  verifica != null;
    }

    public void listarCheckIn(String name){
        MongoCursor<Document> cursor = checkInCollection.find(Filters.eq("Nome", name)).
                projection(Projections.fields(Projections.include("Nome", "Especie", "Tutor", "Idade", "Andar", "Data Check-in"))).iterator();

        Document checkIn = cursor.next();
        String nome = checkIn.getString("Nome");
        String esp = checkIn.getString("Especie");
        String tutor = checkIn.getString("Tutor");
        int idade = checkIn.getInteger("Idade");
        int andar = checkIn.getInteger("Andar");
        Date data = checkIn.getDate("Data Check-in");

        System.out.println("Nome: " + nome + "\n" +
                "Especie: " + esp + "\n" +
                "Tutor: " + tutor + "\n" +
                "Idade: " + idade + "\n" +
                "Andar: " + andar + "\n" +
                "Data Check-in: " + data + "\n");
    }

    public void listarCheckInPorEspecie(String Especie){
        MongoCursor<Document> cursor = checkInCollection.find(Filters.eq("Especie", Especie)).
                projection(Projections.fields(Projections.include("Nome", "Especie", "Tutor", "Idade", "Andar", "Data Check-in"))).iterator();

        Document checkIn = cursor.next();
        String nome = checkIn.getString("Nome");
        String esp = checkIn.getString("Especie");
        String tutor = checkIn.getString("Tutor");
        int idade = checkIn.getInteger("Idade");
        int andar = checkIn.getInteger("Andar");
        Date data = checkIn.getDate("Data Check-in");

        System.out.println("Nome: " + nome + "\n" +
                "Especie: " + esp + "\n" +
                "Tutor: " + tutor + "\n" +
                "Idade: " + idade + "\n" +
                "Andar: " + andar + "\n" +
                "Data Check-in: " + data + "\n");
    }

    public Document buscarDadosCheckIN(String nomeAnimal) {
        return checkInCollection.find(Filters.eq("Nome", nomeAnimal)).first();
    }

}
