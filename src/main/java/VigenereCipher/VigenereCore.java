package VigenereCipher;

import java.util.List;

public class VigenereCore {

    public String encryt(String message, String key){
        key = getMessageKey(key.toUpperCase(), message.length());   //prolonga chave ate o tamanho da mensagem;
        String formatedMessage = VigenereUtils.formatMessage(message);  //retira pontuacao e espaco da mensagem;
        List<Integer> spacesIndex = VigenereUtils.getSpacesIndex(message); //guarda posicao dos espacos para isnerir posteriormente;

        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < formatedMessage.length(); i++){

            // A cifa de vigenere pode ser escrita como: CharCifrado = (Mensagem[i] + Chave[i]) mod 26
            // https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Algebraic.html
            int encryptedChar = (formatedMessage.charAt(i) + key.charAt(i)) % 26;

            // Valor do caractere + 65 para conversao ASCII maiusculas
            // http://sticksandstones.kstrom.com/appen.html
            encryptedChar += 65;

            encryptedMessage.append((char) encryptedChar);
        }

        //Insere espacos novamente;
        spacesIndex.forEach(space -> encryptedMessage.insert(space, " "));
        return encryptedMessage.toString();
    }

    public String decrypt(String encryptedMessage, String key){
        key = getMessageKey(key.toUpperCase(), encryptedMessage.length());  //prolonga chave ate o tamanho da mensagem;
        String encryptedFormatedMessage = VigenereUtils.formatMessage(encryptedMessage);    //retira pontuacao e espaco da mensagem;
        List<Integer> spacesIndex = VigenereUtils.getSpacesIndex(encryptedMessage); //guarda posicao dos espacos para isnerir posteriormente;

        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < encryptedFormatedMessage.length(); i++) {

            // A decifracao de vigenere pode ser escrita como: CharDecifrado = (Mensagem[i] - Chave[i]) mod 26
            // https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Algebraic.html
            int decryptedChar = (encryptedFormatedMessage.charAt(i) - key.charAt(i)) % 26;

            //Faz um shift no array para buscar caracteres nao negativos;
            if (decryptedChar < 0){
                decryptedChar += 26;
            }

            // Valor do caractere + 65 para conversao ASCII maiusculas
            // http://sticksandstones.kstrom.com/appen.html
            decryptedChar += 65;

            decryptedMessage.append((char) decryptedChar);
        }

        spacesIndex.forEach(space -> decryptedMessage.insert(space, " "));
        return decryptedMessage.toString();
    }

    private String getMessageKey(String key, int messageLength){
        StringBuilder messageKey = new StringBuilder(key);
        for (int i = 0; i < messageLength && messageKey.length() < messageLength; i++) {
            messageKey.append(messageKey.charAt(i));
        }
        return messageKey.toString();
    }
}
