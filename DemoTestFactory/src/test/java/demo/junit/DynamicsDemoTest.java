package demo.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

public class DynamicsDemoTest {

	// This will result in a JUnitException!
	/*
	 * @TestFactory public List<String> dynamicTestsWithInvalidReturnType() { return
	 * Arrays.asList("Hello"); }
	 */

	@TestFactory
	public Collection<DynamicTest> dynamicTestsFromCollection() {
		return Arrays.asList(DynamicTest.dynamicTest("1st dynamic test", () -> assertTrue(true)),
				DynamicTest.dynamicTest("2nd dynamic test", () -> assertEquals(4, 2 * 2)));
	}

	@TestFactory
	public Iterable<DynamicTest> dynamicTestsFromIterable() {
		return Arrays.asList(DynamicTest.dynamicTest("3rd dynamic test", () -> assertTrue(true)),
				DynamicTest.dynamicTest("4th dynamic test", () -> assertEquals(4, 2 * 2)));
	}

	@TestFactory
	public Iterator<DynamicTest> dynamicTestsFromIterator() {
		return Arrays.asList(DynamicTest.dynamicTest("5th dynamic test", () -> assertTrue(true)),
				DynamicTest.dynamicTest("6th dynamic test", () -> assertEquals(4, 2 * 2))).iterator();
	}

	@TestFactory
	public Stream<DynamicTest> dynamicTestsFromStream() {
		return Stream.of("A", "B", "C").map(str -> DynamicTest.dynamicTest("test" + str, () -> {
			/* ... */ }));
	}

	@TestFactory
	public Stream<DynamicTest> dynamicTestsFromIntStream() {
		// Generates tests for the first 10 even integers.
		return IntStream.iterate(0, n -> n + 2).limit(10)
				.mapToObj(n -> DynamicTest.dynamicTest("test" + n, () -> assertTrue(n % 2 == 0)));
	}

	@SuppressWarnings("boxing")
	@TestFactory
	public Stream<DynamicTest> generateRandomNumberOfTests() {

		// Generates random positive integers between 0 and 100 until
		// a number evenly divisible by 7 is encountered.
		Iterator<Integer> inputGenerator = new Iterator<Integer>() {

			Random random = new Random();
			int current;

			@Override
			public boolean hasNext() {
				this.current = this.random.nextInt(100);
				return this.current % 7 != 0;
			}

			@Override
			public Integer next() {
				return this.current;
			}
		};

		// Generates display names like: input:5, input:37, input:85, etc.
		Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

		// Executes tests based on the current input value.
		ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);

		// Returns a stream of dynamic tests.
		return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
	}

	@TestFactory
	public Stream<DynamicNode> dynamicTestsWithContainers() {
		return Stream
				.of("A", "B",
						"C")
				.map(input -> DynamicContainer.dynamicContainer("Container " + input,
						Stream.of(DynamicTest.dynamicTest("not null", () -> assertNotNull(input)),
								DynamicContainer.dynamicContainer("properties", Stream.of(
										DynamicTest.dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
										DynamicTest.dynamicTest("not empty", () -> assertFalse(input.isEmpty())))))));
	}

}