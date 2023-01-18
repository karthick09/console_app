public class Login {

    public boolean login(String id,String password,int choice){
        return Database.getLoginDetails(id,password,choice);
    }
}
