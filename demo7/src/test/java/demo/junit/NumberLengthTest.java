package demo.junit;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberLengthTest {

	public static class NumberLength {

		public static int calculate(int number) {
			return Integer.toString(number).length();
		}

	}

	@DisplayName("[[testNumberLength]]")
	@ParameterizedTest(name = "{index} => calculate({0})")
	@ValueSource(ints = { 1, 2, 4, 7 })
	public void testNumberLength(int number) {
		Assertions.assertEquals(1, NumberLength.calculate(number));
	}

	@ParameterizedTest(name = "{index} => calculate({0})")
	@MethodSource({ "sourceStreamInteger", "sourceArrayString" })
	public void testNumberLengthMethodeSource(int number) {
		Assertions.assertEquals(2, NumberLength.calculate(number));
	}

	@ParameterizedTest(name = "{index} => calculate({0}) should return {1}")
	@MethodSource("sourceStreamArguments")
	public void testNumberLengthMethodeSource2(int number, int expectedResult) {
		Assertions.assertEquals(expectedResult, NumberLength.calculate(number));
	}

	@ParameterizedTest(name = "{index} => calculate({0}) should return {1}")
	@CsvSource({ "1,1", "12,2" })
	public void testNumberLengthMethodeCSV(int number, int expectedResult) {
		Assertions.assertEquals(expectedResult, NumberLength.calculate(number));
	}

	@ParameterizedTest(name = "{index} => calculate({0}) should return {1}")
	@CsvSource(value = { "1;1", "12;2" }, delimiter = ';')
	public void testNumberLengthMethodeCSV2(int number, int expectedResult) {
		Assertions.assertEquals(expectedResult, NumberLength.calculate(number));
	}

	@ParameterizedTest(name = "{index} => calculate({0}) should return {1}")
	@CsvFileSource(resources = { "1.csv", "2.csv" }, delimiter = ';')
	public void testNumberLengthMethodeCSVFile(int number, int expectedResult) {
		Assertions.assertEquals(expectedResult, NumberLength.calculate(number));
	}

	@SuppressWarnings("boxing")
	public static Stream<Arguments> sourceStreamArguments() {
		return Stream.of(Arguments.of(1, 1), Arguments.of(2, 1), Arguments.of(3, 1), Arguments.of(4, 1),
				Arguments.of(13, 2), Arguments.of(14, 2), Arguments.of(15, 2));
	}

	public static IntStream sourceStreamInteger() {
		return IntStream.of(13, 16, 19, 21);
	}

	// The returned array will be converted to a Stream
	public static String[] sourceArrayString() {
		return new String[] { "78", "81" };
	}
}
