/**
 * The class represents a record of a pet adoption, including details
 * such as adopter name, pet type, pet name, age, and adoption cost.
 * @author Aman Farukbhai Vahora
 * StudentID 12254617
 */
public class PetRecord {
    private String adopterName;
    private String petType;  // Added pet type
    private String petName;
    private int age;
    private double adoptionCost;  // Added adoption cost

    /**
     * Adds the value of instances to the object
     * The parameters that are set are : 
     * @param adopterName   The name of the adopter.
     * @param petType       The type of the pet (A, B, or C).
     * @param petName       The name of the pet.
     * @param age           The age of the pet.
     * @param adoptionCost  The cost of adopting the pet.
     */
    public PetRecord(String adopterName, String petType, String petName, int age, double adoptionCost) {
        this.adopterName = adopterName;
        this.petType = petType;
        this.petName = petName;
        this.age = age;
        this.adoptionCost = adoptionCost;
    }

    /**
     * Returns the adopter's name.
     * @return The adopter's name.
     */
    public String getAdopterName() {
        return adopterName;
    }

    /**
     * Returns the type of the pet (A, B, or C).
     * @return The type of the pet.
     */
    public String getPetType() {
        return petType;
    }

    /**
     * Returns the name of the pet.
     * @return The name of the pet.
     */
    public String getPetName() {
        return petName;
    }

    /**
     * Returns the age of the pet.
     * @return The age of the pet.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the adoption cost of the pet.
     * @return The adoption cost of the pet.
     */
    public double getAdoptionCost() {
        return adoptionCost;
    }

    /**
     * Displays the details of the pet record, including adopter name, pet type, pet name,
     * age, and adoption cost.
     */
    public void display() {
        System.out.println("Adopter name    : " + adopterName);
        System.out.println("Pet Type        : " + petType);
        System.out.println("Pet Name        : " + petName);
        System.out.println("Pet Age         : " + age);
        System.out.println("Charged Amount  : " + adoptionCost);
        System.out.println("--------------------------------");
    }
}
