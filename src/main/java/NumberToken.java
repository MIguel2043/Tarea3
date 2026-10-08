public class NumberToken {
    private int number;

    public NumberToken(int number) {
        this.number = number;
    }

    public NumberToken() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "NumberToken{" +
                "number=" + number +
                '}';
    }
}
