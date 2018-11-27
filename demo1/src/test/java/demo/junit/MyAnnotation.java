package demo.junit;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apiguardian.api.API.Status.STABLE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apiguardian.api.API;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.*;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@Documented
@Inherited
@API(status = STABLE, since = "5.0")
@Tag( "tag1")
@Test()
public @interface MyAnnotation {

}
