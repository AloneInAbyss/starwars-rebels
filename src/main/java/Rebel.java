import java.util.HashMap;

public class Rebel {
    private final String name;
    private final String age;
    private final String gender;
    private String location;
    private final Inventory inventory;
    private Boolean isTraitor = false;
    private int traitorReports = 0;

    public Rebel(String name, String age, String gender, String location, Inventory inventory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.inventory = inventory;
    }

    public void markAsTraitor() {
        traitorReports++;
        if (traitorReports >= 3) {
            this.isTraitor = true;
        }
    }

    public void negotiate(Rebel target, HashMap<Inventory.Items, Integer> itemsToReceive, HashMap<Inventory.Items, Integer> itemsToSend) {
        if (isTraitor || target.isTraitor) {
            System.out.println("é um traidor");
            return;
        }

        HashMap<Inventory.Items, Integer> myNewInventory = new HashMap<>();
        HashMap<Inventory.Items, Integer> targetNewInventory = new HashMap<>();

        try {
            myNewInventory = inventory.trade(itemsToSend, itemsToReceive);
            targetNewInventory = target.inventory.trade(itemsToReceive, itemsToSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            inventory.setInventory(myNewInventory);
            target.inventory.setInventory(targetNewInventory);
        }
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
