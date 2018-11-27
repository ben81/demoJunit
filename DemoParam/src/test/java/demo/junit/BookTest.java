package demo.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BookTest {

	@ParameterizedTest
	@ValueSource(strings = "42 Cats")
	public void testWithImplicitFallbackArgumentConversion(Book book) {
		Assertions.assertEquals("42 Cats", book.getTitle());
	}

	public static class Book {

		private final String title;

		private Book(String title) {
			this.title = title;
		}

		public static Book fromTitle(String title) {
			return new Book(title);
		}

		public String getTitle() {
			return this.title;
		}
	}

}
