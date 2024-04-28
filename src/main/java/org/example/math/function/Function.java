package org.example.math.function;

import lombok.RequiredArgsConstructor;
import org.example.math.interfaces.Calculable;
import org.example.math.logarithm.Logarithm;
import org.example.math.trigonometry.*;

import static java.lang.Math.pow;

@RequiredArgsConstructor
public class Function implements Calculable {
    private final Sinus sinus;
    private final Cosinus cosinus;
    private final Cotangens cotangens;

    private final Logarithm logarithm2;
    private final Logarithm logarithm3;
    private final Logarithm logarithm10;

    public double calculate(double x) {
        double result;

        if (x <= 0) {
            result = (pow(pow(cosinus.calculate(x), 2), 3)) / (sinus.calculate(x) / cosinus.calculate(x)) * pow(cotangens.calculate(x), 2);
            // (((((cos(x) ^ 2) ^ 3) / (sin(x) / sin(x))) * cot(x)) ^ 2)
        } else {
            result = (((logarithm2.calculate(x) - logarithm10.calculate(x)) + logarithm10.calculate(x)) + logarithm3.calculate(x)) * (logarithm3.calculate(x) + (logarithm2.calculate(x) * logarithm2.calculate(x))) + (logarithm2.calculate(x) * (logarithm3.calculate(x) * (logarithm3.calculate(x) - pow(logarithm2.calculate(x), 2))));
            // (((((log_2(x) - log_10(x)) + log_10(x)) + log_5(x)) * (log_3(x) + (log_2(x) * log_2(x)))) + (log_2(x) * (log_3(x) * (log_3(x) - (log_2(x) ^ 2)))))
        }
        return result;
    }
}
