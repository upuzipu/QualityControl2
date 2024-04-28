package org.example.csv;

import lombok.RequiredArgsConstructor;
import org.example.math.interfaces.Calculable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

@RequiredArgsConstructor
public class CsvWriter {
    private final double lowerBound;
    private final double upperBound;
    private final double step;

    public void write(String filename, Calculable calculable) {
        try {
            var path = Paths.get(filename);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (double i = lowerBound; i < upperBound; i += step) {
                try {
                    stringBuilder.append(String.format(Locale.US,"%.4f,%.4f\n", i, calculable.calculate(i)));
                } catch (Exception e) {
                    stringBuilder.append(String.format(Locale.US,"%.4f,%s\n", i, e.getMessage()));
                }
            }

            Files.writeString(path, stringBuilder.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}