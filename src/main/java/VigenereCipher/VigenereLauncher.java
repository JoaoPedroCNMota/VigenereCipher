package VigenereCipher;

import VigenereCipher.Objects.ProbableKeySize;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class VigenereLauncher {

    private static Scanner scanner = new Scanner(System.in);
    private static VigenereCore core = new VigenereCore();
    private static VigenereCrack crack = new VigenereCrack();

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
        System.out.println("2 - DECIFRAR COM CHAVE");
        System.out.println("3 - DECIFRAR SEM CHAVE");
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
                while(userNavOption.equalsIgnoreCase("1")){
                    System.out.println("INSIRA A MENSAGEM A SER CIFRADA.");
                    String userMessage = VigenereUtils.inicialFormat(scanner.nextLine().toUpperCase());

                    System.out.println("INSIRA UMA SENHA.");
                    String userKey = scanner.nextLine().toUpperCase();

                    System.out.println();
                    System.out.println("MENSAGEM CIFRADA: \n" + core.encryt(userMessage, userKey));
                    System.out.println();

                    System.out.println("=====================================================================");
                    System.out.println("    [ 1 ]           - CIFRAR NOVA MENSAGEM");
                    System.out.println("[QUALQUER TECLA]    - VOLTAR");
                    System.out.println("=====================================================================");
                    userNavOption = !scanner.next().equalsIgnoreCase("1") ? "0" : "1";
                    scanner.nextLine();
                    clearConsole();
                }
                break;

            case 2:
                while(userNavOption.equalsIgnoreCase("1")){
                    System.out.println("INSIRA A MENSAGEM CIFRADA.");
                    String encryptedMessage = VigenereUtils.inicialFormat(scanner.nextLine().toUpperCase());

                    System.out.println("INSIRA A SENHA DA MENSAGEM CIFRADA.");
                    String messageKey = scanner.nextLine().toUpperCase();

                    System.out.println();
                    System.out.println("MENSAGEM DECIFRADA: \n" + core.decrypt(encryptedMessage, messageKey));
                    System.out.println();

                    System.out.println("=====================================================================");
                    System.out.println("    [ 1 ]           - CIFRAR NOVA MENSAGEM");
                    System.out.println("[QUALQUER TECLA]    - VOLTAR");
                    System.out.println("=====================================================================");
                    userNavOption = !scanner.next().equalsIgnoreCase("1") ? "0" : "1";
                    scanner.nextLine();
                    clearConsole();
                }
                break;

            case 3:
                while(userNavOption.equalsIgnoreCase("1")){
                    System.out.println("INSIRA A MENSAGEM CIFRADA.");
                    String encryptedMessage = VigenereUtils.inicialFormat(scanner.nextLine().toUpperCase());

                    List<ProbableKeySize> probableKeySizes = crack.getProbableKeyLenghts(encryptedMessage);

                    System.out.println();
                    probableKeySizes.forEach(x -> {
                        System.out.println("TAMANHO DA CHAVE: " + x.getKeySize() + "   | QTD DE POSICOES DIVISORAS: " + x.getCountPositionDivisors());
                    });
                    System.out.println();

                    String userKeyOption = "0";
                    while (userKeyOption == "0"){
                        System.out.println();
                        System.out.println("1 - DEIXAR O SISTEMA AGIR AUTOMATICAMENTE");
                        System.out.println("2 - ESCOLHER O TAMANHO DA CHAVE MANUALMENTE");
                        userKeyOption = scanner.nextLine();
                        if (!userKeyOption.equalsIgnoreCase("1") && !userKeyOption.equalsIgnoreCase("2")){
                            userKeyOption = "0";
                        }
                    }

                    Integer keySize;
                    if (userKeyOption.equalsIgnoreCase("1")){
                        keySize = crack.automaticChooseKeySize(probableKeySizes);
                        if (keySize == null || keySize == 0){
                            System.out.println();
                            System.out.println("O SISTEMA NAO CONSEGUIU ENCONTRAR UM TAMANHO PROVAVEL DE CHAVE.");
                            System.out.println("INFORME MANUALMENTE UM TAMANHO DE CHAVE COM BASE NAS INFORMACOES ACIMA.");
                            keySize = Integer.valueOf(scanner.nextLine());
                        }
                    }else{
                        System.out.println("INFORME MANUALMENTE UM TAMANHO DE CHAVE COM BASE NAS INFORMACOES ACIMA.");
                        keySize = Integer.valueOf(scanner.nextLine());
                    }

                    System.out.println();
                    System.out.println("IDIOMA DA MENSAGEM:");
                    System.out.println("1 - PORTUGUES");
                    System.out.println("2 - INGLES");
                    Integer languageOption = Integer.valueOf(scanner.nextLine());

                    String probableKey = crack.findKey(encryptedMessage, keySize, languageOption);
                    System.out.println();
                    System.out.println("CHAVE PROVAVEL: " + probableKey);
                    System.out.println();

                    System.out.println("=====================================================================");
                    System.out.println("    [ 1 ]           - CIFRAR NOVA MENSAGEM");
                    System.out.println("[QUALQUER TECLA]    - VOLTAR");
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
