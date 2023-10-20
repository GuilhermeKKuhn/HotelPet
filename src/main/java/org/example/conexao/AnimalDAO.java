package org.example.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.example.Animais.Animal;


public class AnimalDAO {
    private final MongoCollection<Document> animaisCollection;

    public AnimalDAO(){
        MongoClient mongoClient = Connection.connection();
        MongoDatabase HotelPet = mongoClient.getDatabase("HotelPet");
        animaisCollection = HotelPet.getCollection("Animais");
    }

    public void listar(){

        try (MongoCursor<Document> cursor = animaisCollection.find().
                projection(Projections.fields(Projections.include("Nome", "Especie", "Tutor"))).iterator()) {
            while (cursor.hasNext()) {
                Document animal = cursor.next();
                String nome = animal.getString("Nome");
                String esp = animal.getString("Especie");
                String tutor = animal.getString("Tutor");

                System.out.println("Nome: " + nome + ", Especie: " + esp + ", Tutor: " + tutor);
            }
        }
    }

    public void inserir(Animal animal){
        if(!verificaAnimal(animal.getNome())){
            Document newAnimal = new Document("Nome", animal.getNome())
                    .append("Especie", animal.getEspecie())
                    .append("Idade", animal.getIdade())
                    .append("Tutor", animal.getTutor())
                    .append("Andar", animal.getAndar());
            animaisCollection.insertOne(newAnimal);
            System.out.println("Cadastro realizado com sucesso");
        }else{
            System.out.println("esse nome de animal ja esta cadastrado");
        }
    }

    public void editar(String nome, String newName, String especie, int idade, int andar, String tutor){
        if(verificaAnimal(nome)){
            animaisCollection.updateOne(Filters.eq("Nome", nome),
                    Updates.combine(
                            Updates.set("Nome", newName),
                            Updates.set("Especie", especie),
                            Updates.set("Idade", idade),
                            Updates.set("Andar", andar),
                            Updates.set("Tutor", tutor)
                    )
            );
            System.out.println("Editado com sucesso!");
        }else{
            System.out.println("esse animal nao existe no banco");
        }
    }

    public void excluir(String nome){
        if(verificaAnimal(nome)){
            animaisCollection.deleteOne(Filters.eq("Nome", nome));
            System.out.println("o registro foi deletado com sucesso");
        }else{
            System.out.println("o animal com de nome: " + nome + " nao foi encontardo");
        }
    }

    public boolean verificaAnimal(String nome) {
        Document verifica = animaisCollection.find(Filters.eq("Nome", nome)).first();
        return verifica != null;
    }

    public void listarEspecie(String especie){

        try (MongoCursor<Document> cursor = animaisCollection.find(Filters.eq("Especie", especie)).
                projection(Projections.fields(Projections.include("Nome", "Especie", "Tutor"))).iterator()) {
            while (cursor.hasNext()) {
                Document animal = cursor.next();
                String nome = animal.getString("Nome");
                String esp = animal.getString("Especie");
                String tutor = animal.getString("Tutor");

                System.out.println("Nome: " + nome + ", Especie: " + esp + ", Tutor: " + tutor);
            }
        }
    }

    public void listarAnimal(String name){
        MongoCursor<Document> cursor = animaisCollection.find(Filters.eq("Nome", name)).
                projection(Projections.fields(Projections.include("Nome", "Especie", "Tutor", "Idade", "Andar"))).iterator();

            Document animal = cursor.next();
            String nome = animal.getString("Nome");
            String esp = animal.getString("Especie");
            String tutor = animal.getString("Tutor");
            int idade = animal.getInteger("Idade");
            int andar = animal.getInteger("Andar");

            System.out.println("Nome: " + nome + "\n" +
                    "Especie: " + esp + "\n" +
                    "Tutor: " + tutor + "\n" +
                    "Idade: " + idade + "\n" +
                    "Andar: " + andar + "\n");
    }

    public Document buscarDadosDoAnimal(String nomeAnimal) {
        return animaisCollection.find(Filters.eq("Nome", nomeAnimal)).first();
    }
}
