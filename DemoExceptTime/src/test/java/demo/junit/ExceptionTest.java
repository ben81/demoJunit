package demo.junit;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

public class ExceptionTest {

	@Test
	@DisplayName("Test Exception")
	@Tag("v2")
	public void testException() {
		NullPointerException npe = Assertions.assertThrows(NullPointerException.class, () -> {
			throw new NullPointerException("toto");
		});
		Assertions.assertEquals("toto", npe.getMessage());

	}

	@Test
	@DisplayName("Test Exception ant TimeOut")
	@Tag("v2")
	public void testException2() {
		NullPointerException npe = Assertions.assertTimeout(Duration.ofMinutes(2),
				(ThrowingSupplier<NullPointerException>) () -> {
					return Assertions.assertThrows(NullPointerException.class, () -> {
						throw new NullPointerException("toto");
					});
				});
		Assertions.assertEquals("toto", npe.getMessage());

	}
}
