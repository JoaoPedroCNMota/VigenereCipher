package VigenereCipher;

import VigenereCipher.Objects.NgramPositions;
import VigenereCipher.Objects.ProbableKeySize;

import java.util.*;
import java.util.stream.Collectors;

public class VigenereCrack {

    public static final int SPLIT_LEN = 3;          //tamanho dos n-gramas;
    public static final int MAX_KEY_SIZE = 45;      //tamanho maximo de chave;

    /* findKey -> retorna a chave provavel a partir da mensagem cifrada e tamanho da chave */
    public String findKey(String encryptedMessage,Integer keySize,Integer languageOption ){
        String encryptedMessageWithoutSpaces = VigenereUtils.formatMessage(encryptedMessage);

        HashMap<String, Double> frequencyLanguage = languageOption == 1 ?
                VigenereUtils.getPortugueseFrequencyLetters() : VigenereUtils.getEnglishFrequencyLetters();

        HashMap<String, Double> encryptedFrequencyLetters = VigenereUtils.frequencyLetters();

        char[] probableKey = new char[keySize]; //charArray para armazenar possivel chave. Array pois facilita possiveis atualizacoes de posicao;

        Integer wordsPerGoup = encryptedMessageWithoutSpaces.length() / keySize;    //quantidade de palavras por grupo
        double percentLetterPerGrop = 100/wordsPerGoup.doubleValue();               //valor percentual de cada letra dentro de um grupo

        for (int i = 0; i < keySize; i++) {

            encryptedFrequencyLetters.entrySet().forEach(m -> m.setValue(0.0));; //limpa valores do map de frequencia;

            for (int j = 0; j < wordsPerGoup; j++) {
                String letter = String.valueOf(encryptedMessageWithoutSpaces.charAt((j*keySize)+i));
                encryptedFrequencyLetters.put(letter, encryptedFrequencyLetters.get(letter) + percentLetterPerGrop); //armazena percentual de letras de acordo com cada ocorrencia;
            }

            double min = 100_000; //valor inicial para garantir atualizacao na primeira iteracao;
            for (int j = 0; j < 26; j++) { //letras do alfabeto
                double totalDif = 0;
                int index = 0;

                for (int c = 'A'; c <= 'Z'; c++) { //de A a Z, calculando a diferença entre as frequencias, index faz as 'rotações' para comparar as diferentes frequencias

                    if (c + j > 'Z'){ //define index pro inicio do alfabeto
                        index = c + j - 26;
                    }else {
                        index = c + j;
                    }

                    String charC = String.valueOf((char) c);
                    String charIndex = String.valueOf((char) index);
                    totalDif += frequencyLanguage.get(charC) > encryptedFrequencyLetters.get(charIndex)  ?
                            (frequencyLanguage.get(charC) - encryptedFrequencyLetters.get(charIndex)) :
                            (encryptedFrequencyLetters.get(charIndex) - frequencyLanguage.get(charC));
                }
                if (totalDif < min){
                    min = totalDif;
                    probableKey[i] = ((char) ('A' + j));
                }
            }
        }

        return String.valueOf(probableKey);
    }

    /* getProbableKeyLenghts -> retorna o tamanho provavel da chave  a partir da mensagem cifrada */
    public List<ProbableKeySize> getProbableKeyLenghts(String encryptedMessage){
        if (encryptedMessage == null){
            return null;
        }

        String encryptedMessageWithoutSpaces = VigenereUtils.formatMessage(encryptedMessage);
        List<String> trigrams = splitsEncryptedLetters(encryptedMessageWithoutSpaces);

        List<NgramPositions> ngramPositions = new ArrayList<>(); //objeto que para guardar o trigrama e suas posicoes ja calculadas;
        trigrams.forEach(tri -> {
            //https://crypto.stackexchange.com/questions/73051/can-frequency-attack-be-successful-on-vigen%C3%A8re-cipher
            List<Integer> positions = new ArrayList<>();
            int index = encryptedMessageWithoutSpaces.indexOf(tri);
            while (index != -1){
                positions.add(index);
                index = encryptedMessageWithoutSpaces.indexOf(tri, index + 1);
            }
            if (positions.size() > 1){
                List<Integer> calcPositions = calculatePositions(positions);
                ngramPositions.add(new NgramPositions(tri, calcPositions));
            }
        });

        return calculateProbableKeyLenght(ngramPositions); //Map representando o tamanho da chave e a probabilidade;
    }

    /* automaticChooseKeySize -> escolhe automaticamente a chave considerada de maior probabilidade entre a lista de chaves provaveis */
    public Integer automaticChooseKeySize(List<ProbableKeySize> probableKeySizes) {
        return probableKeySizes.stream().max(
                Comparator.comparing(ProbableKeySize::getCountPositionDivisors)
                        .thenComparing(ProbableKeySize::getKeySize)
        ).map(ProbableKeySize::getKeySize).orElse(0);
    }

    /* calculateProbableKeyLenght ->  calcula MDC das posicoes e conta quantidade de posicoes divisiveis, gerando assim o provavel tamanho da chave */
    private static List<ProbableKeySize> calculateProbableKeyLenght(List<NgramPositions> ngramPositions) {
        List<Integer> probableLenght = new ArrayList<>();
        ngramPositions.forEach(ngram -> ngram.getPositions().forEach(p -> {
            for (int i = 2; i < MAX_KEY_SIZE; i++) {
                if (p % i == 0){
                    probableLenght.add(i);
                }
            }
        }));

        //Map para agrupar possiveis tamanhos de chave, criando a contagem de divisores
        Map<Integer, Long> keySizePerDivisors = probableLenght.stream().sorted()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        //ProbableKeySize = objeto para guardar tamanho da chave e quantidade de divisores;
        //abaixo eh retornado uma lista de ProbableKeySize ordenada por ordenadade pela quantidade de divisores;
        return keySizePerDivisors.entrySet().stream()
                .map(t -> new ProbableKeySize(t.getKey(), t.getValue())).collect(Collectors.toList())
                .stream().sorted(
                        Comparator.comparing(ProbableKeySize::getCountPositionDivisors)
                                .thenComparing(ProbableKeySize::getKeySize).reversed()
                )
                .collect(Collectors.toList());
    }

    /* calculatePositions -> calcula distancia bruta entre posicoes encontradas no array. (posicao[i + 1] - posicao[1]) */
    private static List<Integer> calculatePositions(List<Integer> positions) {
        List<Integer> calcPositions = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            if (i < positions.size()-1){
                calcPositions.add(positions.get(i + 1) - positions.get(i));
            }
        }
        return calcPositions;
    }

    /* splitsEncryptedLetters -> separa mensagem cifrada em n-gramas */
    private static List<String> splitsEncryptedLetters(String encryptedMessage) {
        List<String> trigrams = new ArrayList<>();
        for (int i = 0; i < encryptedMessage.length(); i += SPLIT_LEN) { //separa os n-gramas
            trigrams.add(encryptedMessage.substring(i, Math.min(encryptedMessage.length(), i + SPLIT_LEN)));
        }
        return trigrams.stream().distinct().collect(Collectors.toList()); //filtra itens repetidos
    }

}