package chatapp.ucr.com.ucrapp.DatabaseClasses;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public void addChatMetaData(ChatMetaData metaData, String chatID){
        myref.child("chats").child(chatID).setValue(metaData);
    }

    public void addFriendsList(FriendsList friendsList, String userID){
        myref.child("friendLists").child(userID).setValue(friendsList);
    }

    public void addChatList(ChatList chatList, String userID){
        myref.child("chatLists").child(userID).setValue(chatList);
    }

    public void addUsersList(UsersList usersList){
        myref.child("usersList").setValue(usersList);
    }

    public void registerNewUser(final String userID, String username, String email){
        UserInformation userInfo = new UserInformation(username, email);
        addUser(userID, userInfo);

        myref.child("usersList").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("usersList").exists()){
                        UsersList usersList = dataSnapshot.getValue(UsersList.class);

                        usersList.addNewUser(userID);
                        addUsersList(usersList);
                    }
                    else {
                        UsersList x = new UsersList();
                        x.addNewUser(userID);
                        myref.child("usersList").setValue(x);
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
