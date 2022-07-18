package VigenereCipher;

public class VigenereUtils {

    public static final int ROWS = 26;
    public static final int COLS = 26;

    private char[][] table;

    public void vigenereTable(){
        table = new char[ROWS][COLS];

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                int letter = i + j;
                if (letter >= COLS){
                    letter = letter - COLS;
                }
                letter = letter + COLS;
                table[i][j] = (char)letter;
            }
        }
    }

}
