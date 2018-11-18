package demo.junit;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runners.MethodSorters;

/**
 * 
 * @author Utilisateur
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("TestInfo Demo")
public class Junit5Test {

	public Junit5Test(TestInfo testInfo) {
		System.out.println(testInfo.getDisplayName());
		System.out.println("TestJunit5.TestJunit5()");
	}

	@BeforeAll
	public static void beforeAll() {
		System.out.println("TestJunit5.beforeAll()");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("TestJunit5.beforeEach()");
	}

	@BeforeEach
	public void beforeEachTest(TestInfo testInfo) {
		System.out.println(String.format("About to execute [%s]", testInfo.getDisplayName()));
	}

	@AfterEach
	public void afterEach() {
		System.out.println("TestJunit5.afterEach()");
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("TestJunit5.afterAll()");
	}

	/**
	 * 
	 */
	@Test
	@DisplayName("Test Fail X1")
	@Tag("v1")
	public void testX1() {
		Assertions.assertAll("Test", /*  */
				() -> Assertions.fail("f1"), /*  */
				() -> Assertions.fail("f2"), /*  */
				() -> Assertions.assertAll("f3", /*  */
						() -> Assertions.fail("a"), /*  */
						() -> Assertions.fail("b")/*  */
				));
	}

	@Test
	@DisplayName("TEST 1")
	@Tag("my-tag")
	void test1(TestInfo testInfo) {
		Assertions.assertEquals("TEST 1", testInfo.getDisplayName());
		Assertions.assertTrue(testInfo.getTags().contains("my-tag"));
	}

	@DisplayName("Display name of container")
	@ParameterizedTest(name = "{index} ==> first=''{0}'', second={1}")
	@CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
	public void testWithCustomDisplayNames(String first, int second) {
		System.out.println("" + first + "," + second);
	}

}
