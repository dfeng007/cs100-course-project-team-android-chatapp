package chatapp.ucr.com.ucrapp.DatabaseClasses;

import java.util.ArrayList;
import java.util.Map;

public class ChatMetaData {
    private String lastMessage;
    private String timeStamp;
    private String username;
    private String title;
    private ArrayList<Map> chatMembers;

    public ChatMetaData(){
        lastMessage = "";
        timeStamp = "";
        username = "";
        title = "";
    }

    public void setLastMessage(String message){
        lastMessage = message;
    }

    public String getLastMessage(){
        return lastMessage;
    }

    public void setTimeStamp(String time){
        timeStamp = time;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public void setUsername(String user){
        username = user;
    }

    public String getUsername(){
        return username;
    }

    public void setTitle(String chatTitle){
        title = chatTitle;
    }

    public String getTitle(){
        return title;
    }

}
