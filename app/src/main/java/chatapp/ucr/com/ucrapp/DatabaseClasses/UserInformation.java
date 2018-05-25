package chatapp.ucr.com.ucrapp.DatabaseClasses;

public class UserInformation {

    private String userName;
    private String email;
    private String picUrl;
    private Boolean online;

    public UserInformation() {
        this.userName = "";
        this.email = "";
        this.online = false;
        this.picUrl = "";
    }

    public UserInformation(String userName, String email) {
        this.userName = userName;
        this.email = email;
        this.online = false;
        this.picUrl = "";
    }

    public void setOnline(Boolean online){ this.online = online; }

    public Boolean getOnline() { return online; }

    public void setUserName(String userpasswordInput) {
        this.userName = userpasswordInput;
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

    public void setPicUrl(String ulr){ picUrl = ulr; }

    public String getPicUrl(){ return picUrl; }
}

