package org.example.math.logarithm;

import lombok.Setter;
import org.example.math.interfaces.Calculable;

@Setter
public class NaturalLogarithm implements Calculable {

    private int N;
    private double epsilon = 0.000001;

    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x) || x <= 0) {
            throw new IllegalArgumentException("Argument must be a number > 0");
        }

        if (N <= 0) {
            throw new IllegalArgumentException("Number of iterations must be >= 0");
        }

        double prev;
        double current = 0.;
        int sign = 1;
        int i = 1;

        if (Math.abs(x - 1) <= 1) {
            do {
                prev = current;
                current += (sign * Math.pow(x - 1, i)) / i;
                sign *= -1;
                i++;
            } while (epsilon <= Math.abs(current - prev) && i < N);
        } else {
            do {
                prev = current;
                current += (sign * Math.pow(x - 1, -i)) / i;
                sign *= -1;
                i++;
            } while (epsilon <= Math.abs(current - prev) && i < N);
            current += calculate(x - 1);
        }

        return current;
    }
}
