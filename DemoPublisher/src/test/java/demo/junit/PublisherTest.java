package demo.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PublisherTest {

	@BeforeEach
	public void beforeEach(TestReporter testReporter) {
		testReporter.publishEntry("start", "" + System.currentTimeMillis());
	}

	@ParameterizedTest
	@ValueSource(strings = { "foo", "bar" })
	public void testWithRegularParameterResolver(String argument, TestReporter testReporter) {
		testReporter.publishEntry("argument", argument);
		testReporter.publishEntry("argument2", "2");
	}

	@AfterEach
	public void afterEach(TestReporter testReporter) {
		testReporter.publishEntry("stop", "" + System.currentTimeMillis());
	}
}
