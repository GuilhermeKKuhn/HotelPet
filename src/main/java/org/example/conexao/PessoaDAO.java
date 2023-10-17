package org.example.conexao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.example.Pessoas.Pessoa;
import org.example.Pessoas.Tutor;

public class PessoaDAO {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> pessoaCollection;

    public PessoaDAO(){
        mongoClient = Connection.connection();
        MongoDatabase HotelPet = mongoClient.getDatabase("HotelPet");
        pessoaCollection = HotelPet.getCollection("Pessoas");
    }

    //lista todas as pessoas e é filtrado por tipo - Tutor ou funcionario
    public void listar(int tipo){
        MongoCursor<Document> cursor = pessoaCollection.find(Filters.eq("tipo", tipo)). // faz uma busca filtrando por tipo de pessoa
        projection(Projections.fields(Projections.include("Nome", "CPF"))).iterator(); // usa o metodo projections para garantir que ele vai listar apenas os atributos que foi definido

        try{
            //percorre todos os documentos na coleção de pessoas
            while(cursor.hasNext()){
                Document pessoa = cursor.next();
                String nome = pessoa.getString("Nome");
                int cpf = pessoa.getInteger("CPF");

                System.out.println("Nome: " + nome + ", CPF: " + cpf);
            }
        }finally {
            cursor.close();
        }
    }

    //insere uma nova pessoa verificado se ela existe no banco
    public void inserir(Pessoa pessoa){
        if(!verificaPessoa(pessoa.getNome())) {
            Document newTutor = new Document("Nome", pessoa.getNome())
                    .append("CPF", pessoa.getDocumento())
                    .append("tipo", pessoa.getTipo());

            pessoaCollection.insertOne(newTutor);
            System.out.println("Cadastro realizado com sucesso");

        }else{
            System.out.println("esse nome de usuario ja esta cadastrado");
        }

    }

    //edita uma pessoa verificando se ela existe no banco
    public void editar(String name, String newName, int cpf, int tipo ){
        if (verificaPessoa(name)) {
            pessoaCollection.updateOne(
                    Filters.eq("Nome", name), //edita uma pessoa filtrando por nome
                    Updates.combine(
                            Updates.set("Nome", newName),
                            Updates.set("CPF", cpf),
                            Updates.set("tipo", tipo)
                    )
            );
            System.out.println("Editado com sucesso!");
        }else{
            System.out.println("essa pessoa nao existe no banco");
        }
    }

    //deleta um registro com base no nome
    public void excluir(String name){
        if(verificaPessoa(name)){
            pessoaCollection.deleteOne(Filters.eq("Nome", name)); //filtra a pessoa por nome e usa o metodo verifica pessoa para ver se ela existe no banco
            System.out.println("o registro foi deletado");
        }else{
            System.out.println("a pessoa com o nome " + name + " não existe no banco");
        }
    }


     //fecha a conexão
     public void close(){
        mongoClient.close();
     }

     //verifica se a pessoa existe no banco
     public boolean verificaPessoa(String nome){
        Document verifica = pessoaCollection.find(Filters.eq("Nome", nome)).
                projection(Projections.include("Nome")).first();
        return  verifica != null;
     }
}
