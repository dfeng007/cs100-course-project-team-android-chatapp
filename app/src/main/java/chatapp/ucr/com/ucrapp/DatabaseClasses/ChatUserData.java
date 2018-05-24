package chatapp.ucr.com.ucrapp.DatabaseClasses;

public class ChatUserData{
    private String userID;
    private Boolean isTyping;

    public ChatUserData(){ userID = ""; isTyping = false; }

    public void setUserID(String id){ userID = id; }
    public String getUserID(){ return userID; }
    public void setIsTyping(Boolean bool){ isTyping = bool; }
    public Boolean getIsTyping(){ return isTyping; }
}