package demo.junit;
import java.time.LocalDate;

public class Person {

	public enum Gender {
		F, M
	}

	private String firstName;
	private String lastName;
	private Gender gender;
	private LocalDate dateOfBirth;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
		this(firstName, lastName);
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
		result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (this.firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		}
		else if (!this.firstName.equals(other.firstName)) {
			return false;
		}
		if (this.lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		}
		else if (!this.lastName.equals(other.lastName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName + "]";
	}

}