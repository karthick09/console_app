public interface InventoryControl extends Sales{
    void addItem(Item item, String id);
    void deleteItem(String itemId,String id);
    void purchase(String id,float quantity);
}
