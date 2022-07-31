package VigenereCipher.Objects;

import java.util.List;

public class NgramPositions {
    private String ngram;
    private List<Integer> positions;

    public NgramPositions(String ngram, List<Integer> positions) {
        this.ngram = ngram;
        this.positions = positions;
    }

    public String getNgram() {
        return ngram;
    }

    public void setNgram(String ngram) {
        this.ngram = ngram;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

}

