public class Login {

    public boolean login(String id,String password,int choice){
        return DatabaseManager.getLoginDetails(id,password,choice);
    }
}
