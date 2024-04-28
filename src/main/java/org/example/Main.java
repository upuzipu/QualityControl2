package org.example;

import org.example.csv.CsvWriter;
import org.example.math.logarithm.Logarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.function.Function;
import org.example.math.trigonometry.*;

public class Main {
    public static void main(String[] args) {
        Sinus sinus = new Sinus();
        sinus.setN(50);

        Cosinus cosinus = new Cosinus(sinus);
        Cotangens cotangens = new Cotangens(sinus, cosinus);
        NaturalLogarithm naturalLogarithm = new NaturalLogarithm();
        naturalLogarithm.setN(100);

        Logarithm logarithm2 = new Logarithm(naturalLogarithm, 2);
        Logarithm logarithm3 = new Logarithm(naturalLogarithm, 3);
        Logarithm logarithm10 = new Logarithm(naturalLogarithm, 10);
        Function function = new Function(sinus, cosinus, cotangens, logarithm2, logarithm3, logarithm10);
        CsvWriter csvWriter = new CsvWriter(0.1, 5.1, 0.26);
        csvWriter.write("src/main/resources/csv/sinus.csv", sinus);
        csvWriter.write("src/main/resources/csv/cosinus.csv", cosinus);
        csvWriter.write("src/main/resources/csv/cotangens.csv", cotangens);
        csvWriter.write("src/main/resources/csv/function.csv", function);
        csvWriter.write("src/main/resources/csv/log2.csv", logarithm2);
        csvWriter.write("src/main/resources/csv/log3.csv", logarithm3);
        csvWriter.write("src/main/resources/csv/log10.csv", logarithm10);
    }
}