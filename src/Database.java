import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static Database database = null;
    private Database(){

    }

    static Database getInstance(){
        if(database==null){
            database = new Database();
        }
        return database;
    }
    private  Owner owner;
    private static ArrayList<Item> itemList = new ArrayList<>();
    private static final ArrayList<Manger> managerList = new ArrayList<>();
    private static final ArrayList<SalesMan> salesManList = new ArrayList<>();
    private static HashMap<Item, Float> salesList = new HashMap<>();
    private static HashMap<Item, Float> purchaseList = new HashMap<>();

    public Owner getOwner() {
        return owner;
    }

    public  void setOwner(Owner owner) {
        this.owner = owner;
    }

    ArrayList<Manger> getManagerList(){
        return managerList;
    }
    ArrayList<SalesMan> getSalesManList(){
        return salesManList;
    }
    ArrayList<Item> getItemList(){
        return itemList;
    }
    HashMap<Item, Float> getSalesList(){
        return salesList;
    }

    HashMap<Item, Float> getPurchaseList(){
        return purchaseList;
    }
    void addManager(Manger manager){
        managerList.add(manager);
    }
    void addSaleMan(SalesMan salesman){
        salesManList.add(salesman);
    }
    void addItem(Item item){
        itemList.add(item);
    }
    void removeItem(Item item){
        itemList.remove(item);
    }
    void addSales(Item id){
        salesList.put(id, (float) 0.0);
    }
    void addPurchase(Item id,float quantity){
        purchaseList.put(id,quantity);
    }
    void setSalesList(HashMap<Item ,Float> newSalesList){
        salesList=newSalesList;
    }
    void setPurchaseList(HashMap<Item,Float> newPurchaseList ){
        purchaseList=newPurchaseList;
    }
    void setItemList(ArrayList<Item> newItemList){
        itemList=newItemList;
    }

}
