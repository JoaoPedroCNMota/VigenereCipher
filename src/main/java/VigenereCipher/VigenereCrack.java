package VigenereCipher;

import java.util.ArrayList;
import java.util.List;

public class VigenereCrack {

    public static final int SPLIT_LEN = 3;

    public static String attack(String encryptedMessage){
        if (encryptedMessage == null){
            return null;
        }

        List<String> trigrams = splitsEncryptedLetters(encryptedMessage.replaceAll("\\s", ""));
        trigrams.forEach(tri -> {
            //verifica quantidade de aparicoes na cifra
            //incluindo a frequencia de aparicao (quantas casas anda ate aparecer denovo)
            //

            if (encryptedMessage.contains(tri)){        //"ABC"

            } else if (encryptedMessage.contains(tri.substring(0, 1) + " " + tri.substring(1))){ //"A_BC"

            } else if (encryptedMessage.contains(tri.substring(0, 2) + " " + tri.substring(2))){ //"AB_C"

            }

        });
        
        return null;
    }

    private static List<String> splitsEncryptedLetters(String encryptedMessage) {
        List<String> trigrams = new ArrayList<>();
        for (int i = 0; i < encryptedMessage.length(); i += SPLIT_LEN) {
            trigrams.add(encryptedMessage.substring(i, Math.min(encryptedMessage.length(), i + SPLIT_LEN)));
        }
        return trigrams;
    }

    public static void main(String[] args) {
        attack("alo alo alo alo al");
    }

}
