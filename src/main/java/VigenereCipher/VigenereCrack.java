package VigenereCipher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VigenereCrack {

    public static final int SPLIT_LEN = 3;

    public static String attack(String encryptedMessage){
        if (encryptedMessage == null){
            return null;
        }

        List<String> trigrams = splitsEncryptedLetters(encryptedMessage.replaceAll("\\s", ""));
        HashMap<String, Integer> trigramPositionCount = new HashMap<>();

        trigrams.forEach(tri -> {
            //verifica quantidade de aparicoes na cifra
            //incluindo a frequencia de aparicao (quantas casas anda ate aparecer denovo)

            //https://crypto-stackexchange-com.translate.goog/questions/73051/can-frequency-attack-be-successful-on-vigen%C3%A8re-cipher?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt-BR&_x_tr_pto=wapp
            if (encryptedMessage.contains(tri)){                                                    //"ABC"
                trigramPositionCount.put(tri, encryptedMessage.indexOf(tri));
            }
            if (encryptedMessage.contains(tri.substring(0, 1) + " " + tri.substring(1))){ //"A_BC"
                String seq = tri.substring(0, 1) + " " + tri.substring(1);
                trigramPositionCount.put(tri, encryptedMessage.indexOf(seq));
            }
            if (encryptedMessage.contains(tri.substring(0, 2) + " " + tri.substring(2))){ //"AB_C"
                String seq = tri.substring(0, 2) + " " + tri.substring(2);
                trigramPositionCount.put(tri, encryptedMessage.indexOf(seq));
            }

        });
        System.out.println(trigramPositionCount);
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
        attack("XPTGFY HGSJAKNXPT");
    }




}
