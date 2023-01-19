import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Owner extends User implements InventoryControl {

    private final String password;
    public Owner(String name, String userId, String email, String phone, Account account, UserType userType, String password) {
        super(name, userId, email, phone, account, userType);
        this.password = password;
    }
    String userId = "", pass = "", name = "", email = "", phone = "";

    public String getPassword() {
        return password;
    }
    static String validateName()
    {
        int stringLength;
        boolean value=true;
        String string;
        boolean isAlphabet=true;
        do {
            if(!value||!isAlphabet) {
                System.out.println("(Input size should be greater than 4 and should not contain any numbers or special characters)" +
                        "Enter again:");
            }
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            for (int i=0; i<string.length(); i++) {
                char c = string.charAt(i);
                isAlphabet=(c>=0x41&&c<=0x5a)||(c>=0x61&&c<=0x7a)||(c==0x20);
                if(!isAlphabet)
                    break;
            }
            stringLength=string.length();
            value=(stringLength>=5);
        }while (!value||!isAlphabet);
        return string;
    }

    static String validateEmail()
    {
        String string;
        boolean isEmail=true;
        do{
            if(!isEmail)
            {
                System.out.println("enter valid email");
            }
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            Pattern pat = Pattern.compile(emailRegex);
            isEmail=pat.matcher(string).matches();
        }while (!isEmail);
        return string;
    }

    static String validateUserName(int choice)
    {
        int stringLength;
        String string;
        boolean value=true;
        boolean isUserName;
        do{
            if(!value)
            {
                System.out.println("(Input size should be greater than 4)"+"Enter again:");
            }
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            stringLength=string.length();
            isUserName= DatabaseManager.checkUserName(string,choice);
            value=stringLength>=5;
        }while (!isUserName||!value);
        return string;
    }
    static String validatePhoneNumber()
    {
        int stringLength;
        boolean value=true;
        String string;
        boolean isNumber=true;
        do{
            if(!value||!isNumber)
            {
                System.out.println("(Input size should be equal to 10 and contain only numbers)"+
                        "Enter again:");
            }
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            for (int i=0; i<string.length(); i++) {
                char c = string.charAt(i);
                isNumber=(c>=0x30&&c<=0x39);
                if(!isNumber)
                    break;
            }

            stringLength=string.length();
            value=stringLength==10;
        }while (!value||!isNumber);
        return string;
    }
    static String validateMId()
    {
        String string;
        boolean isMID;
        do{
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            isMID= DatabaseManager.checkMId(string);
        }while (!isMID);
        return string;
    }
    static String validateSId()
    {
        String string;
        boolean isSID;
        do{
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            isSID= DatabaseManager.checkSId(string);
        }while (!isSID);
        return string;
    }
    static String validatePassword()
    {
        int stringLength;
        String string;
        boolean isPassword=true;
        do{
            if(!isPassword)
            {
                System.out.println("(Input size should be greater than 7)"+
                        "Enter again:");
            }
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            stringLength=string.length();
            isPassword=stringLength>=8;
        }while (!isPassword);
        return string;
    }
    public static int validateInteger()
    {
        boolean flag;
        int integer1 = 0;
        do {
            flag = true;
            try {
                Scanner scanner = new Scanner(System.in);
                integer1 = scanner.nextInt();

            } catch (InputMismatchException ignored) {
            }
            if(integer1==0)
            {
                System.out.println("enter integer only:");
                flag=false;
            }
        }while (!flag);
        return integer1;
    }

    void addManager() {
        System.out.println("enter the person details");
        System.out.println("enter the username");
        userId = validateUserName(1);
        System.out.println("enter the password");
        pass = validatePassword();
        System.out.println("enter the name ");
        name = validateName();
        System.out.println("enter the email");
        email = validateEmail();
        System.out.println("enter the phone");
        phone = validatePhoneNumber();
        Account account = new Account(userId, pass, AccountStatus.ACTIVE);
        System.out.println("enter the manager id");
        String mId = validateMId();
        Manger manager = new Manger(name,mId,email,phone, account,UserType.MANAGER);
        DatabaseManager.assignManger(manager, password);
    }

    void addSalesPerson() {
        System.out.println("enter the person details");
        System.out.println("enter the username");
        userId = validateUserName(2);
        System.out.println("enter the password");
        pass = validatePassword();
        System.out.println("enter the name ");
        name = validateName();
        System.out.println("enter the email");
        email = validateEmail();
        System.out.println("enter the phone");
        phone = validatePhoneNumber();
        Account account = new Account(userId, pass, AccountStatus.ACTIVE);
        System.out.println("enter the salesperson id ");
        String salePersonId = validateSId();
        SalesMan salesMan = new SalesMan(name,salePersonId,email,phone, account,UserType.SALESMAN);
        DatabaseManager.assignSalesMan(salesMan, password);
    }

    void salary() {
        String empId;
        int designation;
        Scanner sc = new Scanner(System.in);
        System.out.println("1.manager\n2.salesman");
        designation = validateInteger();
        System.out.println("enter the employee id");
        empId = sc.next();
        float salary;
        if (designation == 1) {
            Manger manger = DatabaseManager.getManger(empId);
            if (manger != null) {
                LocalDate doj = manger.getDateOfJoin();
                LocalDate presentDate = LocalDate.now();
                long noOfDays = ChronoUnit.DAYS.between(doj, presentDate);
                salary = (float) noOfDays / 30;
                salary = Math.round(salary);
                salary = salary * 20000;
                System.out.println("salary of the emp is :" + salary);
            } else {
                System.out.println("manager not found");
            }
        } else if (designation == 2) {
            SalesMan salesMan = DatabaseManager.getSalesMan(empId);
            if (salesMan != null) {
                LocalDate doj = salesMan.getDateOfJoin();
                LocalDate presentDate = LocalDate.now();
                long noOfDays = ChronoUnit.DAYS.between(doj, presentDate);
                salary = (float) noOfDays / 30;
                salary = salary * 15000;
                salary = Math.round(salary);
                System.out.println("salary of the emp is :" + salary);
            } else {
                System.out.println("salesman not found");
            }
        } else {
            System.out.println("designation not found ");
        }
    }

    void turnover(int choice) {
        if (choice == 1) {
            HashMap<Item, Float> salesList = DatabaseManager.getSalesList();
            for (Map.Entry<Item, Float> entry : salesList.entrySet()) {
                Item item = entry.getKey();
                System.out.println("item id :" + item.getItemId() + " item name :" + item.getItemName() + " total sales count " + entry.getValue());
            }
        } else if (choice == 2) {
            HashMap<Item, Float> purchaseList = DatabaseManager.getPurchaseList();
            for (Map.Entry<Item, Float> entry : purchaseList.entrySet()) {
                Item item = entry.getKey();
                System.out.println("item id :" + item.getItemId() + "item name:" + item.getItemName() + " total purchase count " + entry.getValue());
            }
        } else {
            System.out.println("invalid choice");
        }
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

    @Override
    public void sales() {
        Scanner sc = new Scanner(System.in);
        String itemId;
        float quantity;
        System.out.println("enter the item no ");
        itemId = sc.next();
        System.out.println("enter the no of quantity");
        quantity = validateFloat();
        Item item;
        float updateQuantity;
        item = DatabaseManager.getItem(itemId);
        if (item != null) {
            if (quantity <= item.getQuantity()) {
                float price;
                price = quantity * item.getItemPrice();
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
                DatabaseManager.updateItem(quantity, updateQuantity, item, UserType.OWNER, "sales");
            } else {
                System.out.println("insufficient stock");
                System.out.println("available stock is " + item.getQuantity());
            }
        } else {
            System.out.println("item not found");
        }
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

    @Override
    public void addItem(String id) {
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
        DatabaseManager.addItem(item, id);
    }

    @Override
    public void deleteItem( String id) {
        Scanner sc = new Scanner(System.in);
        String itemId;
        System.out.println("enter the item id");
        itemId = sc.next();
        DatabaseManager.deleteItem(itemId, id);
    }

    @Override
    public void purchase() {
        Scanner sc = new Scanner(System.in);
        String id;
        float quantity;
        System.out.println("enter the item id");
        id = sc.next();
        System.out.println("enter the count to add");
        quantity = validateFloat();
        Item item;
        float updateQuantity;
        item = DatabaseManager.getItem(id);
        if (item != null) {
            updateQuantity = item.getQuantity() + quantity;
            DatabaseManager.updateItem(quantity, updateQuantity, item, UserType.OWNER, "purchase");
        } else {
            System.out.println("item not found");
        }
    }
}
