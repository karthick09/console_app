import java.util.InputMismatchException;
import java.util.Scanner;

public class Manger extends User implements InventoryControl {

    public Manger(String name, String userId, String email, String phone, Account account, UserType userType) {
        super(name, userId, email, phone , account, userType);
    }
    String validateItemId()
    {
        String string;
        boolean isItemId;
        do{
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            isItemId= DatabaseManager.checkItemId(string);
        }while (!isItemId);
        return string;
    }
    String validateItemName()
    {
        String string;
        boolean isItemName;
        do{
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            isItemName= DatabaseManager.checkItemName(string);
        }while (!isItemName);
        return string;
    }

    public void addItem(String id){
        String iName,itemId;
        float price,quantity;
        System.out.println("enter the item id");
        itemId = validateItemId();
        System.out.println("enter the item name");
        iName = validateItemName();
        System.out.println("enter the price");
        price = validateFloat();
        System.out.println("enter the quantity");
        quantity = validateFloat();
        Item item = new Item(itemId, iName, price, quantity);
        DatabaseManager.addItem(item,id);
    }

    public void deleteItem(String id){
        Scanner sc = new Scanner(System.in);
        String itemId;
        System.out.println("enter the item id");
        itemId = sc.next();
        DatabaseManager.deleteItem(itemId,id);
    }

    public void showItemList(){
        DatabaseManager.getItemList();
    }
    float validateFloat()
    {
        boolean flag;
        float float1 = 0;
        do {
            flag = true;
            try {
                Scanner scanner = new Scanner(System.in);
                float1 = scanner.nextFloat();

            } catch (InputMismatchException ignored) {
            }
            if(float1==0)
            {
                System.out.println("enter a valid input:");
                flag=false;
            }
        }while (!flag);
        return float1;
    }

    public void sales(){
        Scanner sc = new Scanner(System.in);
        String itemId;
        float quantity;
        System.out.println("enter the item no ");
        itemId = sc.next();
        System.out.println("enter the no of quantity");
        quantity = validateFloat();
            Item item;
            float updateQuantity;
            item= DatabaseManager.getItem(itemId);
            if(item!= null){
                if (quantity<=item.getQuantity()){
                    float price;
                    price=quantity* item.getItemPrice();
                    System.out.println("if any discount Y/N");
                    String choice=sc.next();
                    if(choice.equals("y")){
                        System.out.println("enter the discount percentage");
                        float discount=sc.nextFloat();
                        discount=discount/100;
                        price=price-(price*discount);
                    }
                    System.out.println("the total amount rs:"+price);
                    updateQuantity= item.getQuantity()-quantity;
                    DatabaseManager.updateItem(quantity,updateQuantity,item,this.getUserType(),"sales");
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

    public void purchase(){
        Scanner sc = new Scanner(System.in);
        String id;
        float quantity;
        System.out.println("enter the item id");
        id = sc.next();
        System.out.println("enter the count to add");
        quantity = validateFloat();
            Item item;
            float updateQuantity;
            item= DatabaseManager.getItem(id);
            if(item != null){
                updateQuantity= item.getQuantity()+quantity;
                DatabaseManager.updateItem(quantity,updateQuantity,item,this.getUserType(),"purchase");
            }
            else {
                System.out.println("item not found");
            }
    }
}
