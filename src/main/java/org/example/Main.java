package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ProductDAO productDAO = new ProductDAO();

    public static void main(String[] args) {

        while (true) {

            //Menu do programa
            System.out.println("\n----- MENU -----");
            System.out.println("1. Cadastro de produto");
            System.out.println("2. Listar ou buscar produtos");
            System.out.println("3. Editar produto");
            System.out.println("4. Excluir produtos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    System.out.println("\n--- Deseja listar ou buscar produtos? ---");
                    System.out.println("1. Listar produtos");
                    System.out.println("2. Buscar produto");
                    System.out.println("3. Retornar ao menu");
                    System.out.print("Escolha uma opção: ");

                    int escolha2 = scanner.nextInt();
                    scanner.nextLine();

                    switch (escolha2) {
                        case 1:
                            listarProdutos();
                            break;

                        case 2:
                            buscarProduto();
                            break;

                        case 3:
                            System.out.println("Retornando ao menu.");
                            break;

                        default:
                            System.out.println("Opção inválida, tente novamente.");
                    }
                    break;

                case 3:
                    atualizarProduto();
                    break;

                case 4:
                    excluirProduto();
                    break;

                case 5:
                    System.out.println("Encerrando.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // 1. CREATE - Adicionar novos produtos
    private static void cadastrarProduto() {

        System.out.println("\n--- Adicionando Produtos ---");
        System.out.print("Insira o nome do produto: ");
        String name = scanner.nextLine();

        System.out.print("Insira o valor do produto: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Insira a quantidade em estoque: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        //Construtor para adicionar novo produto ao banco de dados
        productDAO.addProduct(new Product(name, price, quantity));

    }

    // 2. READ - Listar todos os produtos
    private static void listarProdutos() {

        System.out.println("\n--- Lista de Produtos ---");

        //Cria uma lista de produtos
        List<Product> products = productDAO.getAllProducts();

        //Exibe todos os produtos da lista
        products.forEach(System.out::println);

    }

    // 3. READ - Buscar um produto por ID
    private static void buscarProduto(){

        System.out.println("\n--- Buscando Produto por ID ---");
        System.out.print("Insira o ID do produto: ");
        int id = scanner.nextInt();

        //Verifica os produtos pelo ID e instancia um objeto para ser exibido
        Product productFound = productDAO.getProductById(id);

        if (productFound != null) {
            System.out.println("Produto encontrado: " + productFound);
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }

    }

    // 4. UPDATE - Atualizar um produto
    private static void atualizarProduto() {

        System.out.println("\n--- Atualizando Produto ---");

        boolean productExists = false;
        int id = 0;

        //Mostrar qual o produto referente ao ID informado antes da exclusão.
        //Se o produto com o ID especificado não existir, recomeça o processo de exclusão.
        while (!productExists) {

            System.out.print("Insira o ID do produto: ");
            id = scanner.nextInt();
            scanner.nextLine();

            Product productFound = productDAO.getProductById(id);

            if (productFound != null) {
                System.out.println("Produto selecionado: " + productFound);
                productExists = true;
            } else {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }

        }

        //Atualização do produto
        System.out.print("Insira o novo nome do produto: ");
        String name = scanner.nextLine();

        System.out.print("Insira o novo preço do produto: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Insira a quantidade em estoque: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product productToUpdate = new Product(id, name, price, quantity);
        productDAO.updateProduct(productToUpdate);

        //Listar os produtos
        System.out.println("\n--- Lista de Produtos após Atualização ---");
        productDAO.getAllProducts().forEach(System.out::println);

    }

    // 5. DELETE - Excluir um produto
    private static void excluirProduto() {

        System.out.println("\n--- Excluindo Produto ---");

        boolean productExists = false;
        int id = 0;

        //Mostrar qual o produto referente ao ID informado antes da exclusão.
        //Se o produto com o ID especificado não existir, recomeça o processo de exclusão.
        while (!productExists) {

            System.out.print("Insira o ID do produto: ");
            id = scanner.nextInt();
            scanner.nextLine();

            Product productFound = productDAO.getProductById(id);

            if (productFound != null) {
                System.out.println("Produto " + id + ": " + productFound);
                productExists = true;
            } else {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }

        }

        productDAO.deleteProduct(id);

        //Listar os produtos
        System.out.println("\n--- Lista de Produtos após Exclusão ---");
        productDAO.getAllProducts().forEach(System.out::println);

    }
}