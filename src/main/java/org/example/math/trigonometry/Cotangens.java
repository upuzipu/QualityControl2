package org.example.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.example.math.interfaces.Calculable;

@RequiredArgsConstructor
public class Cotangens implements Calculable {
    private final Sinus sinus;
    private final Cosinus cosinus;

    private final double epsilon = 0.001;
    public double calculate(double x) {
        double sinResult = sinus.calculate(x);
        if (Math.abs(sinResult) < epsilon) {
            throw new ArithmeticException("zero division");
        }
        return cosinus.calculate(x) / sinResult;
    }
}
