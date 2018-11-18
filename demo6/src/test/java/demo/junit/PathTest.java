package demo.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestInstance(Lifecycle.PER_CLASS)

@DisplayName("Path Test (OS depends)")
public class PathTest {

	@Test
	@DisplayName("Test Linux")
	@EnabledOnOs({ OS.LINUX })
	public void justAnExample2() {
		Path p = Paths.get("");
		assertEquals("sun.nio.fs.UnixPath", p.getClass().getName());
		System.out.println(p.toAbsolutePath().toString());
		// assertEquals(sun.nio.fs.UnixPath.class,p.getClass());
	}

	@Test
	@DisplayName("Test Windows")
	@EnabledOnOs({ OS.WINDOWS })
	public void justAnExample3() {
		Path p = Paths.get("");
		assertEquals("sun.nio.fs.WindowsPath", p.getClass().getName());
		System.out.println(p.toAbsolutePath().toString());
		// assertEquals(sun.nio.fs.WindowsPath.class,p.getClass());
	}

}