package main;

import modelo.Produto;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Mercado {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String args[]) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();

    }

    private static void menu() {
        System.out.println("------------------------------------------------------");
        System.out.println("--------------------Welcome to John-------------------");
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operacao que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("opcao 1 - cadastrar   |");
        System.out.println("opcao 2 - Listar      |");
        System.out.println("opcao 3 - Comprar     |");
        System.out.println("opcao 4 - Carrinho    |");
        System.out.println("opcao 5 - Sair        |");

        int option = input.nextInt();
        //adicionar o dado que usuario informar

        switch (option) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Obrigado pela preferencia");
                System.exit();
            default:
                System.out.println("opção inválida");
                menu();
                break;
        }
    }

    // cadastro de produto

    private static void cadastrarProdutos() {
        System.out.println("nome do produto");
        String nome = input.next();

        System.out.println("Preço do produto");
        Double preco = input.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + "cadastrado com sucesso");
        menu();
    }

    private static void listarProdutos() {
        if (produtos.size() > 0) {
            System.out.println("Lista de produtos!\n");

            for (Produto p : produtos) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhum produto cadastrado");
        }
        menu();
    }

    private static void comprarProdutos() {
        if (produtos.size() > 0) {
            System.out.println("codigo do produto: \n");

            System.out.println("----------------Produtos disponiveis-----------");
            for (Produto p : produtos) {
                System.out.println(p + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresent = false;

            for (Produto p : produtos) {
                if (p.getId() == id) {
                    int qtd = 0;
                    try {
                        qtd = carrinho.get(p);
                        // checa se o produto ja está  no carrinho , incrementa qtd
                        carrinho.put(p, qtd + 1);
                    } catch (NullPointerException e) {
                        // se o produto for o primeiro no carrinho
                        carrinho.put(p, 1);

                    }
                    System.out.println(p.getNome() + "adicionado ao carrinho");
                    isPresent = true;

                    if (isPresent) {
                        System.out.println("deseja adicionar outro produto ao carrinho");
                        System.out.println("digite 1 para sim ou 0 para finalizar a compra \n");
                        int option = Integer.parseInt(input.next());


                        if (option == 1) {
                            comprarProdutos();
                        } else {
                            finalizarCompra();
                        }
                    }
                }else {
                    System.out.println("Produto nao encontrado");
                    menu();
                }
            }
        } else {

        }
    }

}
