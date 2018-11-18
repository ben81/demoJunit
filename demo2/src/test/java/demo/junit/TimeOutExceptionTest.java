package demo.junit;

import  java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeOutExceptionTest {

	@Test
	public void timeoutNotExceeded() {
		// The following assertion succeeds.
		Assertions.assertTimeout(Duration.ofMinutes(2), () -> {
			// Perform task that takes less than 2 minutes.
		});
	}

	@Test
	public void timeoutNotExceededWithResult() {
		// The following assertion succeeds, and returns the supplied object.
		String actualResult = Assertions.assertTimeout(Duration.ofMinutes(2), () -> {
			return "a result";
		});
		Assertions.assertEquals("a result", actualResult);
	}

	@Test
	public void timeoutNotExceededWithMethod() {
		// The following assertion invokes a method reference and returns an object.
		String actualGreeting = Assertions.assertTimeout(Duration.ofMinutes(2), TimeOutExceptionTest::greeting);
		Assertions.assertEquals("Hello, World!", actualGreeting);
	}

	@Test
	public void timeoutExceeded() {
		// The following assertion fails with an error message similar to:
		// execution exceeded timeout of 10 ms by 991 ms
		Assertions.assertTimeout(Duration.ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(1000);
		});
	}

	@Test
	public void timeoutExceededWithPreemptiveTermination() {
		// The following assertion fails with an error message similar to:
		// execution timed out after 10 ms
		Assertions.assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(1000);
		});
	}

	private static String greeting() {
		return "Hello, World!";
	}
}
