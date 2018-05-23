package chatapp.ucr.com.ucrapp.DatabaseClasses;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import chatapp.ucr.com.ucrapp.Message.Message;
import chatapp.ucr.com.ucrapp.Message.TextMessage;

public class AddToDatabase {

    private FirebaseDatabase database;
    private DatabaseReference myref;


    public AddToDatabase() {
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
    }

    public void addUser(String userID, UserInformation userInfo) {
        myref.child("users").child(userID).setValue(userInfo);
    }

    public void addTextMessage(Message newMessage, String chatID) {
        myref.child("messages").child(chatID).push().setValue(newMessage);
    }

    public void addChatMetaData(ChatMetaData metaData){

    }

    public void addFriendsList(FriendsList friendsList){

    }

    public void addChatList(ChatList chatList){

    }
}
