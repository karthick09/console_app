import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
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
            isUserName=Database.checkUserName(string,choice);
            value=stringLength>=5;
        }while (!isUserName||!value);
        return string;
    }

    static String validateItemName()
    {
        String string;
        boolean isItemName;
        do{
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            isItemName=Database.checkItemName(string);
        }while (!isItemName);
        return string;
    }
    static String validateItemId()
    {
        String string;
        boolean isItemId;
        do{
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            isItemId=Database.checkItemId(string);
        }while (!isItemId);
        return string;
    }
    static String validateMId()
    {
        String string;
        boolean isMID;
        do{
            Scanner scanner=new Scanner(System.in);
            string=scanner.nextLine();
            isMID=Database.checkMId(string);
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
            isSID=Database.checkSId(string);
        }while (!isSID);
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
    public static float validateFloat()
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account ac1=new Account("karthick","muthu1309",AccountStatus.ACTIVE);
        Account ac2=new Account("raghul","muthu1234",AccountStatus.ACTIVE);
        Manger ps1= new Manger("karthick","m101","karthickm@gmnvia.fv","1234567890",ac1,UserType.MANAGER);
        SalesMan ps2= new SalesMan("karthick","s101","karthickm@gmnvia.fv","1234567890",ac2,UserType.SALESMAN);
        Owner o =new Owner("owner");
        o.addManager(ps1);
        o.addSalesPerson(ps2);
        do{
            String username,password;
            int choice1,choice2;
            boolean flag=true;
            System.out.println("1.owner login \n2.manger login \n3.salesman \n4.exit");
            choice1= validateInteger();
            if(choice1==4){
                System.exit(0);
            }
            Login login =new Login();
            switch (choice1){
                case 1 -> {
                    System.out.println("enter the username");
                    username = sc.next();
                    System.out.println("enter the password");
                    password = sc.next();
                    if (username.equals("abc") && password.equals("123")) {
                        do {
                            Owner owner = new Owner("owner");
                            System.out.println("""
                                    1.Add manager\s
                                    2.Add salesperson\s
                                    3.showList\s
                                    4.salary for employee
                                    5.turnover
                                    6.Add item\s
                                    7.Delete item\s
                                    8.sales\s
                                    9.purchase
                                    10.exit""");
                            choice2 = validateInteger();
                            String userId = "", pass = "", name = "", email = "", phone = "",itemId;
                            float quantity;
                            if (choice2 < 3) {
                                System.out.println("enter the person details");
                                System.out.println("enter the username");
                                userId = validateUserName(choice2);
                                System.out.println("enter the password");
                                pass = validatePassword();
                                System.out.println("enter the name ");
                                name = validateName();
                                System.out.println("enter the email");
                                email = validateEmail();
                                System.out.println("enter the phone");
                                phone = validatePhoneNumber();
                            }
                            Account account = new Account(userId, pass, AccountStatus.ACTIVE);
                            switch (choice2) {
                                case 1 -> {
                                    System.out.println("enter the manager id");
                                    String mId = validateMId();
                                    Manger person = new Manger(name,mId,email,phone, account,UserType.MANAGER);
                                    owner.addManager(person);
                                }
                                case 2 -> {
                                    System.out.println("enter the salesperson id ");
                                    String salePersonId = validateSId();
                                    SalesMan person = new SalesMan(name,salePersonId,email,phone, account,UserType.SALESMAN);
                                    owner.addSalesPerson(person);
                                }
                                case 3 -> {
                                    System.out.println("1.manger list \n2.salesman list \n3.item list");
                                    int ch = validateInteger();
                                    switch (ch) {
                                        case 1 -> Database.getMangerList();
                                        case 2 -> Database.getSaleManList();
                                        case 3 -> Database.getItemList();
                                        default -> System.out.println("invalid choice");
                                    }
                                }
                                case 4 -> {
                                    String eId;
                                    int designation;
                                    System.out.println("1.manager\n2.salesman");
                                    designation = validateInteger();
                                    System.out.println("enter the employee id");
                                    eId = sc.next();
                                    float salary = owner.salary(eId, designation);
                                    if (salary != -1) {
                                        System.out.println("salary of the emp is :" + salary);
                                    }
                                }
                                case 5 -> {
                                    System.out.println("1.total sales\n2.total purchase");
                                    int choice = validateInteger();
                                    owner.turnover(choice);
                                }
                                case 6 -> {
                                    String iName;
                                    float price;
                                    System.out.println("enter the item id");
                                    itemId = validateItemId();
                                    System.out.println("enter the item name");
                                    iName = validateItemName();
                                    System.out.println("enter the price");
                                    price = validateFloat();
                                    System.out.println("enter the quantity");
                                    quantity = validateFloat();
                                    Item item = new Item(itemId, iName, price, quantity);
                                    owner.addItem(item,owner.getPassword());
                                }
                                case 7 -> {
                                    System.out.println("enter the item id");
                                    itemId = sc.next();
                                    owner.deleteItem(itemId, owner.getPassword());
                                }
                                case 8 -> {
                                    System.out.println("enter the item no ");
                                    itemId = sc.next();
                                    System.out.println("enter the no of quantity");
                                    quantity = validateFloat();
                                    owner.sales(itemId, quantity);
                                }
                                case 9 -> {
                                    System.out.println("1.To update item \n2.new item");
                                    int ch = validateInteger();
                                    if (ch == 1) {
                                        System.out.println("enter the item id");
                                        itemId = sc.next();
                                        System.out.println("enter the count to add");
                                        quantity = validateFloat();
                                        owner.purchase(itemId, quantity);
                                    } else if (ch == 2) {
                                        String iName;
                                        float price;
                                        System.out.println("enter the item id");
                                        itemId = validateItemId();
                                        System.out.println("enter the item name");
                                        iName = validateItemName();
                                        System.out.println("enter the price");
                                        price = validateFloat();
                                        System.out.println("enter the quantity");
                                        quantity = validateFloat();
                                        Item item = new Item(itemId, iName, price, quantity);
                                        owner.addItem(item, owner.getPassword());
                                    } else {
                                        System.out.println("invalid choice");
                                    }
                                }
                                case 10 -> {
                                    flag=false;
                                    System.out.println("Exited successfully");
                                }
                                default -> System.out.println("invalid choice");
                            }
                        } while (flag);
                    } else {
                        System.out.println("invalid username and password");
                    }
                }
                case 2 -> {
                    System.out.println("enter the username");
                    username = sc.next();
                    System.out.println("enter the password");
                    password = sc.next();
                    if (login.login(username, password,choice1)) {
                        Manger manger;
                        manger = Database.getManger(username);
                        if (manger == null) {
                            System.out.println("manager not found");
                            break;
                        }
                        String itemId;
                        float quantity;
                        do {
                            System.out.println("1.showItemList \n2.Add item \n3.Delete item \n4.sales \n5.purchase\n6.exit");
                            choice2 = validateInteger();
                            switch (choice2) {
                                case 1 -> manger.showItemList();
                                case 2 -> {
                                    String iName;
                                    float price;
                                    System.out.println("enter the item id");
                                    itemId = validateItemId();
                                    System.out.println("enter the item name");
                                    iName = validateItemName();
                                    System.out.println("enter the price");
                                    price = validateFloat();
                                    System.out.println("enter the quantity");
                                    quantity = validateFloat();
                                    Item item = new Item(itemId, iName, price, quantity);
                                    manger.addItem(item,username);
                                }
                                case 3 -> {
                                    System.out.println("enter the item id");
                                    itemId = sc.next();
                                    manger.deleteItem(itemId,username);
                                }
                                case 4 -> {
                                    System.out.println("enter the item no ");
                                    itemId = sc.next();
                                    System.out.println("enter the no of quantity");
                                    quantity = validateFloat();
                                    manger.sales(itemId, quantity);
                                }
                                case 5 -> {
                                    System.out.println("1.To update item \n2.new item");
                                    int ch = validateInteger();
                                    if (ch == 1) {
                                        System.out.println("enter the item id");
                                        itemId = sc.next();
                                        System.out.println("enter the count to add");
                                        quantity = validateFloat();
                                        manger.purchase(itemId, quantity);
                                    } else if (ch == 2) {
                                        String iName;
                                        float price;
                                        System.out.println("enter the item id");
                                        itemId = validateItemId();
                                        System.out.println("enter the item name");
                                        iName = validateItemName();
                                        System.out.println("enter the price");
                                        price = validateFloat();
                                        System.out.println("enter the quantity");
                                        quantity = validateFloat();
                                        Item item = new Item(itemId, iName, price, quantity);
                                        manger.addItem(item,username);
                                    } else {
                                        System.out.println("invalid choice");
                                    }
                                }
                                case 6-> {
                                    System.out.println("Exited successfully");
                                    flag=false;
                                }
                                default -> System.out.println("invalid choice");
                            }
                        } while (flag);
                    } else {
                        System.out.println("invalid username or password");
                    }
                }
                case 3 -> {
                    System.out.println("enter the username");
                    username = sc.next();
                    System.out.println("enter the password");
                    password = sc.next();
                    if (login.login(username, password,choice1)) {
                        SalesMan salesMan;
                        String itemId;
                        float quantity;
                        salesMan = Database.getSalesMan(username);
                        if (salesMan == null) {
                            System.out.println("salesman not found ");
                            break;
                        }
                        do {
                            System.out.println("1.view list \n2.sales\n3.exit");
                            choice2 = validateInteger();
                            switch (choice2) {
                                case 1 -> salesMan.showItemList();
                                case 2 -> {
                                    System.out.println("enter the item no ");
                                    itemId = sc.next();
                                    System.out.println("enter the no of quantity");
                                    quantity = validateFloat();
                                    salesMan.sales(itemId, quantity);
                                }
                                case 3 -> {
                                    System.out.println("Exited successfully");
                                    flag=false;
                                }
                                default -> System.out.println("invalid choice");
                            }
                        } while (flag);
                    } else {
                        System.out.println("invalid username or password");
                    }
                }
                default -> System.out.println("invalid choice");
            }
        }while(true);
    }
}