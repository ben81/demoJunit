package demo.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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

}
