package chatapp.ucr.com.ucrapp.Main;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import chatapp.ucr.com.ucrapp.Chat.Chat;
import chatapp.ucr.com.ucrapp.DatabaseClasses.ChatList;
import chatapp.ucr.com.ucrapp.DatabaseClasses.ChatMetaData;

public class MetaDataAdapterDTB {
    private DatabaseReference root;
    private ArrayList<ChatMetaData> chatMetaData = new ArrayList<>();
    private String userID;
    private ChatList chatList;
    private int i;
    private MetaDataAdapter adapter;
    private final String TAG = "META";

    public MetaDataAdapterDTB(Context c,  DatabaseReference ref, String userID){
        root = ref;
        this.userID = userID;
        adapter = new MetaDataAdapter(c, retrieve());
    }

    public MetaDataAdapter getAdapter(){
        return adapter;
    }

    public ArrayList<ChatMetaData> retrieve(){

        retrieveMetaDataList();

        return chatMetaData;
    }

    private void retrieveMetaDataList(){

        root.child("chatLists").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    chatList = dataSnapshot.getValue(ChatList.class);

                    fetchData();
                }
                else{
                    root.child("chatLists").child(userID).setValue(new ChatList());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void fetchData(){
        for( i = 0; (chatList.getChatList().size() - 1) > i ; i++ ) {
            root.child("chats").child(chatList.getChatList().get(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if( i == 0 ) {
                        chatMetaData.clear();
                    }

                    chatMetaData.add(dataSnapshot.getValue(ChatMetaData.class));

                    if (i == (chatList.getChatList().size() - 1)){
                        adapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
