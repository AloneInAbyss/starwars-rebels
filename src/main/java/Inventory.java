import java.util.HashMap;

public class Inventory {
    private HashMap<Items, Integer> items;

    public Inventory(HashMap<Items, Integer> items) {
        this.items = items;
    }

    public HashMap<Items, Integer> trade(HashMap<Items, Integer> itemsToRemove, HashMap<Items, Integer> itemsToAdd) {
        // Novo inventário
        HashMap<Items, Integer> newInventory = this.items;

        // Checa se houve falhas e a quantia de pontos
        boolean failure = false;
        int pointsToRemove = 0;
        int pointsToAdd = 0;

        // Verifica os itens para remover
        for (Items itemToRemove : itemsToRemove.keySet()) {
            // Verifica o valor em pontos a ser removido
            pointsToRemove = getPoints(itemsToRemove, pointsToRemove, itemToRemove);

            boolean found = false;
            for (Items inventoryItem : newInventory.keySet()) {
                // Se o item existe na lista de remoção e no inventário atual...
                if (itemToRemove == inventoryItem) {
                    // Verifica se as quantidades batem
                    int previousQuantity = newInventory.get(itemToRemove);
                    int newQuantity = previousQuantity - itemsToRemove.get(itemToRemove);

                    if (newQuantity < 0) {
                        failure = true;
                    }

                    newInventory.put(itemToRemove, newQuantity);
                    found = true;
                }
            }
            // Se o item não existe no inventário
            if (!found) {
                failure = true;
            }
        }

        // Verifica os itens para receber
        for (Items itemToAdd : itemsToAdd.keySet()) {
            // Verifica o valor em pontos a ser recebido
            pointsToAdd = getPoints(itemsToAdd, pointsToAdd, itemToAdd);

            boolean found = false;
            for (Items inventoryItem : newInventory.keySet()) {
                // Se o item existe na lista de recebimento e no inventário atual...
                if (itemToAdd == inventoryItem) {
                    int previousQuantity = newInventory.get(itemToAdd);
                    int newQuantity = previousQuantity + itemsToAdd.get(itemToAdd);
                    newInventory.put(itemToAdd, newQuantity);
                    found = true;
                }
            }
            // Se o item ainda não existe no inventário
            if (!found) {
                int newQuantity = itemsToAdd.get(itemToAdd);
                newInventory.put(itemToAdd, newQuantity);
            }
        }

        // Se não houve falhas faz a operação
        if (!failure && pointsToAdd == pointsToRemove) {
            return newInventory;
        }
        throw new Error("Falha na transação");
    }

    private int getPoints(HashMap<Items, Integer> items, int points, Items item) {
        switch (item) {
            case GUN:
                points += 4 * items.get(item);
                break;
            case AMMUNITION:
                points += 3 * items.get(item);
                break;
            case WATER:
                points += 2 * items.get(item);
                break;
            case FOOD:
                points += items.get(item);
                break;
        }
        return points;
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
        GUN, AMMUNITION, WATER, FOOD
    }
}
