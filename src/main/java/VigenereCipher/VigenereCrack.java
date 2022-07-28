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

        String encryptedMessageWithoutSpaces = encryptedMessage.replaceAll("\\s", "").toUpperCase();
        List<Integer> spacesPosition = getSpacesIndex(encryptedMessage);

        List<String> trigrams = splitsEncryptedLetters(encryptedMessageWithoutSpaces);
        HashMap<String, List<Integer>> trigramPositions = new HashMap<>();

        trigrams.forEach(tri -> {
            //verifica quantidade de aparicoes na cifra
            //incluindo a frequencia de aparicao (quantas casas anda ate aparecer denovo)
            //https://crypto-stackexchange-com.translate.goog/questions/73051/can-frequency-attack-be-successful-on-vigen%C3%A8re-cipher?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt-BR&_x_tr_pto=wapp

            List<Integer> positions = new ArrayList<>();
            int index = encryptedMessageWithoutSpaces.indexOf(tri);
            while (index != -1){
                positions.add(index);
                index = encryptedMessageWithoutSpaces.indexOf(tri, index + 1);
            }
            if (positions.size() > 1){
                List<Integer> calcPositions = calculatePositions(positions);
                trigramPositions.put(tri, calcPositions);
            }
        });

        return null;
    }

    private static List<Integer> calculatePositions(List<Integer> positions) {
        List<Integer> calcPositions = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            if (i < positions.size()){
                calcPositions.add(positions.get(i + 1) - positions.get(i));
            }
        }
        return calcPositions;
    }

    private static List<String> splitsEncryptedLetters(String encryptedMessage) {
        List<String> trigrams = new ArrayList<>();
        for (int i = 0; i < encryptedMessage.length(); i += SPLIT_LEN) {
            trigrams.add(encryptedMessage.substring(i, Math.min(encryptedMessage.length(), i + SPLIT_LEN)));
        }
        return trigrams;
    }

    private static List<Integer> getSpacesIndex(String encryptedMessage) {
        return  null;
    }

    public static void main(String[] args) {
        attack("Vyc qxgv gpdzfcdq pks yigimsp jm iaov tmbiivzlv foeygcxg erl exfhfpb mvgd ojbqmcw pgr uf rwth jlkpg pgzlvl qce scwstjrpgr vycb vzgrpar. O rimvkoodcg bg kucpeza rl tlgcpghm kjf udkyu ngia htrbxmwqeya tsukftmwe rls ewvvppkm hfpbl ou ncae ou dyiasorrxvon tmcvsrkq, ih qqdkjgwerrt mvg nyn mvck yc tzifpxmvo nmgdg ceb ih qqetxgqg r pttrgi rwth vyc gxgwcrh pwnc zt vcticrm");
    }

}
