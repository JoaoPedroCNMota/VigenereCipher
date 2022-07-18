package VigenereCipher;

import java.util.Scanner;

public class VigenereLauncher {

    private static Scanner scanner;

    public static void main(String[] args) {
        int input;
        do {
            init();
            input = scanner.nextInt();
            getUserOption(input);
        }while(input != 0);
    }

    private static void init(){
        scanner = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("=====================================================================");
        System.out.println(
                "░█▀▀░▀█▀░█▀▀░█▀▄░█▀█░█▀▄░█▀█░█▀▄    ░█░█░▀█▀░█▀▀░█▀▀░█▀█░█▀▀░█▀▄░█▀▀\n" +
                "░█░░░░█░░█▀▀░█▀▄░█▀█░█░█░█░█░█▀▄    ░▀▄▀░░█░░█░█░█▀▀░█░█░█▀▀░█▀▄░█▀▀\n" +
                "░▀▀▀░▀▀▀░▀░░░▀░▀░▀░▀░▀▀░░▀▀▀░▀░▀    ░░▀░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀▀▀"
        );
        System.out.println("=====================================================================");
        System.out.println("1 - CIFRAR");
        System.out.println("2 - DECIFRAR");
        System.out.println("2 - ENCERRAR");
        System.out.println("=====================================================================");
    }

    private static void getUserOption(int option){
        switch (option){
            case 1:
                System.out.println("CIFRANDO..");
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
