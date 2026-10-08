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


    public static int getSuma(NumberToken a, NumberToken b) {
        return a.number + b.number;
    }

    public static int getResta(NumberToken a, NumberToken b) {
        return a.number - b.number;
    }

    public static int getMultiplicacion(NumberToken a, NumberToken b) {
        return a.number * b.number;
    }

    public static int getDivision(NumberToken a, NumberToken b) {
        if (b.number == 0) {
            throw new ArithmeticException("Division por zero");
        }
        return (int)(a.number / b.number);
    }
    @Override
    public String toString() {
        return "NumberToken{" +
                "number=" + number +
                '}';
    }
}
