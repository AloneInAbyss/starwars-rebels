import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Criar Itens para um Rebelde
        HashMap<Inventory.Items, Integer> items = new HashMap<>();
        items.put(Inventory.Items.GUN, 2);
        items.put(Inventory.Items.AMMUNITION, 20);
        items.put(Inventory.Items.WATER, 5);

        // Criar Inventário para um Rebelde
        Inventory inventory = new Inventory(items);
        Rebel rebel1 = new Rebel("Thiago", "21", "Male", "Br", inventory);

        // Criar Itens para outro Rebelde
        HashMap<Inventory.Items, Integer> items2 = new HashMap<>();
        items2.put(Inventory.Items.FOOD, 6);
        items2.put(Inventory.Items.AMMUNITION, 10);
        items2.put(Inventory.Items.WATER, 10);

        // Criar Inventário para outro Rebelde
        Inventory inventory2 = new Inventory(items2);
        Rebel rebel2 = new Rebel("John", "22", "Male", "Usa", inventory2);

        // Criar Lista de Itens para receber
        HashMap<Inventory.Items, Integer> itemsToReceive = new HashMap<>();
        itemsToReceive.put(Inventory.Items.FOOD, 4);

        // Criar Lista de Itens para transferir
        HashMap<Inventory.Items, Integer> itemsToSend = new HashMap<>();
        itemsToSend.put(Inventory.Items.GUN, 1);

        // Antes de negociar
        System.out.println(rebel1);
        System.out.println(rebel2);

        // Negociar com outro Rebelde
        rebel1.negotiate(rebel2, itemsToReceive, itemsToSend);

        // Testes
//        rebel1.markAsTraitor();
//        rebel2.markAsTraitor();
//        rebel2.markAsTraitor();
//        rebel2.markAsTraitor();
//        rebel1.updateLocation("Russia");

        System.out.println();

        System.out.println(rebel1);
        System.out.println(rebel2);
    }
}
