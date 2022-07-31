package VigenereCipher.Objects;

public class ProbableKeySize {
    private int keySize;
    private Long countPositionDivisors;

    public ProbableKeySize(int keySize, Long countPositionDivisors) {
        this.keySize = keySize;
        this.countPositionDivisors = countPositionDivisors;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public Long getCountPositionDivisors() {
        return countPositionDivisors;
    }

    public void setCountPositionDivisors(Long countPositionDivisors) {
        this.countPositionDivisors = countPositionDivisors;
    }
}
