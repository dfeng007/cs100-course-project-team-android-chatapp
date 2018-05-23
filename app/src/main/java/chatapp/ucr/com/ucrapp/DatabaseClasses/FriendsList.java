package chatapp.ucr.com.ucrapp.DatabaseClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendsList {

    private ArrayList<Map> friendList;

    public FriendsList(){
    }

    public void addFriend(String friendUID){
        Map<String, Boolean> friendMap = new HashMap<>();
        friendMap.put(friendUID, false);
        friendList.add(friendMap);
    }

    public ArrayList<Map> getFriendList(){
        return friendList;
    }
}
