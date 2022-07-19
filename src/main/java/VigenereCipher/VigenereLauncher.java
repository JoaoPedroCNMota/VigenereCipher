package VigenereCipher;

import java.util.Scanner;

public class VigenereLauncher {

    private static Scanner scanner;
    private static VigenereCore core;

    public static void main(String[] args) {
        int input;
        do {
            init();
            input = scanner.nextInt();
            getUserOption(input);
        }while(input != 0);
    }

    private static void init(){
        clearConsole();
        scanner = new Scanner(System.in);
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
        switch (option){
            case 1:
                System.out.println("INSIRA A MENSAGEM A SER CIFRADA..");
                String userMessage = scanner.nextLine();
                System.out.println("INSIRA SUA CHAVE");
                String userKey = scanner.nextLine();
                core.encryt();
                break;

            case 2:
                System.out.println("DECIFRANDO..");
                break;
            case 0:
                scanner.close();
                break;

            default:
                init();
        }
    }
}
