public class Item {
    private final String itemId;
    private final String itemName;
    private float itemPrice;
    private float quantity;

    public Item(String itemId, String itemName, float itemPrice, float quantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
