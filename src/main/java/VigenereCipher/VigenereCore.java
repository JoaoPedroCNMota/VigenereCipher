package VigenereCipher;

public class VigenereCore {

    public String encryt(String message, String key){
        key = getMessageKey(key, message.length());
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++){

            // A cifa de vigenere pode ser escrita como: CharCifrado = (Mensagem[i] + Chave[i]) mod 26
            // https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Algebraic.html
            int encryptedChar = (message.charAt(i) + key.charAt(i)) % 26;

            // Valor do caractere + 65 para conversao ASCII maiusculas
            // http://sticksandstones.kstrom.com/appen.html
            encryptedChar += 65;

            encryptedMessage.append((char) encryptedChar);
        }
        return encryptedMessage.toString();
    }

    public String decrypt(String encryptedMessage, String key){
        key = getMessageKey(key, encryptedMessage.length());
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < encryptedMessage.length(); i++) {

            // A decifracao de vigenere pode ser escrita como: CharDecifrado = (Mensagem[i] - Chave[i]) mod 26
            // https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Algebraic.html
            int decryptedChar = (encryptedMessage.charAt(i) - key.charAt(i)) % 26;

            //Faz um shift no array para buscar caracteres nao negativos;
            if (decryptedChar < 0){
                decryptedChar += 26;
            }

            decryptedChar += 65;

            decryptedMessage.append((char) decryptedChar);
        }
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
