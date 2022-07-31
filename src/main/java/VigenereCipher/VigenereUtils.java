package VigenereCipher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VigenereUtils {

    public static String inicialFormat(String message){
        return message.replaceAll("[^a-zA-Z ]", "")
                .toUpperCase();
    }

    public static String formatMessage(String message){
        return message.replaceAll("[^a-zA-Z ]", "")
                .replaceAll("\\s", "")
                .toUpperCase();
    }

    public static List<Integer> getSpacesIndex(String encryptedMessage) {
        List<Integer> spacesIndex = new ArrayList<>();
        int index = encryptedMessage.indexOf(" ");
        while (index != -1){
            spacesIndex.add(index);
            index = encryptedMessage.indexOf(" ", index + 1);
        }
        return spacesIndex;
    }

    public static HashMap<String, Double> getEnglishFrequencyLetters (){
        HashMap<String, Double> charMap = new HashMap<>();
        charMap.put("A", 8.167);
        charMap.put("B", 1.492);
        charMap.put("C", 2.782);
        charMap.put("D", 4.253);
        charMap.put("E", 12.702);
        charMap.put("F", 2.228);
        charMap.put("G", 2.015);
        charMap.put("H", 6.094);
        charMap.put("I", 6.966);
        charMap.put("J", 0.153);
        charMap.put("K", 0.772);
        charMap.put("L", 4.025);
        charMap.put("M", 2.406);
        charMap.put("N", 6.749);
        charMap.put("O", 7.507);
        charMap.put("P", 1.929);
        charMap.put("Q", 0.095);
        charMap.put("R", 5.987);
        charMap.put("S", 6.327);
        charMap.put("T", 9.056);
        charMap.put("U", 2.758);
        charMap.put("V", 0.978);
        charMap.put("W", 2.360);
        charMap.put("X", 0.150);
        charMap.put("Y", 1.974);
        charMap.put("Z", 0.074);
        return charMap;
    };

    public static HashMap<String, Double> getPortugueseFrequencyLetters (){
        HashMap<String, Double> charMap = new HashMap<>();
        charMap.put("A", 14.63);
        charMap.put("B", 1.04);
        charMap.put("C", 3.88);
        charMap.put("D", 4.99);
        charMap.put("E", 12.57);
        charMap.put("F", 1.02);
        charMap.put("G", 1.30);
        charMap.put("H", 1.28);
        charMap.put("I", 6.18);
        charMap.put("J", 0.40);
        charMap.put("K", 0.02);
        charMap.put("L", 2.78);
        charMap.put("M", 4.74);
        charMap.put("N", 5.05);
        charMap.put("O", 10.73);
        charMap.put("P", 2.52);
        charMap.put("Q", 1.20);
        charMap.put("R", 6.53);
        charMap.put("S", 7.81);
        charMap.put("T", 4.34);
        charMap.put("U", 4.63);
        charMap.put("V", 1.67);
        charMap.put("W", 0.01);
        charMap.put("X", 0.21);
        charMap.put("Y", 0.01);
        charMap.put("Z", 0.47);
        return charMap;
    };

    public static HashMap<String, Double> frequencyLetters (){
        HashMap<String, Double> charMap = new HashMap<>();
        charMap.put("A", 0.0);
        charMap.put("B", 0.0);
        charMap.put("C", 0.0);
        charMap.put("D", 0.0);
        charMap.put("E", 0.0);
        charMap.put("F", 0.0);
        charMap.put("G", 0.0);
        charMap.put("H", 0.0);
        charMap.put("I", 0.0);
        charMap.put("J", 0.0);
        charMap.put("K", 0.0);
        charMap.put("L", 0.0);
        charMap.put("M", 0.0);
        charMap.put("N", 0.0);
        charMap.put("O", 0.0);
        charMap.put("P", 0.0);
        charMap.put("Q", 0.0);
        charMap.put("R", 0.0);
        charMap.put("S", 0.0);
        charMap.put("T", 0.0);
        charMap.put("U", 0.0);
        charMap.put("V", 0.0);
        charMap.put("W", 0.0);
        charMap.put("X", 0.0);
        charMap.put("Y", 0.0);
        charMap.put("Z", 0.0);
        return charMap;
    };

}
