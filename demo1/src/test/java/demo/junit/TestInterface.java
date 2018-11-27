package demo.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface TestInterface {

	
	@Test
	@DisplayName("Test on interface")
	default public void testOninterface() {

	}
}
