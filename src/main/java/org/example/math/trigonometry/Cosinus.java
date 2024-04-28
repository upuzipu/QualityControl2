package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.example.math.interfaces.Calculable;

@RequiredArgsConstructor
public class Cosinus implements Calculable {
    private final Sinus sinus;
    public double calculate(double x) {
        if (Double.isInfinite(x) || Double.isNaN(x)) {
            throw new IllegalArgumentException("Argument must be a number");
        }

        x = sinus.getPeriod(x);

        double result = Math.sqrt(1 - sinus.calculate(x) * sinus.calculate(x));

        if (((x > Math.PI / 2) && (x < 3 * Math.PI / 2)) || ((x < -Math.PI / 2) && (x > -3 * Math.PI / 2))) {
            result *= -1;
        }
        return result;
    }

}