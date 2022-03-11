import java.util.HashMap;

public class Rebel {
    private String name;
    private String age;
    private String gender;
    private String location;
    private final Inventory inventory;
    private Boolean isTraitor = false;
    private int traitorReports = 0;
    private static int traitorsCounter = 0;

    public Rebel(String name, String age, String gender, String location, Inventory inventory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.inventory = inventory;
    }

    public void updateLocation(String location) {
        this.location = location;
    }

    public void markAsTraitor() {
        traitorReports++;
        if (traitorReports >= 3) {
            this.isTraitor = true;
            traitorsCounter++;
        }
    }

    public void negotiate(Rebel target, HashMap<Inventory.Items, Integer> itemsToReceive, HashMap<Inventory.Items, Integer> itemsToSend) {
        if (isTraitor) {
            System.out.println("é um traidor");
            return;
        }
        inventory.trade(itemsToSend, itemsToReceive);
        target.inventory.trade(itemsToReceive, itemsToSend);
    }

    @Override
    public String toString() {
        return "Rebel{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", location='" + location + '\'' +
                ", inventory=" + inventory.toString() +
                ", isTraitor=" + isTraitor +
                ", traitorReports=" + traitorReports +
                '}';
    }
}