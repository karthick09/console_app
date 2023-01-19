import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

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


    public static void main(String[] args) {
        User user;
        Scanner sc = new Scanner(System.in);
        Account ac3= new Account("abc","123",AccountStatus.ACTIVE);
        Owner o =new Owner("karthick","m101","karthickm@gmnvia.fv","1234567890",ac3,UserType.OWNER,"owner");
        DatabaseManager.addOwner(o);
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
            System.out.println("enter the username");
            username = sc.next();
            System.out.println("enter the password");
            password = sc.next();
            switch (choice1){
                case 1 -> {
                    if (username.equals("abc") && password.equals("123")) {
                        do {
                            user = DatabaseManager.getOwner();
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
                            switch (choice2) {
                                case 1 -> ((Owner) user).addManager();
                                case 2 -> ((Owner) user).addSalesPerson();
                                case 3 -> {
                                    System.out.println("1.manger list \n2.salesman list \n3.item list");
                                    int ch = validateInteger();
                                    switch (ch) {
                                        case 1 -> DatabaseManager.getMangerList();
                                        case 2 -> DatabaseManager.getSaleManList();
                                        case 3 -> DatabaseManager.getItemList();
                                        default -> System.out.println("invalid choice");
                                    }
                                }
                                case 4 -> ((Owner) user).salary();
                                case 5 -> {
                                    System.out.println("1.total sales\n2.total purchase");
                                    int choice = validateInteger();
                                    ((Owner) user).turnover(choice);
                                }
                                case 6 -> ((Owner) user).addItem(((Owner) user).getPassword());
                                case 7 -> ((Owner) user).deleteItem( ((Owner) user).getPassword());
                                case 8 -> user.sales();
                                case 9 -> {
                                    System.out.println("1.To update item \n2.new item");
                                    int ch = validateInteger();
                                    if (ch == 1) {
                                        ((Owner) user).purchase();
                                    } else if (ch == 2) {
                                        ((Owner) user).addItem(((Owner) user).getPassword());
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
                    if (login.login(username, password,choice1)) {
                        user = DatabaseManager.getManger(username);
                        if (user == null) {
                            System.out.println("manager not found");
                            break;
                        }
                        do {
                            System.out.println("1.showItemList \n2.Add item \n3.Delete item \n4.sales \n5.purchase\n6.exit");
                            choice2 = validateInteger();
                            switch (choice2) {
                                case 1 -> ((Manger) user).showItemList();
                                case 2 -> ((Manger) user).addItem(username);
                                case 3 -> ((Manger) user).deleteItem(username);
                                case 4 -> user.sales();
                                case 5 -> {
                                    System.out.println("1.To update item \n2.new item");
                                    int ch = validateInteger();
                                    if (ch == 1) {
                                        ((Manger) user).purchase();
                                    } else if (ch == 2) {
                                        ((Manger) user).addItem(username);
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
                    if (login.login(username, password,choice1)) {
                        user = DatabaseManager.getSalesMan(username);
                        if (user == null) {
                            System.out.println("salesman not found ");
                            break;
                        }
                        do {
                            System.out.println("1.view list \n2.sales\n3.exit");
                            choice2 = validateInteger();
                            switch (choice2) {
                                case 1 -> ((SalesMan) user).showItemList();
                                case 2 -> user.sales();
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