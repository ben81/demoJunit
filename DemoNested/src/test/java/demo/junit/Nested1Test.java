package demo.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;

@DisplayName("1 Outter class PER_METHOD")
@TestInstance(Lifecycle.PER_METHOD)
public class Nested1Test {

	static int countOutter = 0;
	static int countInner = 0;

	public Nested1Test() {
		countOutter++;
	}

	@BeforeAll
	public static void beforeAll(TestReporter testReporter) {
		testReporter.publishEntry("all", "" + countOutter);
	}

	@BeforeEach
	public void beforeEach(TestReporter testReporter) {
		testReporter.publishEntry("countOutter", "" + countOutter);
	}

	@Test
	public void test1() {

	}

	@Test
	public void test2() {

	}

	@DisplayName("Inner class PER_METHOD")
	@Nested
	@TestInstance(Lifecycle.PER_METHOD)
	public class NestedInnerTest {

		public NestedInnerTest(TestReporter testReporter) {
			countInner++;
			testReporter.publishEntry("newInner", "" + countInner);
		}

		@BeforeEach
		public void beforeEach(TestReporter testReporter) {
			testReporter.publishEntry("countInner", "" + countInner);
		}

		@Test
		public void test1() {

		}

		@Test
		public void test2() {

		}

		@AfterEach
		public void afterEach() {

		}

	}

	@AfterEach
	public void afterEach() {

	}

}
