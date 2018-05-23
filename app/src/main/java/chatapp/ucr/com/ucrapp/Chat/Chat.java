package chatapp.ucr.com.ucrapp.Chat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Chat {

    private String chatID;
    private String userID;
    private DatabaseReference root;

    public Chat() {
        chatID = "";
        root = FirebaseDatabase.getInstance().getReference().getRoot();
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public String createChat(){
        //need to create new unique chatID in database
        //store it and return unique chatID

        chatID = root.child("messages").push().getKey();

        return chatID;
    }

    public void setChatID(String chat){
        chatID = chat;
    }

    public String getChatID(){
        //return stored unique chatID;
        return chatID;
    }

}
