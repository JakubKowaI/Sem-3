package lib.test;

import org.apache.commons.math3.distribution.BinomialDistribution;

public class Main {
    public static void main(String[] args) {
        int n = 100;  // liczba prób
        double p = 0.5;  // prawdopodobieństwo sukcesu

        // Wartość oczekiwana
        double expectedValue = n * p;

        System.out.println("n: "+n);

        // 1. P(X ≥ 5/6 * E(X))
        int k1 = (int) Math.ceil(5.0 / 6.0 * expectedValue);
        BinomialDistribution binom = new BinomialDistribution(n, p);
        double probability1 = 1 - binom.cumulativeProbability(k1 );
        System.out.println("P(X ≥ 5/6 E(X)) = " + probability1);

        // 2. P(|X - E(X)| ≥ 1/10 E(X))
        double d = expectedValue / 10.0;
        int lowerBound = (int) Math.floor(expectedValue - d);
        int upperBound = (int) Math.ceil(expectedValue + d);
        double probability2 = 1 - (binom.cumulativeProbability(upperBound) - binom.cumulativeProbability(lowerBound - 1));
        System.out.println("P(|X - E(X)| ≥ 1/10 E(X)) = " + probability2);

        int E = n/2;

        for(int i = 0; i <= n; i++) {
            System.out.println("P(X = " + i + ") = " + binom.probability(i));
        }
    }
}
