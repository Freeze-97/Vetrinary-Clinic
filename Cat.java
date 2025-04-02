package veterinary.clinic;

public class Cat extends Pet {
	private int miceCaught;

	Cat(String name, double health, int painLevel, int miceCaught) {
		super(name, health, painLevel);
		if(miceCaught < 0)
			miceCaught = 0;
		else
			this.miceCaught = miceCaught;
	}
	
	Cat(String name, double health, int painLevel) {
		this(name, health, painLevel, 0);
	}
	
	public int getMiceCaught() {
		return miceCaught;
	}

	@Override
	public int treat() {
		heal();
		
		if(miceCaught < 4)
			return (int) Math.ceil((getPainLevel() * 2.0) / getHealth());
		
		if(miceCaught >= 4 && miceCaught <= 7)
			return (int) Math.ceil(getPainLevel() / getHealth());
			
		return (int) Math.ceil(getPainLevel() / (getHealth() * 2));
	}
	
	public void speak() {
		super.speak();
		
		if(getPainLevel() > 5) {
			for(int i = 0; i < miceCaught; i++) {
				System.out.print("MEOW ");
			}
		} else {
			for(int i = 0; i < miceCaught; i++) {
				System.out.print("meow ");
			}
		}
		System.out.println();
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if(!(o instanceof Cat)) // Must only compare Cat class
			return false;
		
		if(!super.equals(o)) // Check name
			return false;
		
		Cat c = (Cat) o;
		return miceCaught == c.getMiceCaught();
	}
}
