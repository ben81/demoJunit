package demo.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;
import static org.junit.jupiter.api.parallel.Resources.SYSTEM_PROPERTIES;

import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceLock;

//@Execution(ExecutionMode.SAME_THREAD)
@Execution(ExecutionMode.CONCURRENT)
public class SharedResourcesDemoTest {

	private Properties backup;

	@BeforeEach
	public void backup(TestInfo info, TestReporter reporter) {
		reporter.publishEntry("start", info.getDisplayName());
		this.backup = new Properties();
		this.backup.putAll(System.getProperties());
	}

	@AfterEach
	public void restore(TestInfo info, TestReporter reporter) {
		reporter.publishEntry("stop", info.getDisplayName());
		System.setProperties(this.backup);
	}

	@Test
	@ResourceLock(value = SYSTEM_PROPERTIES, mode = READ)
	public void customPropertyIsNotSetByDefault1(TestReporter testReporter) throws InterruptedException {
		testReporter.publishEntry("thread", "" + Thread.currentThread().getId());
		Thread.sleep(1000);
		assertNull(System.getProperty("my.prop"));
	}

	@Test
	@ResourceLock(value = SYSTEM_PROPERTIES, mode = READ)
	public void customPropertyIsNotSetByDefault2(TestReporter testReporter) throws InterruptedException {
		testReporter.publishEntry("thread", "" + Thread.currentThread().getId());
		Thread.sleep(1000);
		assertNull(System.getProperty("my.prop"));
	}

	@Test
	@ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
	public void canSetCustomPropertyToFoo(TestReporter testReporter) throws InterruptedException {
		testReporter.publishEntry("thread", "" + Thread.currentThread().getId());
		Thread.sleep(1000);
		System.setProperty("my.prop", "foo");
		assertEquals("foo", System.getProperty("my.prop"));
	}

	@Test
	@ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
	public void canSetCustomPropertyToBar(TestReporter testReporter) throws InterruptedException {
		testReporter.publishEntry("thread", "" + Thread.currentThread().getId());
		Thread.sleep(1000);
		System.setProperty("my.prop", "bar");
		assertEquals("bar", System.getProperty("my.prop"));
	}
}