package VigenereCipher;

public class VigenereUtils {

    public static final int ROWS = 26;
    public static final int COLS = 26;

    public static char[][] vigenereTable(){
        char[][] alphabeticalTable = new char[ROWS][COLS];

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                int letter = i + j;
                if (letter >= COLS){
                    letter = letter - COLS;
                }
                letter = letter + COLS;
                System.out.print((char)letter);
                alphabeticalTable[i][j] = (char)letter;
            }
            System.out.println();
        }
        return alphabeticalTable;
    }

}
