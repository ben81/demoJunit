package demo.junit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class SetTest {

	@DisplayName("Set Test V1")
	@Test
	public void setExample() {

		Set<String> expected = new HashSet<String>(Arrays.asList("a", "b", "c"));
		Set<String> actual = new HashSet<String>(Arrays.asList("b", "c", "d"));
		Assertions.assertEquals(expected, actual, () -> "test set");
	}

	@DisplayName("Set Test V2")
	@Test
	public void setExample2() {

		Set<String> expected = new HashSet<String>(Arrays.asList("a", "b", "c"));
		Set<String> actual = new HashSet<String>(Arrays.asList("b", "c", "d"));
		// @formatter:off
		// testInfo.
		Assertions.assertAll("check Set",
				() -> Assertions.assertAll("missing value expected ",
						expected.stream().map(str -> () -> Assertions.assertTrue(actual.contains(str), () -> str))),
				() -> Assertions.assertAll("unexpected value found",
						actual.stream().map(str -> () -> Assertions.assertTrue(expected.contains(str), () -> str)))

		);
		// @formatter:on
	}

	@DisplayName("Set Test V3")
	@Test
	public void setExample3() {

		final Set<String> expected = new HashSet<String>(Arrays.asList("a", "b", "c"));
		final Set<String> actual = new HashSet<String>(Arrays.asList("b", "c", "d"));
		// @formatter:off
		Assertions.assertAll("check Set",
				() -> Assertions.assertAll("missing value expected : ",
						expected.stream().filter(str -> !actual.contains(str)).map(SetTest::failMessage)),
				() -> Assertions.assertAll("unexpected value found :",
						actual.stream().filter(str -> !expected.contains(str)).map(SetTest::failMessage))

		);
		// @formatter:on
	}

	public static Executable failMessage(String message) {
		return () -> {
			Assertions.fail(message);
		};
	}
}
