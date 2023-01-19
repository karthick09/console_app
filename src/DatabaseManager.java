import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {

    static Database database =Database.getInstance();

    public static Manger getManger(String id){
        for (Manger m : database.getManagerList()) {
            Account account=m.getAccount();
            if (id.equals(account.getId()) || id.equals(m.getUserId())) {
                return m;
            }
        }
        return null;
    }
    public static SalesMan getSalesMan(String id){
        for (SalesMan SM : database.getSalesManList()) {
            Account account=SM.getAccount();
            if (id.equals(account.getId()) || id.equals(SM.getUserId())) {
                return SM;
            }
        }
        return null;
    }

    static HashMap<Item, Float> getSalesList(){
         return database.getSalesList();
     }

    static HashMap<Item, Float> getPurchaseList(){
        return database.getPurchaseList();
    }
    public static boolean checkUserName(String username,int choice){
        boolean flag=true;
        Account account;
        if (choice == 1) {
            for(Manger p : database.getManagerList()){
                account=p.getAccount();
                if(account.getId().equals(username)){
                    System.out.println("User name already exist!");
                    flag=false;
                    break;
                }
            }
        }
        if(choice==2){
            for(SalesMan p : database.getSalesManList()){
                account=p.getAccount();
                if(account.getId().equals(username)){
                    System.out.println("User name already exist!");
                    flag=false;
                    break;
                }
            }
        }
        return flag;
    }
    public static boolean checkItemName(String itemName){
        boolean flag=true;
        for(Item item : database.getItemList()){
            if(item.getItemName().equals(itemName)){
                System.out.println("item name already exits");
                flag=false;
                break;
            }
        }
        return flag;
    }
    public static boolean checkItemId(String itemId){
        boolean flag=true;
        for(Item item : database.getItemList()){
            if(item.getItemId().equals(itemId)){
                System.out.println("item id already exits");
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static boolean checkMId(String MID){
        boolean flag=true;
        for(Manger manger : database.getManagerList()){
            if(manger.getUserId().equals(MID)){
                System.out.println("manager id already exits");
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static boolean checkSId(String SID){
        boolean flag=true;
        for(SalesMan salesMan : database.getSalesManList()){
            if(salesMan.getUserId().equals(SID)){
                System.out.println("salesman id already exits");
                flag=false;
                break;
            }
        }
        return flag;
    }



    public static Item getItem(String id){
        for (Item I : database.getItemList()) {
            if (id.equals(I.getItemId())) {
                return I;
            }
        }
        return null;
    }

    static void getMangerList(){
        for(Manger m : database.getManagerList()){
            System.out.println("manger id :"+m.getUserId()+" --manger name :"+m.getName());
        }
    }

    static void getSaleManList(){
        for(SalesMan sm : database.getSalesManList()){
            System.out.println("salesMan id :"+sm.getUserId()+" --saleMan name :"+sm.getName());
        }
    }


    static void getItemList(){
        for(Item i : database.getItemList()){
            System.out.println("item id :"+i.getItemId()+" --item name :"+i.getItemName()+" --item price :"+i.getItemPrice()+" --item quantity :"+i.getQuantity());
        }
    }
    static void assignManger(Manger manger,String password){
        if(password.equals("owner")){
            database.addManager(manger);
        }
        else{
            System.out.println("Access denied");
        }
    }

    static void assignSalesMan(SalesMan salesMan,String password){
        if(password.equals("owner")){
            database.addSaleMan(salesMan);
        }
        else {
            System.out.println("Access denied");
        }

    }
    static void addOwner(Owner owner){
        database.setOwner(owner);
    }

    static Owner getOwner(){
        return database.getOwner();

    }
    public static boolean getLoginDetails(String id,String password,int choice){
        if(choice==2){
            for(Manger p : database.getManagerList()){
                Account account =p.getAccount();
                if(id.equals(account.getId()) && password.equals(account.getPassword())){
                    return true;
                }
            }
        }
        else if(choice==3){
            for(SalesMan p : database.getSalesManList()){
                Account account =p.getAccount();
                if(id.equals(account.getId()) && password.equals(account.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    public static void addItem(Item item,String idNo)
    {
        if((database.getManagerList().contains(getManger(idNo))) || idNo.equals("owner"))
        {
            database.addItem(item);
            database.addSales(item);
            database.addPurchase(item, item.getQuantity());
        }
        else{
            System.out.println("Access denied");
        }
    }

    public static void deleteItem(String id,String idNo)
    {
        if((database.getManagerList().contains(getManger(idNo))) || idNo.equals("owner")){

            Item item;
            item=getItem(id);
            if(item != null){
                database.removeItem(item);
            }
            else
            {
                System.out.println("item not found");
            }
        }
        else
        {
            System.out.println("access denied");
        }
    }
    public static void  updateItem(float updateQuantity,float quantity,Item item, UserType userType,String type){
        if(type.equals("sales")){
            if((userType.equals(UserType.MANAGER))  || userType.equals(UserType.SALESMAN) || userType.equals(UserType.OWNER))
            {
                Item item1;
                item1=item;
                item1.setQuantity(quantity);
                ArrayList<Item> itemList=database.getItemList();
                itemList.set(itemList.indexOf(item),item1);
                database.setItemList(itemList);
                HashMap<Item ,Float> salesList=database.getSalesList();
                float value=salesList.get(item);
                value=value+updateQuantity;
                salesList.put(item,value);
                database.setSalesList(salesList);
            }
            else {
                System.out.println("access denied");
            }
        }
        else if(type.equals("purchase")){
            if((userType.equals(UserType.MANAGER))||userType.equals(UserType.OWNER) )
            {
                Item item1;
                item1=item;
                item1.setQuantity(quantity);
                ArrayList<Item> itemList=database.getItemList();
                itemList.set(itemList.indexOf(item),item1);
                database.setItemList(itemList);
                HashMap<Item ,Float> purchaseList=database.getPurchaseList();
                float value = purchaseList.get(item);
                value=value+updateQuantity;
                purchaseList.put(item,value);
                database.setPurchaseList(purchaseList);
            }
            else {
                System.out.println("access denied");
            }
        }
    }
}
