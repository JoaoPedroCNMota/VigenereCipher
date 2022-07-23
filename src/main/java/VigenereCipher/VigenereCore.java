package VigenereCipher;

public class VigenereCore {

    private VigenereUtils utils = new VigenereUtils();

    public String encryt(String message, String key){

        char[] messageVarchar = message.replaceAll("[^a-zA-Z]+", "").toCharArray();
        char[] keyVarchar = message.replaceAll("[^a-zA-Z]+", "").toCharArray();

        for (int i = 0; i < messageVarchar.length; i++) {
            System.out.println(i%messageVarchar.length);
        }
        utils.vigenereTable();
        return null;
    }
}
