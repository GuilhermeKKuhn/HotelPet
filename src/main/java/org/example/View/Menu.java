package org.example.View;

import org.example.Animais.Animal;
import org.example.Animais.Cachorro;
import org.example.Animais.Gato;
import org.example.Animais.Passaro;
import org.example.Controler.ControllerHotel;
import org.example.Controler.ListAnimais;
import org.example.Controler.ListPessoas;
import org.example.Pessoas.Funcionario;
import org.example.Pessoas.Pessoa;
import org.example.Pessoas.Tutor;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    ListAnimais listaAnimais = new ListAnimais();
    ListPessoas listPessoas = new ListPessoas();
    ControllerHotel reservas = new ControllerHotel();
    public Menu(){
    }

    public void menuPrincipal(){
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1 -- Realizar Check-in");
        System.out.println("2 -- Realizar Check-out");
        System.out.println("3 -- Cadastro");
        System.out.println("4 -- Sair");
        System.out.print("Informe uma opção: ");
        int opção = scanner.nextInt();

        switch (opção){
            case 1:
                System.out.println("========== REALIZAR CHECK-IN ==========");
                for(int i = 0; i < listaAnimais.getAnimais().size(); i++){
                    System.out.println(i + ". " + listaAnimais.getAnimais().get(i).getNome() +
                            " tutor: " + listaAnimais.getAnimais().get(i).getTutor().getNome() + " doc: " + listaAnimais.getAnimais().get(i).getTutor().getDocumento());
                }
                System.out.print("Selecione o Animal que deseja fazer check-in: ");
                int opçãoCheckIn = scanner.nextInt();

                System.out.println("confirme os dados abaixo");
                System.out.println("nome: " + listaAnimais.getAnimais().get(opçãoCheckIn).getNome() + "\n" +
                    "Idade: " + listaAnimais.getAnimais().get(opçãoCheckIn).getIdade() + "\n" +
                    "Especie: " + listaAnimais.getAnimais().get(opçãoCheckIn).getEspecie() + "\n" +
                    "Tutor: " + listaAnimais.getAnimais().get(opçãoCheckIn).getTutor().getNome() + "\n" +
                    "Documento: " + listaAnimais.getAnimais().get(opçãoCheckIn).getTutor().getDocumento() + "\n");

                System.out.println("Realizar Check-in?");
                System.out.println("1 -- SIM");
                System.out.println("2 -- NÂO");
                int realizarCheckIn = scanner.nextInt();
                if (realizarCheckIn == 1){
                    System.out.println("Check-in Realizado com sucesso!");
                    reservas.getReservas().add(listaAnimais.getAnimais().get(opçãoCheckIn));
                    menuPrincipal();
                } else if (realizarCheckIn == 2) {
                    System.out.println("check-in cancelado");
                    menuPrincipal();
                }
                break;
            case 2:
                System.out.println("========== REALIZAR CHECK-OUT ==========");
                for(int i = 0; i < reservas.getReservas().size(); i++){
                    System.out.println(i + ". " + reservas.getReservas().get(i).getNome() + "\n" +
                            "especie: " + reservas.getReservas().get(i).getEspecie() + "\n" +
                            "tutor: " + reservas.getReservas().get(i).getTutor().getNome() + "\n" +
                            "andar: " + reservas.getReservas().get(i).getAndar() + "\n" +
                            "doc: " + reservas.getReservas().get(i).getTutor().getDocumento() + "\n\n");

                }
                System.out.print("Selecione o Animal que deseja fazer check-out: ");
                int opçãoCheckOut = scanner.nextInt();

                System.out.println("confirme os dados abaixo");
                System.out.println("nome: " + reservas.getReservas().get(opçãoCheckOut).getNome() + "\n" +
                        "Idade: " + reservas.getReservas().get(opçãoCheckOut).getIdade() + "\n" +
                        "Especie: " + reservas.getReservas().get(opçãoCheckOut).getEspecie() + "\n" +
                        "Tutor: " + reservas.getReservas().get(opçãoCheckOut).getTutor().getNome() + "\n" +
                        "Documento: " + reservas.getReservas().get(opçãoCheckOut).getTutor().getDocumento() + "\n");

                System.out.println("Realizar Check-out?");
                System.out.println("1 -- SIM");
                System.out.println("2 -- NÂO");
                int realizarCheckOut = scanner.nextInt();
                if (realizarCheckOut == 1){
                    System.out.println("Check-Out Realizado com sucesso!");
                    reservas.getReservas().remove(reservas.getReservas().get(opçãoCheckOut));
                    menuPrincipal();
                } else if (realizarCheckOut == 2) {
                    System.out.println("check-Out cancelado");
                    menuPrincipal();
                }
                break;
            case 3:
                System.out.println("========== CADASTROS ==========");
                System.out.println("1 -- cadastro de Animais");
                System.out.println("2 -- cadastro de Pessoa");
                System.out.println("3 -- voltar");
                System.out.print("Selecione uma opção: ");
                int opçãoCadastro = scanner.nextInt();

                switch (opçãoCadastro){
                    case 1:
                        System.out.println("========== CADASTRO DE ANIMAIS ==========");
                        System.out.println("1 -- cadastrar Animal");
                        System.out.println("2 -- excluir cadastro");
                        System.out.println("3 -- editar cadastro");
                        System.out.println("4 -- voltar");
                        System.out.println("escolha uma opção: ");
                        int opçãoCadastroAnimal = scanner.nextInt();

                        switch (opçãoCadastroAnimal){
                            case 1:
                                System.out.println("========== CADASTRO DE ANIMAIS ==========");
                                System.out.println("nome: ");
                                String nome = scanner.next();
                                System.out.println("idade: ");
                                int idade = scanner.nextInt();
                                System.out.println("selecione um tutor");
                                listPessoas.listar(1);
                                Pessoa tutorSelecionado = listPessoas.seleciona();

                                System.out.println("informe a especie de animal");
                                System.out.println("1 - cachorro");
                                System.out.println("2 - Gato");
                                System.out.println("3 - passaro");
                                int opçãoAnimal = scanner.nextInt();

                                if(opçãoAnimal == 1){
                                    Cachorro cachorro = new Cachorro(nome, idade, tutorSelecionado);
                                    listaAnimais.getAnimais().add(cachorro);
                                } else if (opçãoAnimal == 2) {
                                    Gato gato = new Gato(nome, idade, tutorSelecionado);
                                    listaAnimais.getAnimais().add(gato);
                                } else if (opçãoAnimal == 3) {
                                    Passaro passaro = new Passaro(nome, idade, tutorSelecionado);
                                    listaAnimais.getAnimais().add(passaro);
                                }
                                menuPrincipal();
                                break;
                            case 2:
                                System.out.println("========== EXCLUIR CADASTRO ==========");
                                System.out.println("1 - Cachorro");
                                System.out.println("2 - Gato");
                                System.out.println("3 - passaro");
                                int opçãoExcluir = scanner.nextInt();
                                if (opçãoExcluir == 1){
                                    listaAnimais.Listar("Cachorro");
                                    Animal excluir = listaAnimais.seleciona();
                                    listaAnimais.getAnimais().remove(excluir);
                                    System.out.println("Cadastro excluido com sucesso");
                                } else if (opçãoExcluir == 2) {
                                    listaAnimais.Listar("Gato");
                                    Animal excluir = listaAnimais.seleciona();
                                    listaAnimais.getAnimais().remove(excluir);
                                } else if (opçãoExcluir == 3) {
                                    listaAnimais.Listar("Passaro");
                                    Animal excluir = listaAnimais.seleciona();
                                    listaAnimais.getAnimais().remove(excluir);
                                    System.out.println("Cadastro excluido com sucesso");
                                }
                                menuPrincipal();
                                break;
                            case 3:
                                System.out.println("========== EDITAR CADASTRO ==========");
                                for(int i = 0 ; i < listaAnimais.getAnimais().size(); i++){
                                    System.out.println(i + ". " + listaAnimais.getAnimais().get(i).getNome() + "especie: " + listaAnimais.getAnimais().get(i).getEspecie() + " tutor: " + listaAnimais.getAnimais().get(i).getTutor().getNome());
                                }
                                System.out.println("Selecione o animal que deseja editar: ");
                                int selecionaAnimalEditar = scanner.nextInt();
                                System.out.println("nome: ");
                                String nomeEditar = scanner.next();
                                System.out.println("idade: ");
                                int idadeEditar = scanner.nextInt();
                                System.out.println("selecione um tutor");
                                listPessoas.listar(1);
                                Pessoa tutorSelecionadoEditar = listPessoas.seleciona();

                                System.out.println("informe a especie de animal");
                                System.out.println("1 - cachorro");
                                System.out.println("2 - Gato");
                                System.out.println("3 - passaro");
                                int opçãoAnimalEditar = scanner.nextInt();

                                if(opçãoAnimalEditar == 1){
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setNome(nomeEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setIdade(idadeEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setTutor(tutorSelecionadoEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setEspecie("Cachorro");
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setAndar(1);
                                } else if (opçãoAnimalEditar == 2) {
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setNome(nomeEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setIdade(idadeEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setTutor(tutorSelecionadoEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setEspecie("Gato");
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setAndar(2);
                                } else if (opçãoAnimalEditar == 3) {
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setNome(nomeEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setIdade(idadeEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setTutor(tutorSelecionadoEditar);
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setEspecie("Passaro");
                                    listaAnimais.getAnimais().get(selecionaAnimalEditar).setAndar(3);
                                }
                                System.out.println("editado com sucesso");
                                menuPrincipal();
                                break;
                            case 4:
                                menuPrincipal();
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("========== CADASTRO DE PESSOA ==========");
                        System.out.println("1 -- cadastrar Pessoa");
                        System.out.println("2 -- excluir cadastro");
                        System.out.println("3 -- editar cadastro");
                        System.out.println("4 -- voltar");
                        System.out.println("escolha uma opção: ");
                        int opçãoCadastroPessoa = scanner.nextInt();

                        switch (opçãoCadastroPessoa){
                            case 1:
                                System.out.println("========== CADASTRO DE PESSOA ==========");
                                System.out.println("Nome: ");
                                String nome = scanner.next();
                                System.out.println("Documento: ");
                                int doc = scanner.nextInt();
                                System.out.println("escolha oque deseja cadastrar");
                                System.out.println("1 - Tutor");
                                System.out.println("2 - Funcionario");
                                int opçãoCadastroTipoPessoa = scanner.nextInt();
                                if (opçãoCadastroTipoPessoa == 1){
                                    Tutor cadastroTutor = new Tutor(nome, doc, opçãoCadastroTipoPessoa);
                                    listPessoas.getPessoas().add(cadastroTutor);
                                    System.out.println("Cadastro realizado com sucesso");
                                } else if (opçãoCadastroTipoPessoa == 2) {
                                    Funcionario cadastroFuncionario = new Funcionario(nome, doc, opçãoCadastroTipoPessoa);
                                    listPessoas.getPessoas().add(cadastroFuncionario);
                                    System.out.println("Cadastro realizado com sucesso");
                                }
                                menuPrincipal();
                                break;
                            case 2:
                                System.out.println("========== EXCLUIR CADASTRO ==========");
                                System.out.println("1 - Tutor");
                                System.out.println("2 - Funcionario");
                                int opçãoExcluir = scanner.nextInt();
                                if (opçãoExcluir == 1){
                                    listPessoas.listar(1);
                                    Pessoa excluir = listPessoas.seleciona();
                                    listPessoas.getPessoas().remove(excluir);
                                    System.out.println("Cadastro excluido com sucesso");
                                } else if (opçãoExcluir == 2) {
                                    listPessoas.listar(2);
                                    Pessoa excluir = listPessoas.seleciona();
                                    listPessoas.getPessoas().remove(excluir);
                                    System.out.println("Cadastro excluido com sucesso");
                                }
                                menuPrincipal();
                                break;
                            case 3:
                                System.out.println("========== EDITAR CADASTRO ==========");
                                for(int i = 0 ; i < listPessoas.getPessoas().size(); i++){
                                    System.out.println(i + ". " + listPessoas.getPessoas().get(i).getNome() + "DOC: " + listPessoas.getPessoas().get(i).getDocumento());
                                }
                                System.out.println("Selecione a pessoa que deseja editar: ");
                                int selecionaPessoaEditar = scanner.nextInt();
                                System.out.println("nome: ");
                                String nomeEditar = scanner.next();
                                System.out.println("documento: ");
                                int docEditar = scanner.nextInt();
                                System.out.println("1  -- Tutor");
                                System.out.println("2 -- Funcionario");
                                int tipoEditar = scanner.nextInt();

                                if (tipoEditar == 1) {
                                    listPessoas.getPessoas().get(selecionaPessoaEditar).setNome(nomeEditar);
                                    listPessoas.getPessoas().get(selecionaPessoaEditar).setDocumento(docEditar);
                                    listPessoas.getPessoas().get(selecionaPessoaEditar).setTipo(1);
                                } else if (tipoEditar == 2) {
                                    listPessoas.getPessoas().get(selecionaPessoaEditar).setNome(nomeEditar);
                                    listPessoas.getPessoas().get(selecionaPessoaEditar).setDocumento(docEditar);
                                    listPessoas.getPessoas().get(selecionaPessoaEditar).setTipo(2);
                                }
                                System.out.println("editado com sucesso");
                                menuPrincipal();
                                break;
                            case 4:
                                menuPrincipal();
                                break;
                        }
                }
                break;
            case 4:
                System.exit(0);
        }
    }

}
