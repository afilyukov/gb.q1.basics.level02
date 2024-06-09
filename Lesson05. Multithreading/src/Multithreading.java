public class Multithreading {
    public static void main(String[] args) {
        new ArrayThreading().doMathSync();
        new ArrayThreading().doMathAsync();
    }

}
