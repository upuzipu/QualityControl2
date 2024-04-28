import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.math.function.Function;
import org.example.math.logarithm.Logarithm;
import org.example.math.logarithm.NaturalLogarithm;
import org.example.math.trigonometry.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FunctionTest {
    private static Sinus mockedSinus;
    private static Cosinus mockedCosinus;
    private static Cotangens mockedCotangens;

    private static Logarithm mockedLogarithm2;
    private static Logarithm mockedLogarithm3;
    private static Logarithm mockedLogarithm10;

    private static Sinus sinus;

    private static Cosinus cosinus;

    private static NaturalLogarithm naturalLogarithm;
    private static Logarithm logarithm2;
    private static Logarithm logarithm3;
    private static Logarithm logarithm10;

    private final double epsilon = 0.1;

    @BeforeAll
    static void init() {
        mockedSinus = Mockito.mock(Sinus.class);
        mockedCosinus = Mockito.mock(Cosinus.class);
        mockedCotangens = Mockito.mock(Cotangens.class);

        mockedLogarithm2 = Mockito.mock(Logarithm.class);
        mockedLogarithm3 = Mockito.mock(Logarithm.class);
        mockedLogarithm10 = Mockito.mock(Logarithm.class);

        sinus = new Sinus();
        sinus.setN(100);

        cosinus = new Cosinus(sinus);

        naturalLogarithm = new NaturalLogarithm();
        naturalLogarithm.setN(100);

        logarithm2 = new Logarithm(naturalLogarithm, 2);
        logarithm3 = new Logarithm(naturalLogarithm, 3);
        logarithm10 = new Logarithm(naturalLogarithm, 5);

        try {
            Reader cos = new FileReader("src/main/resources/csv/cosinus.csv");
            Reader sin = new FileReader("src/main/resources/csv/sinus.csv");
            Reader cotan = new FileReader("src/main/resources/csv/cotangens.csv");
            Reader log2 = new FileReader("src/main/resources/csv/log2.csv");
            Reader log3 = new FileReader("src/main/resources/csv/log3.csv");
            Reader log10 = new FileReader("src/main/resources/csv/log10.csv");

            for (CSVRecord record : CSVFormat.DEFAULT.parse(cos)) {
                Mockito.when(mockedCosinus.calculate(Double.parseDouble(record.get(0))))
                        .thenReturn(Double.parseDouble(record.get(1)));
            }
            for (CSVRecord record : CSVFormat.DEFAULT.parse(sin)) {
                Mockito.when(mockedSinus.calculate(Double.parseDouble(record.get(0))))
                        .thenReturn(Double.parseDouble(record.get(1)));
            }
            for (CSVRecord record : CSVFormat.DEFAULT.parse(cotan)) {
                Mockito.when(mockedCotangens.calculate(Double.parseDouble(record.get(0))))
                        .thenReturn(Double.parseDouble(record.get(1)));
            }
            for (CSVRecord record : CSVFormat.DEFAULT.parse(log2)) {
                Mockito.when(mockedLogarithm2.calculate(Double.parseDouble(record.get(0))))
                        .thenReturn(Double.parseDouble(record.get(1)));
            }
            for (CSVRecord record : CSVFormat.DEFAULT.parse(log3)) {
                Mockito.when(mockedLogarithm3.calculate(Double.parseDouble(record.get(0))))
                        .thenReturn(Double.parseDouble(record.get(1)));
            }
            for (CSVRecord record : CSVFormat.DEFAULT.parse(log10)) {
                Mockito.when(mockedLogarithm10.calculate(Double.parseDouble(record.get(0))))
                        .thenReturn(Double.parseDouble(record.get(1)));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testMock(double value, double expected) {
        Function function = new Function(mockedSinus, mockedCosinus, mockedCotangens, mockedLogarithm2, mockedLogarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testSin(double value, double expected) {
        Function function = new Function(sinus, mockedCosinus, mockedCotangens, mockedLogarithm2, mockedLogarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testCos(double value, double expected) {
        Function function = new Function(mockedSinus, cosinus, mockedCotangens, mockedLogarithm2, mockedLogarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testSinAndCos(double value, double expected) {
        Function function = new Function(sinus, cosinus, mockedCotangens, mockedLogarithm2, mockedLogarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testCot(double value, double expected) {
        Function function = new Function(mockedSinus, mockedCosinus, new Cotangens(sinus, cosinus), mockedLogarithm2, mockedLogarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testAllTrig(double value, double expected) {
        Function function = new Function(sinus, cosinus, new Cotangens(sinus, cosinus), mockedLogarithm2, mockedLogarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testLog2(double value, double expected) {
        Function function = new Function(mockedSinus, mockedCosinus, mockedCotangens, logarithm2, mockedLogarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testLog3(double value, double expected) {
        Function function = new Function(mockedSinus, mockedCosinus, mockedCotangens, mockedLogarithm2, logarithm3, mockedLogarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testLog10(double value, double expected) {
        Function function = new Function(mockedSinus, mockedCosinus, mockedCotangens, mockedLogarithm2, mockedLogarithm3, logarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testAllLog(double value, double expected) {
        Function function = new Function(mockedSinus, mockedCosinus, mockedCotangens, logarithm2, logarithm3, logarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/function.csv")
    void testAll(double value, double expected) {
        Function function = new Function(sinus, cosinus, new Cotangens(sinus, cosinus), logarithm2, logarithm3, logarithm10);
        Assertions.assertEquals(expected, function.calculate(value), epsilon);
    }
}
