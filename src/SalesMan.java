import java.util.InputMismatchException;
import java.util.Scanner;

public class SalesMan extends User{
    public SalesMan(String name, String userId, String email, String phone, Account account, UserType userType) {
        super(name, userId, email, phone, account, userType);
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
            if (quantity< item.getQuantity()){
                float price;
                price=quantity* item.getItemPrice();
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
}
