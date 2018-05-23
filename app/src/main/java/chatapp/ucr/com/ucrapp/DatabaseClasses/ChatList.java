package chatapp.ucr.com.ucrapp.DatabaseClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatList {

    private ArrayList<Map> chatList;

    public ChatList(){

    }

    public void addChat(String chatID, String title){

        Map<String, String> chatMap = new HashMap<>();
        chatMap.put(chatID, title);

        chatList.add(chatMap);
    }
    
    public ArrayList<Map> getChatList(){
        return chatList;
    }


}
