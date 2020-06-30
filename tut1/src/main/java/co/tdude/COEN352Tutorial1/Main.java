package co.tdude.COEN352Tutorial1;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the factorial to calculate: ");
        int factorial = scanner.nextInt();
        Instant start = Instant.now();
        System.out.println(factorial(BigInteger.valueOf(factorial)));
        Instant finish = Instant.now();
        System.out.println("It took " + Duration.between(start, finish).toMillis() + " ms!");
    }

    public static BigInteger factorial(BigInteger n){
        if (n.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        else
            return(n.multiply(factorial(n.subtract(BigInteger.ONE))));
    }
}
