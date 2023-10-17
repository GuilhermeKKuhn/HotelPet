package org.example.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.example.Animais.Animal;
import org.example.Hotel.CheckIn;

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
                projection(Projections.fields(Projections.include("Nome", "Especie", "Idade", "Tutor","Andar", "Data"))).iterator();

        try{
            while(cursor.hasNext()){
                Document checkIn = cursor.next();
                String nome = checkIn.getString("Nome");
                String especie = checkIn.getString("Especie");
                int idade = checkIn.getInteger("Idade");
                String tutor = checkIn.getString("Tutor");
                int andar = checkIn.getInteger("Andar");
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
        AnimalDAO animal = new AnimalDAO();
        Document dadosAnimal = animal.buscarDadosDoAnimal(nomeAnimal);

        if (dadosAnimal != null) {
            Date dataCheckIn = new Date();
            Document novoAnimalNaCheckIn = new Document("Nome", nomeAnimal)
                    .append("Especie", dadosAnimal.getString("Especie"))
                    .append("Idade", dadosAnimal.getInteger("Idade"))
                    .append("Tutor", dadosAnimal.getString("Tutor"))
                    .append("Andar", dadosAnimal.getInteger("Andar"))
                    .append("Data", dataCheckIn);

            checkInCollection.insertOne(novoAnimalNaCheckIn);
            System.out.println("Animal " + nomeAnimal + " foi inserido na coleção de check-ins.");
        } else {
            System.out.println("Animal " + nomeAnimal + " não encontrado na coleção de animais.");
        }
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

    public Document buscarDadosCheckIN(String nomeAnimal) {
        return checkInCollection.find(Filters.eq("Nome", nomeAnimal)).first();
    }


}
