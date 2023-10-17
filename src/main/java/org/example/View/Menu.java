package org.example.View;

import org.example.Animais.Cachorro;
import org.example.Animais.Gato;
import org.example.Animais.Passaro;
import org.example.Pessoas.Funcionario;
import org.example.Pessoas.Tutor;
import org.example.conexao.AnimalDAO;
import org.example.conexao.CheckInDAO;
import org.example.conexao.CheckOutDAO;
import org.example.conexao.PessoaDAO;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public Menu(){
    }

    PessoaDAO pessoaDAO = new PessoaDAO();
    AnimalDAO animalDAO = new AnimalDAO();
    CheckInDAO checkInDAO = new CheckInDAO();
    CheckOutDAO checkOutDAO = new CheckOutDAO();

    public void menuPrincipal(){
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1 -- Realizar Check-in");
        System.out.println("2 -- Realizar Check-out");
        System.out.println("3 -- Cadastro");
        System.out.println("4 -- Sair");
        System.out.print("Informe uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                System.out.println("========== REALIZAR CHECK-IN ==========");
                animalDAO.listar();
                System.out.print("digite o nome do animal que deseja fazer check-in: ");
                String opcaoCheckIn = scanner.next();
                System.out.println("confirme os dados abaixo");
                animalDAO.listarAnimal(opcaoCheckIn);
                System.out.println("Realizar Check-in?");
                System.out.println("1 -- SIM");
                System.out.println("2 -- NÂO");
                int realizarCheckIn = scanner.nextInt();
                if (realizarCheckIn == 1) {
                    checkInDAO.inserir(opcaoCheckIn);
                    menuPrincipal();
                } else if (realizarCheckIn == 2) {
                    System.out.println("check-in cancelado");
                    menuPrincipal();
                }
            }
            case 2 -> {
                System.out.println("========== REALIZAR CHECK-OUT ==========");
                checkInDAO.listar();
                System.out.print("digite o nome do Animal que deseja fazer check-out: ");
                String opcaoCheckOut = scanner.next();
                System.out.println("confirme os dados abaixo");
                checkInDAO.listarCheckIn(opcaoCheckOut);
                System.out.println("Realizar Check-out?");
                System.out.println("1 -- SIM");
                System.out.println("2 -- NÂO");
                int realizarCheckOut = scanner.nextInt();
                if (realizarCheckOut == 1) {
                    checkInDAO.remover(opcaoCheckOut);
                    checkOutDAO.inserir(opcaoCheckOut);
                    menuPrincipal();
                } else if (realizarCheckOut == 2) {
                    System.out.println("check-Out cancelado");
                    menuPrincipal();
                }
            }
            case 3 -> {
                System.out.println("========== CADASTROS ==========");
                System.out.println("1 -- cadastro de Animais");
                System.out.println("2 -- cadastro de Pessoa");
                System.out.println("3 -- voltar");
                System.out.print("Selecione uma opção: ");
                int opcaoCadastro = scanner.nextInt();
                switch (opcaoCadastro) {
                    case 1 -> {
                        System.out.println("========== CADASTRO DE ANIMAIS ==========");
                        System.out.println("1 -- cadastrar Animal");
                        System.out.println("2 -- excluir cadastro");
                        System.out.println("3 -- editar cadastro");
                        System.out.println("4 -- voltar");
                        System.out.println("escolha uma opção: ");
                        int opcaoCadastroAnimal = scanner.nextInt();
                        switch (opcaoCadastroAnimal) {
                            case 1 -> {
                                System.out.println("========== CADASTRO DE ANIMAIS ==========");
                                System.out.println("nome: ");
                                String nome = scanner.next();
                                System.out.println("idade: ");
                                int idade = scanner.nextInt();
                                pessoaDAO.listar(1);
                                System.out.println("digite o nome de um tutor");
                                String tutor = scanner.next();
                                System.out.println("informe a especie de animal");
                                System.out.println("1 - cachorro");
                                System.out.println("2 - Gato");
                                System.out.println("3 - passaro");
                                int opcaoAnimal = scanner.nextInt();

                                if (opcaoAnimal == 1) {
                                    Cachorro cachorro = new Cachorro(nome, idade, tutor);
                                    animalDAO.inserir(cachorro);
                                } else if (opcaoAnimal == 2) {
                                    Gato gato = new Gato(nome, idade, tutor);
                                    animalDAO.inserir(gato);
                                } else if (opcaoAnimal == 3) {
                                    Passaro passaro = new Passaro(nome, idade, tutor);
                                    animalDAO.inserir(passaro);
                                }
                                menuPrincipal();
                            }
                            case 2 -> {
                                System.out.println("========== EXCLUIR CADASTRO ==========");
                                System.out.println("1 - Cachorro");
                                System.out.println("2 - Gato");
                                System.out.println("3 - passaro");
                                int opcaoExcluir = scanner.nextInt();
                                if (opcaoExcluir == 1) {
                                    animalDAO.listarEspecie("Cachorro");
                                    System.out.println("Informe o nome do animal que deseja excluir: ");
                                    String nomeExcluir = scanner.next();
                                    animalDAO.listarAnimal(nomeExcluir);
                                    System.out.println("Excluir cadastro?");
                                    System.out.println("1 -- sim");
                                    System.out.println("2 -- não");
                                    int excluir = scanner.nextInt();

                                    if (excluir == 1) {
                                        animalDAO.excluir(nomeExcluir);
                                    } else {
                                        System.out.println("Cancelado!");
                                        menuPrincipal();
                                    }
                                } else if (opcaoExcluir == 2) {
                                    animalDAO.listarEspecie("Gato");
                                    System.out.println("Informe o nome do animal que deseja excluir: ");
                                    String nomeExcluir = scanner.next();
                                    animalDAO.listarAnimal(nomeExcluir);
                                    System.out.println("Excluir cadastro?");
                                    System.out.println("1 -- sim");
                                    System.out.println("2 -- não");
                                    int excluir = scanner.nextInt();

                                    if (excluir == 1) {
                                        animalDAO.excluir(nomeExcluir);
                                    } else {
                                        System.out.println("Cancelado!");
                                        menuPrincipal();
                                    }
                                } else if (opcaoExcluir == 3) {
                                    animalDAO.listarEspecie("Passaro");
                                    System.out.println("Informe o nome do animal que deseja excluir: ");
                                    String nomeExcluir = scanner.next();
                                    animalDAO.listarAnimal(nomeExcluir);
                                    System.out.println("Excluir cadastro?");
                                    System.out.println("1 -- sim");
                                    System.out.println("2 -- não");
                                    int excluir = scanner.nextInt();

                                    if (excluir == 1) {
                                        animalDAO.excluir(nomeExcluir);
                                    } else {
                                        System.out.println("Cancelado!");
                                        menuPrincipal();
                                    }
                                }
                                menuPrincipal();
                            }
                            case 3 -> {
                                System.out.println("========== EDITAR CADASTRO ==========");
                                animalDAO.listar();
                                System.out.println("digite o nome do animal que deseja editar: ");
                                String selecionaAnimalEditar = scanner.next();
                                System.out.println("nome: ");
                                String nomeEditar = scanner.next();
                                System.out.println("idade: ");
                                int idadeEditar = scanner.nextInt();
                                System.out.println("selecione um tutor");
                                pessoaDAO.listar(1);
                                System.out.println("informe o nome do novo Tutor:");
                                String nomeTutor = scanner.next();
                                System.out.println("informe a especie de animal");
                                System.out.println("1 - cachorro");
                                System.out.println("2 - Gato");
                                System.out.println("3 - passaro");
                                int opcaoAnimalEditar = scanner.nextInt();

                                if (opcaoAnimalEditar == 1) {
                                    animalDAO.editar(selecionaAnimalEditar, nomeEditar, "cachorro", idadeEditar, 1, nomeTutor);
                                } else if (opcaoAnimalEditar == 2) {
                                    animalDAO.editar(selecionaAnimalEditar, nomeEditar, "Gato", idadeEditar, 2, nomeTutor);
                                } else if (opcaoAnimalEditar == 3) {
                                    animalDAO.editar(selecionaAnimalEditar, nomeEditar, "Passaro", idadeEditar, 3, nomeTutor);
                                }
                                menuPrincipal();
                            }
                            case 4 -> menuPrincipal();
                        }
                    }
                    case 2 -> {
                        System.out.println("========== CADASTRO DE PESSOA ==========");
                        System.out.println("1 -- cadastrar Pessoa");
                        System.out.println("2 -- excluir cadastro");
                        System.out.println("3 -- editar cadastro");
                        System.out.println("4 -- voltar");
                        System.out.println("escolha uma opção: ");
                        int opcaoCadastroPessoa = scanner.nextInt();
                        switch (opcaoCadastroPessoa) {
                            case 1 -> {
                                System.out.println("========== CADASTRO DE PESSOA ==========");
                                System.out.println("Nome: ");
                                String nome = scanner.next();
                                System.out.println("Documento: ");
                                int doc = scanner.nextInt();
                                System.out.println("escolha oque deseja cadastrar");
                                System.out.println("1 - Tutor");
                                System.out.println("2 - Funcionario");
                                int opcaoCadastroTipoPessoa = scanner.nextInt();

                                try {

                                    if (opcaoCadastroTipoPessoa == 1) {

                                        //cadastra um novo Tutor criando um tutor e em seguida inserindo na coleção pessoas
                                        Tutor cadastroTutor = new Tutor(nome, doc, opcaoCadastroTipoPessoa);
                                        pessoaDAO.inserir(cadastroTutor); // insere um Tutor na coleção pessoas

                                    } else if (opcaoCadastroTipoPessoa == 2) {

                                        //cadastra um novo funcionario criando um funcionario e em seguida inserindo na coleção pessoas
                                        Funcionario cadastroFunc = new Funcionario(nome, doc, opcaoCadastroTipoPessoa);
                                        pessoaDAO.inserir(cadastroFunc); // insere um funcionario na coleção pessoas

                                    }
                                }finally {
                                    menuPrincipal();
                                }
                            }
                            case 2 -> {
                                System.out.println("========== EXCLUIR CADASTRO ==========");
                                System.out.println("1 - Tutor");
                                System.out.println("2 - Funcionario");
                                int opcaoExcluir = scanner.nextInt();
                                if (opcaoExcluir == 1) {
                                    pessoaDAO.listar(1);
                                    System.out.print("digite o nome da pessoa que deseja excluir: ");
                                    String nomeTutorExcluir = scanner.next();
                                    pessoaDAO.excluir(nomeTutorExcluir);
                                } else if (opcaoExcluir == 2) {
                                    pessoaDAO.listar(2);
                                    System.out.print("digite o nome da pessoa que deseja excluir: ");
                                    String nomeTutorExcluir = scanner.next();
                                    pessoaDAO.excluir(nomeTutorExcluir);
                                }
                                menuPrincipal();
                            }
                            case 3 -> {
                                System.out.println("========== EDITAR CADASTRO ==========");
                                System.out.println("informe o tipo de cadastro que deseja editar");
                                System.out.println("1 -- Tutor");
                                System.out.println("2 -- Funcionario");
                                int tipoEditar = scanner.nextInt();
                                if (tipoEditar == 1) {
                                    pessoaDAO.listar(1);
                                    System.out.println("digite o nome da pessoa que deseja editar: ");
                                    String nomeantigo = scanner.next();
                                    System.out.println("novo nome: ");
                                    String nomeEditar = scanner.next();
                                    System.out.println("documento: ");
                                    int docEditar = scanner.nextInt();
                                    System.out.println("novo tipo: ");
                                    int novotipo = scanner.nextInt();
                                    pessoaDAO.editar(nomeantigo, nomeEditar, docEditar, novotipo);

                                } else if (tipoEditar == 2) {
                                    pessoaDAO.listar(2);
                                    System.out.println("digite o nome da pessoa que deseja editar: ");
                                    String nomeantigo = scanner.next();
                                    System.out.println("novo nome: ");
                                    String nomeEditar = scanner.next();
                                    System.out.println("documento: ");
                                    int docEditar = scanner.nextInt();
                                    System.out.println("novo tipo: ");
                                    int novotipo = scanner.nextInt();

                                    pessoaDAO.editar(nomeantigo, nomeEditar, docEditar, novotipo);

                                }
                                menuPrincipal();
                            }
                            case 4 -> menuPrincipal();
                        }
                    }
                }
            }
            case 4 -> System.exit(0);
        }
    }

}
