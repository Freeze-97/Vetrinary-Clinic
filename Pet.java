package veterinary.clinic;

/**
 * Any type of pet that would seek consultation from the clinic
 */
abstract public class Pet {
	private String name;
	private double health; // 0.0 to 1.0
	private int painLevel; // 1 to 10
	
	Pet(String name, double health, int painLevel) {
		this.name = name;
		
		if(health > 1.0)
			this.health = 1.0;
		if(health < 0.0)
			this.health = 0.0;
		
		if(painLevel > 10)
			this.painLevel = 10;
		if(painLevel < 1)
			this.painLevel = 1;
	}
	
	public String getName() {
		return name;
	}
	
	public double getHealth() {
		return health;
	}
	
	public int getPainLevel() {
		return painLevel;
	}
	
	// abstract method that returns the time taken (in minutes) to treat the pet
	public abstract int treat();
	
	public void speak() {
		if(painLevel > 5)
			System.out.println("HELLO! MY NAME IS " + name.toUpperCase());
		else
			System.out.println("Hello! My name is " + name);
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if (o == null || getClass() != o.getClass()) // Different class
			return false;
		
		Pet p = (Pet) o;
		return name.equals(p.name);
	}
	
	protected void heal() {
		health = 1.0;
		painLevel = 1;
	}
}
