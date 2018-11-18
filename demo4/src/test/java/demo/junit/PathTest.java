package demo.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(Lifecycle.PER_CLASS)

@DisplayName("Path Test (OS depends)")
public class PathTest {

	String osName = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH);

	@BeforeEach
	public void beforeEach() {
		//System.out.println("PathTest.beforeEach()");
	}
	
	@AfterEach
	public void afterEach() {
		//System.out.println("PathTest.afterEach()");
	}
	
	@Test
	@DisplayName("Test Linux")
	@EnabledOnOs({ OS.LINUX })
	public void justAnExample1() {
		Path p = Paths.get("");
		assertEquals("sun.nio.fs.UnixPath", p.getClass().getName());
		System.out.println(p.toAbsolutePath().toString());
		// assertEquals(sun.nio.fs.UnixPath.class,p.getClass());
	}

	@Test
	@DisplayName("Test Linux 2")
	public void justAnExample1b() {
		assumeTrue(this.osName.contains("linux"));
		Path p = Paths.get("");
		assertEquals("sun.nio.fs.UnixPath", p.getClass().getName());
		System.out.println(p.toAbsolutePath().toString());
		// assertEquals(sun.nio.fs.UnixPath.class,p.getClass());
	}

	@Test
	@DisplayName("Test Windows")
	@EnabledOnOs({ OS.WINDOWS })
	public void justAnExample2() {
		Path p = Paths.get("");
		assertEquals("sun.nio.fs.WindowsPath", p.getClass().getName());
		System.out.println(p.toAbsolutePath().toString());
		// assertEquals(sun.nio.fs.WindowsPath.class,p.getClass());
	}

	@Test
	@DisplayName("Test Windows 2")
	public void justAnExample2b() {
		assumeTrue(this.osName.contains("windows"));
		Path p = Paths.get("");
		assertEquals("sun.nio.fs.WindowsPath", p.getClass().getName());
		System.out.println(p.toAbsolutePath().toString());
		// assertEquals(sun.nio.fs.WindowsPath.class,p.getClass());
	}
	
	
}