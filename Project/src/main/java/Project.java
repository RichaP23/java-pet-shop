import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class for the program, serving as the driver.
 * Additionally, it invokes the menu function to display options to the customer.
 * @author Aman Farukbhai Vahora
 * StudentID 12254617
 */
public class Project {
    //stores the highet value in the student ID
    static final int N = 7;
    static ArrayList<PetRecord> petRecords = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("\n      WELCOME TO PET ADOPTION SYSTEM      \n ");
        
        // Calling the menu options
        PetAdoptionMenu petAdoptionMenu = new PetAdoptionMenu();
        petAdoptionMenu.displayMenu();
        
    }
}
