import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static final ArrayList<Item> itemList = new ArrayList<>();
    private static final ArrayList<Manger> managerList = new ArrayList<>();
    private static final ArrayList<SalesMan> salesManList = new ArrayList<>();
    private static final HashMap<String, Float> salesList = new HashMap<>();
    private static final HashMap<String, Float> purchaseList = new HashMap<>();

    public static Manger getManger(String id){
        for (Manger m : managerList) {
            Account account=m.getAccount();
            if (id.equals(account.getId()) || id.equals(m.getUserId())) {
                return m;
            }
        }
        return null;
    }
    public static SalesMan getSalesMan(String id){
        for (SalesMan SM : salesManList) {
            Account account=SM.getAccount();
            if (id.equals(account.getId()) || id.equals(SM.getUserId())) {
                return SM;
            }
        }
        return null;
    }

    static HashMap<String, Float> getSalesList(){
         return salesList;
     }

    static HashMap<String, Float> getPurchaseList(){
        return purchaseList;
    }
    public static boolean checkUserName(String username,int choice){
        boolean flag=true;
        Account account;
        if (choice == 1) {
            for(Manger p :managerList){
                account=p.getAccount();
                if(account.getId().equals(username)){
                    System.out.println("User name already exist!");
                    flag=false;
                    break;
                }
            }
        }
        if(choice==2){
            for(SalesMan p :salesManList){
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
        for(Item item :itemList){
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
        for(Item item :itemList){
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
        for(Manger manger :managerList){
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
        for(SalesMan salesMan :salesManList){
            if(salesMan.getUserId().equals(SID)){
                System.out.println("salesman id already exits");
                flag=false;
                break;
            }
        }
        return flag;
    }



    public static Item getItem(String id){
        for (Item I : itemList) {
            if (id.equals(I.getItemId())) {
                return I;
            }
        }
        return null;
    }

    static void getMangerList(){
        for(Manger m :managerList){
            System.out.println("manger id :"+m.getUserId()+" --manger name :"+m.getName());
        }
    }

    static void getSaleManList(){
        for(SalesMan sm :salesManList){
            System.out.println("salesMan id :"+sm.getUserId()+" --saleMan name :"+sm.getName());
        }
    }


    static void getItemList(){
        for(Item i : itemList){
            System.out.println("item id :"+i.getItemId()+" --item name :"+i.getItemName()+" --item price :"+i.getItemPrice()+" --item quantity :"+i.getQuantity());
        }
    }
    static void assignManger(Manger manger,String password){
        if(password.equals("owner")){
            managerList.add(manger);
        }
        else{
            System.out.println("Access denied");
        }
    }

    static void assignSalesMan(SalesMan salesMan,String password){
        if(password.equals("owner")){
            salesManList.add(salesMan);
        }
        else {
            System.out.println("Access denied");
        }

    }

    public static boolean getLoginDetails(String id,String password,int choice){
        if(choice==2){
            for(Manger p :managerList){
                Account account =p.getAccount();
                if(id.equals(account.getId()) && password.equals(account.getPassword())){
                    return true;
                }
            }
        }
        else if(choice==3){
            for(SalesMan p :salesManList){
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
        if((managerList.contains(getManger(idNo))) || idNo.equals("owner"))
        {
            itemList.add(item);
            salesList.put(item.getItemId(), (float) 0);
            purchaseList.put(item.getItemId(),item.getQuantity());
        }
        else{
            System.out.println("Access denied");
        }
    }

    public static void deleteItem(String id,String idNo)
    {
        if((managerList.contains(getManger(idNo))) || idNo.equals("owner")){

            Item item;
            item=getItem(id);
            if(item != null){
                itemList.remove(item);
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
                itemList.set(itemList.indexOf(item),item1);
                float value=salesList.get(item.getItemId());
                value=value+updateQuantity;
                salesList.put(item.getItemId(),value);
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
                itemList.set(itemList.indexOf(item),item1);
                float value = purchaseList.get(item.getItemId());
                value=value+updateQuantity;
                purchaseList.put(item.getItemId(),value);
            }
            else {
                System.out.println("access denied");
            }
        }


    }
}
