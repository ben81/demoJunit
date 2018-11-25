package demo.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.condition.JRE.JAVA_10;
import static org.junit.jupiter.api.condition.JRE.JAVA_8;
import static org.junit.jupiter.api.condition.JRE.JAVA_9;

import java.time.LocalDate;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;



public class EnableGeneralTest {
	
	@Test // Static JavaScript expression.
	@EnabledIf("2 * 3 == 6")
	public void willBeExecuted() {
	    // ...
	}

	@RepeatedTest(10) // Dynamic JavaScript expression.
	@DisabledIf("Math.random() < 0.314159")
	public void mightNotBeExecuted (RepetitionInfo info, TestReporter report) {
	    report.publishEntry("current", ""+info.getCurrentRepetition());
	    report.publishEntry("total", ""+info.getTotalRepetitions());
	}

	@Test // Regular expression testing bound system property.
	@DisabledIf("/32/.test(systemProperty.get('os.arch'))")
	public void disabledOn32BitArchitectures() {
	    assertFalse(System.getProperty("os.arch").contains("32"));
	}

	@Test
	@EnabledIf("'CI' == systemEnvironment.get('ENV')")
	public void onlyOnCiServer() {
	    assertTrue("CI".equals(System.getenv("ENV")));
	}

	@Test // Multi-line script, custom engine name and custom reason.
	@EnabledIf(value = {
	                "load('nashorn:mozilla_compat.js')",
	                "importPackage(java.time)",
	                "",
	                "var today = LocalDate.now()",
	                "var tomorrow = today.plusDays(1)",
	                "tomorrow.isAfter(today)"
	            },
	            engine = "nashorn",
	            reason = "Self-fulfilling: {result}")
	public void theDayAfterTomorrow() {
	    LocalDate today = LocalDate.now();
	    LocalDate tomorrow = today.plusDays(1);
	    assertTrue(tomorrow.isAfter(today));
	}
	
	@Test
	@EnabledOnJre(JAVA_8)
	void onlyOnJava8() {
	    // ...
	}

	@Test
	@EnabledOnJre({ JAVA_9, JAVA_10 })
	void onJava9Or10() {
	    // ...
	}

	@Test
	@DisabledOnJre(JAVA_9)
	void notOnJava9() {
	    // ...
	}
	
}
