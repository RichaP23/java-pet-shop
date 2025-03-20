import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class is the main class that performs sorting, searching, displaying 
 * insertion operation on the data.
 * It is called by the menu option to perform its actions.
  * @author Aman Farukbhai Vahora
 * StudentID 12254617
 */
public class PetAdoptionCalculator {
    final static int N = Project.N;
    static String RECORD_FILE_NAME = "pet_records.txt";
    static ArrayList<PetRecord> petRecords = Project.petRecords;
    Scanner scanner = new Scanner(System.in);
    /**
     * This function takes entry of the object values
     * It also validates them and asks for re entry if the entry is invalid
     * It saves the data in the file and in the arraylist perRecords
     * @throws IOException while saving in the file
     */
    public void adoptPet() throws IOException {
       
        final int INDEX_ZERO = 0;
        int index = INDEX_ZERO;

        // Taking entries for N number of pet adoptions (N is the highest digit in the student ID)
        do {
            

            // Validating adopter name
            String adopterName = validateAdopterName();

            // Validating pet type
            String petType = validatePetType();

            // Validating pet name
            String petName = validatePetName();

            // Validating pet age
            int age = validatePetAge();

            // Calculate adoption cost based on pet type
            double adoptionCost = calculateAdoptionCost(petType, age);

            // Creating a new PetRecord with the calculated adoption cost
            PetRecord newRecord = new PetRecord(adopterName, petType, petName, age, adoptionCost);
            petRecords.add(newRecord);

            // Save the new record to the file
            saveToFile(newRecord);

            System.out.println("Pet adopted successfully!");
            index++;
        } while (index < N);
    }
    /**
     * a helper function it validates the name of the adopter.
     * valid name contains letters only
     * @return the valid input adopter name
     */
    private String validateAdopterName() {
        String adopterName;
        boolean flag;
        do {
            flag=true;
            System.out.print("\nEnter adopter name: ");
            adopterName = scanner.nextLine();
            String[] nameParts=adopterName.split(" ");
            for(String parts : nameParts){
                //if name does not contain letters
                if (!parts.matches("^[a-zA-z]+$")) {
                    System.out.println("Please enter a valid adopter name.");
                    flag=false;
                }
            }
        } while (flag!=true);
        return adopterName;
    }
    /**
     * It validates the input pet type
     * It checks if type is something else rather than A B or C
     * @return the valid input type
     */
    private String validatePetType() {
        String petType;
        do {
            System.out.print("Enter pet type (A, B, or C): ");
            petType = scanner.nextLine().trim().toUpperCase();

            if (!petType.matches("[ABC]")) {
                System.out.println("Please enter a valid pet type (A, B, or C).");
            }
        } while (!petType.matches("[ABC]"));
        return petType;
    }
    /**
     * Validates the pet name
     * @return the valid pet name
     */
    private String validatePetName() {
        String petName;
        do {
            System.out.print("Enter pet name: ");
            petName = scanner.nextLine().trim();

            if (petName.isEmpty()) {
                System.out.println("Please enter a valid pet name.");
            }
        } while (petName.isEmpty());
        return petName;
    }
    /**
     * Validates the age of the pet 
     * Tells the user to enter only positive value.
     * @return the valid age
     */
    private int validatePetAge() {
        int age;
        do {
            System.out.print("Enter pet age: ");
            
                age = Integer.parseInt(scanner.nextLine());

                if (age <= 0) {
                    System.out.println("Please enter a valid positive integer for pet age.");
                }
            
        } while (age <= 0);
        return age;
    }
    /**
     * Calculates the adoption cost based on the age and the pet type
     * @param petType of which the cost will be calculated
     * @param age of which the cost will be calculated
     * @return the calculated rate
     */
    private double calculateAdoptionCost(String petType, int age) {
       
        final double BASE_COST_A = 10.0;
        final double BASE_COST_B = 15.0;
        final double BASE_COST_C = 20.0;

        final double AGE_MULTIPLIER = 2.0;
        final double DISCOUNT = 5.0;
        final double SURCHARGE = 7.0;

        double baseCost;
        if ("A".equalsIgnoreCase(petType)) {
            baseCost = BASE_COST_A;
        } else if ("B".equalsIgnoreCase(petType)) {
            baseCost = BASE_COST_B;
        } else {
            baseCost = BASE_COST_C;
        } 

        double cost = baseCost + (age * AGE_MULTIPLIER);

        if ("B".equalsIgnoreCase(petType)) {
            cost -= DISCOUNT;
        } else if ("C".equalsIgnoreCase(petType)) {
            cost += SURCHARGE;
        }

        return cost;
    }
    /**
     * It displays the details of the adoption in a tabular format
     */
    public void displayAdoptedPets() {
        //if no data is present
        if (petRecords.isEmpty()) {
            System.out.println("\nNo adopted pets available. Please adopt a pet first.");
            return;
        }
        double totalCost=0;
        final String PET_TYPE_HEADER = "Pet Type";
        final String PET_NAME_HEADER = "Pet Name";
        final String ADOPTER_NAME_HEADER = "Adopter Name";
        final String PET_AGE_HEADER = "Pet Age";
        final String COST_HEADER = "Pet Age";
        System.out.println("\nAdopted Pets:");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s%-10s%10s\n",
                PET_TYPE_HEADER, PET_NAME_HEADER, ADOPTER_NAME_HEADER, PET_AGE_HEADER,COST_HEADER);
        System.out.println("------------------------------------------------------------------------------------------");

        
        for (PetRecord record : petRecords) {
            System.out.printf("%-20s%-20s%-20s%-10d%10s%.2f\n",
                    record.getPetType(), record.getPetName(), record.getAdopterName(), record.getAge(),"$",record.getAdoptionCost());
            totalCost+=record.getAdoptionCost();
        }   

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("                 TOTAL AMOUNT COLLECTED : $%.2f\n",totalCost);
        System.out.println("------------------------------------------------------------------------------------------");
    }
  
    /**
     * Saves the data of any record to a file
     * @param record
     * @throws IOException 
     */
    public void saveToFile(PetRecord record) throws IOException {
        File file = new File(RECORD_FILE_NAME);
        FileWriter fw;

        // Initializing the file writer
        if (file.exists()) {
            fw = new FileWriter(file, true);
        } else {
            fw = new FileWriter(file);
        }

        // Writing to the file
        fw.write(record.getAdopterName() + ";" + record.getPetType() + ";" + record.getPetName()
                + ";" + record.getAge() + ";" + record.getAdoptionCost() + "\n");
        fw.close();
    }
    /**
     * Reads the saved data from file into the array list.
     * @throws IOException 
     */
    public void readFromFile() throws IOException {
        File file = new File(RECORD_FILE_NAME);
        if (!file.exists()) {
            System.out.println("No data saved in the file.");
            return;
        }
        final int NAME_INDEX=0;
        final int PET_INDEX=1;
        final int PET_TYPE_INDEX=2;
        final int AGE_INDEX=3;
        final int COST_INDEX=4;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            String adopterName = parts[NAME_INDEX];
            String petType = parts[PET_INDEX];
            String petName = parts[PET_TYPE_INDEX];
            int age = Integer.parseInt(parts[AGE_INDEX]);
            double adoptionCost = Double.parseDouble(parts[COST_INDEX]);

            PetRecord readRecord = new PetRecord(adopterName, petType, petName, age, adoptionCost);

            petRecords.add(readRecord);
        }

        br.close();
    }
    /**
     * Displays the statistics to the user
     * Displays the total entries 
     * The record with the minimum cost
     * The record with the maximum cost
     * Average cost of the data
     */
    public void displayStatistics() {
        //if no data is present
        if (petRecords.isEmpty()) {
            System.out.println("\nNo adopted pets available. Please adopt a pet first.");
            return;
        }
        System.out.println("\nStatistics:");
        System.out.println("-------------------------------------------------------");

        System.out.println("Total Entries: " + petRecords.size());

        double totalCost = 0;
        double minCost = Double.MAX_VALUE;
        double maxCost = Double.MIN_VALUE;

        PetRecord minRecord = null;
        PetRecord maxRecord = null;

        for (PetRecord record : petRecords) {
            double cost = record.getAdoptionCost();
            totalCost += cost;

            if (cost < minCost) {
                minCost = cost;
                minRecord = record;
            }

            if (cost > maxCost) {
                maxCost = cost;
                maxRecord = record;
            }
        }

        System.out.println("THE RECORD WITH MINIMUM COST");
        if (minRecord != null) {
            minRecord.display();
        }

        System.out.println("THE RECORD WITH MAXIMUM COST");
        if (maxRecord != null) {
            maxRecord.display();

            System.out.printf("Average Cost: $%.2f\n" ,(totalCost / petRecords.size()));
            System.out.println("-------------------------------------------------------");
        }
    }
    /**
     * A function to search a pet by their name
     * If the pet does not exist it notifies the user
     * @param petName The name that is searched 
     */
    public void searchPet(String petName) {
        //if no data is present
        if (petRecords.isEmpty()) {
            System.out.println("\nNo adopted pets available. Please adopt a pet first.");
            return;
        }
        boolean found = false;
        for (PetRecord record : petRecords) {
            if (record.getPetName().equalsIgnoreCase(petName)) {
                found = true;
                record.display();
            }
        }
        if (!found) {
            System.out.println("\nPet with the name '" + petName + "' not found.");
        }
    }
    /**
    * Sorts the adopted pets by adoption cost using the insertion sort algorithm.
    */
    public void sortPetsByAdoptionCost() {
        //if no data is present
        if (petRecords.isEmpty()) {
            System.out.println("\nNo adopted pets available. Please adopt a pet first.");
            return;
        }
       int n = petRecords.size();
       final int SECOND_INDEX=1;
       final int FIRST_INDEX=0;
       final int ONE=1;
       for (int i = SECOND_INDEX; i < n; i++) {
           PetRecord keyRecord = petRecords.get(i);
           int j = i - ONE;

           // Move elements of petRecords[0..i-1] that are greater than keyRecord's cost to one position ahead of their current position
           while (j >= FIRST_INDEX && petRecords.get(j).getAdoptionCost() > keyRecord.getAdoptionCost()) {
               petRecords.set(j + ONE, petRecords.get(j));
               j = j - ONE;
           }
           petRecords.set(j + ONE, keyRecord);
       }

       System.out.println("Pets sorted by adoption cost (using insertion sort):");
       displayAdoptedPets();
    }

}
