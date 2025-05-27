package org.example;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ProductDAO productDAO = new ProductDAO();

    public static void main(String[] args) {

        while (true) {

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

        productDAO.addProduct(new Product(name, price));

    }

    // 2. READ - Listar todos os produtos
    private static void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Product> products = productDAO.getAllProducts();
        products.forEach(System.out::println);
    }

    // 3. READ - Buscar um produto por ID
    private static void buscarProduto(){

        System.out.println("\n--- Buscando Produto por ID ---");
        System.out.print("Insira o ID do produto: ");
        int id = scanner.nextInt();

        Product productFound = productDAO.getProductById(id);

        if (productFound != null) {
            System.out.println("Produto encontrado: " + productFound);
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }

    }

    // 4. UPDATE - Atualizar um produto (supondo que o Mouse tenha ID 2)
    private static void atualizarProduto() {

        System.out.println("\n--- Atualizando Produto ---");
        System.out.print("Insira o ID do produto: ");
        int id = scanner.nextInt();

        Product productToUpdate = new Product(id,
                "Mouse Sem Fio",
                199.99);

        productDAO.updateProduct(productToUpdate);

        System.out.println("\n--- Lista de Produtos após Atualização ---");
        productDAO.getAllProducts().forEach(System.out::println);

    }

    // 5. DELETE - Excluir um produto (supondo que o Teclado Mecânico tenha ID 3)
    private static void excluirProduto() {
        System.out.println("\n--- Excluindo Produto (ID 3) ---");
        productDAO.deleteProduct(3);

        System.out.println("\n--- Lista de Produtos após Exclusão ---");
        productDAO.getAllProducts().forEach(System.out::println);

        // Tentando excluir um produto que não existe
        System.out.println("\n--- Tentando Excluir Produto Inexistente (ID 99) ---");
        productDAO.deleteProduct(99);
    }
}