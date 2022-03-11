import java.util.HashMap;

public class Inventory {
    private HashMap<Items, Integer> items;

    public Inventory(HashMap<Items, Integer> items) {
        this.items = items;
    }

    public HashMap<Items, Integer> trade(HashMap<Items, Integer> itemsToRemove, HashMap<Items, Integer> itemsToAdd) {
        // Novo inventário
        HashMap<Items, Integer> newInventory = new HashMap<>();

        // Checa se houve falhas
        boolean failure = false;

        // Verifica os itens para remover
        for (Items itemToRemove : itemsToRemove.keySet()) {
            for (Items inventoryItem : this.items.keySet()) {
                // Se o item existe na lista de remoção e no inventório atual...
                if (itemToRemove == inventoryItem) {
                    // Verifica se as quantidades batem
                    int oldQuantity = this.items.get(itemToRemove);
                    int newValue = oldQuantity - itemsToRemove.get(itemToRemove);
                    if (newValue < 0) {
                        failure = true;
                    }
                    newInventory.put(itemToRemove, newValue);
                }
            }
        }

        // Verifica os itens para receber
        for (Items itemToAdd : itemsToAdd.keySet()) {
            boolean found = false;
            for (Items inventoryItem : this.items.keySet()) {
                // Se o item existe na lista de recebimento e no inventório atual...
                if (itemToAdd == inventoryItem) {
                    int oldQuantity = this.items.get(itemToAdd);
                    int newValue = oldQuantity + itemsToAdd.get(itemToAdd);
                    newInventory.put(itemToAdd, newValue);
                    found = true;
                }
            }
            // Se o item ainda não existe no inventório
            if (!found) {
                int newValue = itemsToAdd.get(itemToAdd);
                newInventory.put(itemToAdd, newValue);
            }
        }

        // Se não houve falhas faz a operação
        if (!failure) {
            return newInventory;
        }
        throw new Error("Falha na transação");
    }

    public void setInventory(HashMap<Items, Integer> newInventory) {
        this.items = newInventory;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "items=" + items +
                '}';
    }

    public enum Items {
        GUN, AMMUNITION, WATER, FOOD;
    }
}
