

public class SalesMan extends User implements Sales{
    public SalesMan(String name, String userId, String email, String phone, Account account, UserType userType) {
        super(name, userId, email, phone, account, userType);
    }


    public void showItemList(){
        Database.getItemList();
    }

    public void sales(String itemId,float quantity){
        Item item;
        float updateQuantity;
        item=Database.getItem(itemId);
        if(item!= null){
            if (quantity< item.getQuantity()){
                float price;
                price=quantity* item.getItemPrice();
                System.out.println("the total amount rs:"+price);
                updateQuantity= item.getQuantity()-quantity;
                Database.updateItem(quantity,updateQuantity,item,this.getUserType(),"sales");
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
