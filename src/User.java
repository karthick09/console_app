import java.time.LocalDate;

public abstract class User {
    private final String name;
    private final String userId;
    private String email;
    private String phone;
    private final LocalDate dateOfJoin;
    private final Account account;
    private final UserType userType;

    public User(String name, String userId, String email, String phone, Account account, UserType userType) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
        this.dateOfJoin=LocalDate.of(2022,1,4);
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDate getDateOfJoin() {
        return dateOfJoin;
    }

    public String getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }
}
