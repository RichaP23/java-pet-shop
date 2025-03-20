import java.io.IOException;
import java.util.Scanner;
/**
 * Since the program is menu driven 
 * This class shows the menu option
 * Takes the menu input
 * Calls the functions as required
 * It also exits the program
* @author Aman Farukbhai Vahora
* StudentID 12254617
 */
public class PetAdoptionMenu {
    /**
     * Displays menu to user
     * Reads data form file using function of PetAdoptionCalculator class
     * and asks user input for various menu options.
     * @throws IOException when calls file reading function of PetAdoptionCalculator class
     */
    public void displayMenu() throws IOException {
        PetAdoptionCalculator calculator = new PetAdoptionCalculator();
        calculator.readFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            try {
                switch (choice) {
                    case 1:
                        calculator.adoptPet();
                        break;
                    case 2:
                        calculator.displayAdoptedPets();
                        break;
                    case 3:
                        calculator.sortPetsByAdoptionCost();
                        break;
                    case 4:
                        calculator.displayStatistics();
                        break;
                    case 5:
                        System.out.print("Enter pet name to search: ");
                        String petName=scanner.nextLine();
                        calculator.searchPet(petName);
                        break;
                    case 6:
                        System.out.println("Exiting the program");
                        System.out.println("Program written by : 12254617");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    /**
     * Helper function that displays the menu to the user.
     */
    public void printMenu() {
        System.out.println("\nPet Adoption Menu:");
        System.out.println("1. Adopt a Pet");
        System.out.println("2. Display Adopted Pets");
        System.out.println("3. Sort Pets by adoptoin cost");
        System.out.println("4. Display Statistics");
        System.out.println("5. Search for a Pet");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

}
