import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public record Owner(String password) implements InventoryControl {

    void addManager(Manger manger) {
        Database.assignManger(manger, password);
    }

    void addSalesPerson(SalesMan salesMan) {
        Database.assignSalesMan(salesMan, password);
    }

    float salary(String empId, int designation) {
        float salary;
        if (designation == 1) {
            Manger manger = Database.getManger(empId);
            if (manger != null) {
                LocalDate doj = manger.getDateOfJoin();
                LocalDate presentDate = LocalDate.now();
                long noOfDays = ChronoUnit.DAYS.between(doj, presentDate);
                salary = (float) noOfDays / 30;
                salary = Math.round(salary);
                salary = salary * 20000;
                return salary;
            } else {
                System.out.println("manager not found");
                return -1;
            }
        } else if (designation == 2) {
            SalesMan salesMan = Database.getSalesMan(empId);
            if (salesMan != null) {
                LocalDate doj = salesMan.getDateOfJoin();
                LocalDate presentDate = LocalDate.now();
                long noOfDays = ChronoUnit.DAYS.between(doj, presentDate);
                salary = (float) noOfDays / 30;
                salary = salary * 15000;
                salary = Math.round(salary);
                return salary;
            } else {
                System.out.println("salesman not found");
                return -1;
            }
        } else {
            System.out.println("designation not found ");
        }
        return -1;
    }

    void turnover(int choice) {
        if (choice == 1) {
            HashMap<Item, Float> salesList = Database.getSalesList();
            for (Map.Entry<Item, Float> entry : salesList.entrySet()) {
                Item item = entry.getKey();
                System.out.println("item id :" + item.getItemId() + " item name :" + item.getItemName() + " total sales count " + entry.getValue());
            }
        } else if (choice == 2) {
            HashMap<Item, Float> purchaseList = Database.getPurchaseList();
            for (Map.Entry<Item, Float> entry : purchaseList.entrySet()) {
                Item item = entry.getKey();
                System.out.println("item id :" + item.getItemId() + "item name:" + item.getItemName() + " total purchase count " + entry.getValue());
            }
        } else {
            System.out.println("invalid choice");
        }
    }

    @Override
    public void sales(String itemId, float quantity) {
        Item item;
        float updateQuantity;
        item = Database.getItem(itemId);
        if (item != null) {
            if (quantity <= item.getQuantity()) {
                float price;
                price = quantity * item.getItemPrice();
                Scanner sc = new Scanner(System.in);
                System.out.println("if any discount Y/N");
                String choice=sc.next();
                if(choice.equals("y")){
                    System.out.println("enter the discount percentage");
                    float discount=sc.nextFloat();
                    discount=discount/100;
                    price=price-(price*discount);
                }
                System.out.println("the total amount rs:" + price);
                updateQuantity = item.getQuantity() - quantity;
                Database.updateItem(quantity, updateQuantity, item, UserType.OWNER, "sales");
            } else {
                System.out.println("insufficient stock");
                System.out.println("available stock is " + item.getQuantity());
            }
        } else {
            System.out.println("item not found");
        }
    }

    @Override
    public void addItem(Item item, String id) {
        Database.addItem(item, id);
    }

    @Override
    public void deleteItem(String itemId, String id) {
        Database.deleteItem(itemId, id);
    }

    @Override
    public void purchase(String id, float quantity) {
        Item item;
        float updateQuantity;
        item = Database.getItem(id);
        if (item != null) {
            updateQuantity = item.getQuantity() + quantity;
            Database.updateItem(quantity, updateQuantity, item, UserType.OWNER, "purchase");
        } else {
            System.out.println("item not found");
        }
    }
}
