package VigenereCipher;

import java.util.Locale;
import java.util.Scanner;

public class VigenereLauncher {

    private static Scanner scanner = new Scanner(System.in);
    private static VigenereCore core = new VigenereCore();

    public static void main(String[] args) {
        int input;
        scanner.useLocale(Locale.US);
        do {
            init();
            input = scanner.nextInt();
            scanner.nextLine(); //clear scanner buffer;
            getUserOption(input);
        }while(input != 0);
    }

    private static void init(){
        System.out.println("=====================================================================");
        System.out.println("""
                ░█▀▀░▀█▀░█▀▀░█▀▄░█▀█░█▀▄░█▀█░█▀▄    ░█░█░▀█▀░█▀▀░█▀▀░█▀█░█▀▀░█▀▄░█▀▀
                ░█░░░░█░░█▀▀░█▀▄░█▀█░█░█░█░█░█▀▄    ░▀▄▀░░█░░█░█░█▀▀░█░█░█▀▀░█▀▄░█▀▀
                ░▀▀▀░▀▀▀░▀░░░▀░▀░▀░▀░▀▀░░▀▀▀░▀░▀    ░░▀░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀▀▀
                """);
        System.out.println("=====================================================================");
        System.out.println("1 - CIFRAR");
        System.out.println("2 - DECIFRAR");
        System.out.println("0 - ENCERRAR");
        System.out.println("=====================================================================");
    }

    private static void clearConsole(){
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    private static void getUserOption(int option){
        String userNavOption = "1";
        switch (option){
            case 1:
                while(userNavOption == "1"){
                    System.out.println("INSIRA A MENSAGEM A SER CIFRADA.");
                    String userMessage = scanner.nextLine().toLowerCase();
                    System.out.println("INSIRA SUA CHAVE.");
                    String userKey = scanner.nextLine().toLowerCase();
                    System.out.println("MENSAGEM CIFRADA: " + core.encryt(userMessage, userKey));
                    System.out.println();
                    System.out.println("=====================================================================");
                    System.out.println("1 - CIFRAR NOVA MENSAGEM");
                    System.out.println("=====================================================================");
                    userNavOption = !scanner.next().equalsIgnoreCase("1") ? "0" : "1";
                    scanner.nextLine();
                    clearConsole();
                }
                break;

            case 2:
                while(userNavOption == "1"){
                    System.out.println("INSIRA A MENSAGEM CIFRADA.");
                    String userMessage = scanner.nextLine();
                    System.out.println("INSIRA A CHAVE DA MENSAGEM CIFRADA.");
                    String userKey = scanner.nextLine();
                    System.out.println("MENSAGEM DECIFRADA: " + core.encryt(userMessage, userKey));
                    System.out.println();
                    System.out.println("=====================================================================");
                    System.out.println("1 - DECIFRAR NOVA MENSAGEM");
                    System.out.println("=====================================================================");
                    userNavOption = !scanner.next().equalsIgnoreCase("1") ? "0" : "1";
                    scanner.nextLine();
                    clearConsole();
                }
                break;

            case 0:
                scanner.close();
                break;

            default:
                init();
        }
    }
}
