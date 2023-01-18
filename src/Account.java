public class Account {
    private final String username;
    private  String password;
    private AccountStatus status;

    public Account(String id, String password,  AccountStatus status) {
        this.username = id;
        this.password = password;
        this.status = status;
    }

    public String getId() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

}
