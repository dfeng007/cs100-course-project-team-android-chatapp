package chatapp.ucr.com.ucrapp.Chat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import chatapp.ucr.com.ucrapp.DatabaseClasses.AddToDatabase;
import chatapp.ucr.com.ucrapp.DatabaseClasses.ChatMetaData;
import chatapp.ucr.com.ucrapp.DatabaseClasses.UserInformation;

public class Chat {

    private String chatID;
    private String userID;
    private String title;
    private String details;
    private DatabaseReference root;

    public Chat(String userID){
        chatID = "";
        this.title = "";
        this.details = "";
        root = FirebaseDatabase.getInstance().getReference().getRoot();
        this.userID = userID;
    }

    public Chat(String userID, String title, String details) {
        chatID = "";
        this.title = title;
        this.details = details;
        root = FirebaseDatabase.getInstance().getReference().getRoot();
        this.userID = userID;
    }

    public String createChat(){
        //need to create new unique chatID in database
        //store it and return unique chatID

        chatID = root.child("messages").push().getKey();
        AddToDatabase addToDatabase = new AddToDatabase();
        addToDatabase.addNewChat(chatID, userID);

        final ChatMetaData chatMetaData = new ChatMetaData();
        chatMetaData.addUserToChat(userID);
        chatMetaData.setTitle(title);
        chatMetaData.setDetails(details);
        chatMetaData.setChatID(chatID);

        root.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                chatMetaData.setUsername(userInformation.getUserName());

                root.child("chats").child(chatID).setValue(chatMetaData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return chatID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setChatID(String chat){
        chatID = chat;
    }

    public String getChatID(){
        //return stored unique chatID;
        return chatID;
    }

}
