package veterinary.clinic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is a class representing the vet clinic.
 */
public class Clinic {
	private File patientFile; // File with patient information
	private int day;
	
	Clinic(File file) {
		patientFile = file;
		this.day = 1;
	}
	
	Clinic(String fileName) {
		this(new File(fileName));
	}
	
	public String nextDay(File f) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(f);
		Scanner inputScanner = new Scanner(System.in);
		StringBuilder output = new StringBuilder();
		
		while(fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String parts[] = line.split(",");
			
			if(parts.length != 4)
				continue;
			
			String name = parts[0];
			String species = parts[1];
			double attribute;
			int entryTime;
			
			try {
                attribute = Double.parseDouble(parts[2]);
                entryTime = Integer.parseInt(parts[3]);
			}
			catch(NumberFormatException e) {
				fileScanner.close();
				continue;
			}
			
			// Validate pet type
			if (!species.equals("Dog") && !species.equals("Cat")) {
				fileScanner.close();
				throw new InvalidPetException();
	        }
			
			 // Ask for health input
			 System.out.println("Consultation for " + name + " the " + species + " at " + entryTime + ".");
	         System.out.println("What is the health of " + name + "?");
	         double health = getValidDoubleInput(inputScanner);
	         
	         // Ask for pain level input
	         System.out.println("On a scale of 1 to 10, how much pain is " + name + " in right now?");
	         int painLevel = getValidIntInput(inputScanner);
	         
	         // Create the pet object
	         Pet pet;
	         if (species.equals("Dog")) {
	        	 pet = new Dog(name, health, painLevel, attribute);
	         } 
	         else {
	        	 pet = new Cat(name, health, painLevel, (int) attribute);
	         }
	         
	         pet.speak();
	         pet.treat();
	         int exitTime = calculateExitTime(entryTime);
	         
	        // Format the output string
	        output.append(String.format("%s,%s,%.1f,Day %d,%d,%d,%.1f,%d%n",
	        		name, species, attribute, day, entryTime, exitTime, health, painLevel));
		}
		
		fileScanner.close();
		day++;
		return output.toString();
	}
	
	public String nextDay(String fileName) throws FileNotFoundException {
		return nextDay(new File(fileName));
	}
	
    private int calculateExitTime(int entryTime) {
        return entryTime + 20;
    }
	
    private double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
    }

    private int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer:");
            }
        }
    }
    
    public boolean addToFile(String patientInfo) {
    	 StringBuilder fileContents = new StringBuilder();
         boolean patientExists = false;
         String newPatientName = patientInfo.split(",")[0];
         
      // Read the file and store contents
      if (patientFile.exists()) {
		 try (Scanner scanner = new Scanner(patientFile)) {
		     while (scanner.hasNextLine()) {
		         String line = scanner.nextLine();
		         String existingPatientName = line.split(",")[0];
		
		         if (existingPatientName.equals(newPatientName)) {
		             patientExists = true;
		             line += "," + patientInfo.split(",", 4)[3]; // Append only appointment details
		         }
		         fileContents.append(line).append("\n");
		     }
		 } catch (FileNotFoundException e) {
		     System.out.println("File not found: " + patientFile.getName());
		         return false;
		     }
		 }
      
      // If it's a new patient, append the entire patientInfo line
      if (!patientExists) {
          fileContents.append(patientInfo).append("\n");
      }
      
      // Overwrite the file with updated data
      try (PrintWriter writer = new PrintWriter(patientFile)) {
          writer.print(fileContents.toString()); // Print all the stored lines at once
      } catch (IOException e) {
          System.out.println("Error writing to file: " + patientFile.getName());
          return false;
      }

      return true;
	}
    
    private String addTime(String timeIn, int treatmentTime) {
        // Extract hours and minutes from the military time string
        int hours = Integer.parseInt(timeIn.substring(0, 2));
        int minutes = Integer.parseInt(timeIn.substring(2, 4));

        // Add the treatment time to the total minutes
        minutes += treatmentTime;

        // if minutes exceed 60, adjust hours
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes %= 60;
        }

        // Ensure the output is in "HHMM" format
        return String.format("%02d%02d", hours, minutes);
    }
}
