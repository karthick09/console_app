import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Owner implements InventoryControl {
    private final String password;

    public Owner(String password) {
        this.password = password;
    }

    void addManager(Manger manger){
        Database.assignManger(manger,password);
    }

    public String getPassword() {
        return password;
    }

    void addSalesPerson(SalesMan salesMan){
        Database.assignSalesMan(salesMan,password);
    }

    float salary(String empId,int designation){
        float salary;
        if(designation==1){
            Manger manger=Database.getManger(empId);
            if (manger != null) {
                LocalDate doj=manger.getDateOfJoin();
                LocalDate presentDate=LocalDate.now();
                long noOfDays= ChronoUnit.DAYS.between(doj,presentDate);
                salary=(float)noOfDays/30;
                salary=Math.round(salary);
                salary=salary*20000;
                return salary;
            }
            else {
                System.out.println("manager not found");
                return -1;
            }
        }
        else if(designation==2){
            SalesMan salesMan=Database.getSalesMan(empId);
            if(salesMan != null){
                LocalDate doj=salesMan.getDateOfJoin();
                LocalDate presentDate=LocalDate.now();
                long noOfDays=ChronoUnit.DAYS.between(doj,presentDate);
                salary=(float)noOfDays/30;
                salary=salary*15000;
                salary=Math.round(salary);
                return salary;
            }
            else {
                System.out.println("salesman not found");
                return -1;
            }
        }
        else {
            System.out.println("designation not found ");
        }
        return -1;
    }
    void turnover(int choice){
        if(choice==1){
            HashMap<String, Float> salesList = Database.getSalesList();
            for (Map.Entry<String, Float> entry : salesList.entrySet()) {
                Item item=Database.getItem(entry.getKey());
                if (item != null) {
                    System.out.println("item id :"+entry.getKey()+" item name :"+item.getItemName()+" total sales count "+entry.getValue());
                }
                else{
                    System.out.println("item id :"+entry.getKey()+" total sales count "+entry.getValue());
                }
            }
        }
        else if (choice==2){
            HashMap<String, Float> purchaseList  = Database.getPurchaseList();
            for (Map.Entry<String, Float> entry : purchaseList.entrySet()) {
                Item item=Database.getItem(entry.getKey());
                if (item != null) {
                    System.out.println("item id :"+entry.getKey()+"item name:"+item.getItemName()+" total purchase count "+entry.getValue());
                }
                else {
                    System.out.println("item id :"+entry.getKey()+" total purchase count "+entry.getValue());

                }
            }
        }
        else {
            System.out.println("invalid choice");
        }
    }

    @Override
    public void sales(String itemId, float quantity) {
        Item item;
        float updateQuantity;
        item=Database.getItem(itemId);
        if(item!= null){
            if (quantity<=item.getQuantity()){
                float price;
                price=quantity* item.getItemPrice();
                System.out.println("the total amount rs:"+price);
                updateQuantity= item.getQuantity()-quantity;
                Database.updateItem(quantity,updateQuantity,item,UserType.OWNER,"sales");
            }
            else {
                System.out.println("insufficient stock");
                System.out.println("available stock is "+item.getQuantity());
            }
        }
        else {
            System.out.println("item not found");
        }
    }

    @Override
    public void addItem(Item item, String id) {
        Database.addItem(item,id);
    }

    @Override
    public void deleteItem(String itemId, String id) {
        Database.deleteItem(itemId,id);
    }

    @Override
    public void purchase(String id, float quantity) {
        Item item;
        float updateQuantity;
        item=Database.getItem(id);
        if(item != null){
            updateQuantity= item.getQuantity()+quantity;
            Database.updateItem(quantity,updateQuantity,item,UserType.OWNER,"purchase");
        }
        else {
            System.out.println("item not found");
        }
    }
}
