public class Main {
    public static void main(String[] args) {
        int result1 = Try.of(() -> Integer.parseInt("500")) // Success[500]
                .map(value -> value * 10) // Success[5000]
                .getOrElse(0);

        int result2 = Try.of(() -> Integer.parseInt("500K")) // Failure[NumberFormatException]
                .map(value -> value * 10) // Failure[NumberFormatException]
                .getOrElse(0);

        System.out.println(result1);
        System.out.println(result2);

    }
}
