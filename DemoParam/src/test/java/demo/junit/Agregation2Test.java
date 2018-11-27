package demo.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

import demo.junit.Person.Gender;

public class Agregation2Test {

	@ParameterizedTest
	@CsvSource({ "Jane, Doe, F, 1990-05-20", "John, Doe, M, 1990-10-22" })
	public void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
		Person person = new Person(arguments.getString(0), arguments.getString(1), arguments.get(2, Gender.class),
				arguments.get(3, LocalDate.class));

		if (person.getFirstName().equals("Jane")) {
			assertEquals(Gender.F, person.getGender());
		} else {
			assertEquals(Gender.M, person.getGender());
		}
		assertEquals("Doe", person.getLastName());
		assertEquals(1990, person.getDateOfBirth().getYear());
	}

	@ParameterizedTest
	@CsvSource({ "Jane, Doe, F, 1990-05-20", "John, Doe, M, 1990-10-22" })
	public void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
		if (person.getFirstName().equals("Jane")) {
			assertEquals(Gender.F, person.getGender());
		} else {
			assertEquals(Gender.M, person.getGender());
		}
		assertEquals("Doe", person.getLastName());
		assertEquals(1990, person.getDateOfBirth().getYear());
	}

	
	
	@ParameterizedTest
	@CsvSource({ "Jane, Doe, F, 1990-05-20", "John, Doe, M, 1990-10-22" })
	public void testWithArgumentsAggregator2(@CsvToPerson Person person) {
		if (person.getFirstName().equals("Jane")) {
			assertEquals(Gender.F, person.getGender());
		} else {
			assertEquals(Gender.M, person.getGender());
		}
		assertEquals("Doe", person.getLastName());
		assertEquals(1990, person.getDateOfBirth().getYear());
	}
	
	public static class PersonAggregator implements ArgumentsAggregator {
		@Override
		public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			return new Person(arguments.getString(0), arguments.getString(1), arguments.get(2, Gender.class),
					arguments.get(3, LocalDate.class));
		}
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.PARAMETER)
	@AggregateWith(PersonAggregator.class)
	public static @interface CsvToPerson {
	}

}
