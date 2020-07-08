package co.tdude.COEN352Tutorial1;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter up to which factorial to calculate: ");
        int factorial = scanner.nextInt();
        double[] times = new double[factorial];
        double[] xData = new double[factorial];

        for (int i = 1; i <= factorial; i++) {
            xData[i - 1] = i;
        }

        for (int i = 1; i <= factorial; i++) {
            times[i - 1] = (double) timeFactorial(BigInteger.valueOf(i));
        }

        Chart chart = QuickChart.getChart("Factorial execution time (ns)", "Factorial", "Execution time (ns)", "Factorial execution time (ns)", xData, times);
        new SwingWrapper(chart).displayChart();
    }

    public static long timeFactorial(BigInteger n) {
        Instant start = Instant.now();
        System.out.println("Iteration " + n);
        factorial(n);
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    public static BigInteger factorial(BigInteger n){
        if (n.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        else
            return(n.multiply(factorial(n.subtract(BigInteger.ONE))));
    }
}
