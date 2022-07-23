package VigenereCipher;

public class VigenereCore {

    private char[][] charsTable = VigenereUtils.vigenereTable();

    public String encryt(String message, String key){

        char[] messageVarchar = message.replaceAll("[^a-zA-Z]+", "").toCharArray();
        char[] keyVarchar = key.replaceAll("[^a-zA-Z]+", "").toCharArray();

        String encryptedMessage = "";

        for (int i = 0; i < messageVarchar.length; i++) {
            int messageCharNum = messageVarchar[i % messageVarchar.length];
            int keyCharNum = keyVarchar[i % keyVarchar.length];

            System.out.println(messageCharNum);
            System.out.println(keyCharNum);

            System.out.println(charsTable[keyCharNum-65][messageCharNum-65]);
        }
        return encryptedMessage;
    }
}
