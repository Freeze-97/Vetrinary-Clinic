package veterinary.clinic;

public class Dog extends Pet {
	private double droolRate;

	Dog(String name, double health, int painLevel, double droolRate) {
		super(name, health, painLevel); // Pet class constructor
		if(droolRate <= 0.0)
			droolRate = 0.5;
		else
			this.droolRate = droolRate;
	}
	
	Dog(String name, double health, int painLevel) {
		this(name, health, painLevel, 5.0); // Other Dog constructor
	}
	
	public double getDroolRate() {
		return droolRate;
	}
	
	
	@Override
	public int treat() {
	    heal();

	    if (droolRate < 3.5)
	        return (int) Math.ceil((getPainLevel() * 2.0) / getHealth());

	    if (droolRate >= 3.5 && droolRate <= 7.5)
	        return (int) Math.ceil(getPainLevel() / getHealth());

	    return (int) Math.ceil(getPainLevel() / (getHealth() * 2.0)); // Over 7.5
	}
	
	public void speak() {
		super.speak();
		
		if(getPainLevel() > 5) {
			for(int i = 0; i < getPainLevel(); i++) {
				System.out.print("BARK ");
			}
		} else {
			for(int i = 0; i < getPainLevel(); i++) {
				System.out.print("bark ");
			}
		}
		System.out.println();
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Dog)) // Must compare Dog object only
			return false;
		
		if(!super.equals(o)) // Check name
			return false;
		
		Dog d = (Dog) o;
		return droolRate == d.getDroolRate();
	}
}
