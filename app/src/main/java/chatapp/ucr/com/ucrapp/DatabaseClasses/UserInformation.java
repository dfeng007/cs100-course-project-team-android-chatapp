package chatapp.ucr.com.ucrapp.DatabaseClasses;

public class UserInformation {

    private String userName;
    private String password;
    private String email;


    public UserInformation() {
        this.userName = "";
        this.email = "";
        this.password = "";
    }

    public UserInformation(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;

    }

    public void setUserName(String userpasswordInput) {
        this.userName = userpasswordInput;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String user_emailInput) {
        this.email = user_emailInput;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}

