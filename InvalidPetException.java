package veterinary.clinic;

public class InvalidPetException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	InvalidPetException() {
		super("Your pet is invalid!");
	}
	
	InvalidPetException(String s) {
		super(s);
	}
}
