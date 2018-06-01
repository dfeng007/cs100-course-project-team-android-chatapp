package chatapp.ucr.com.ucrapp.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import chatapp.ucr.com.ucrapp.Chat.Chat;
import chatapp.ucr.com.ucrapp.DatabaseClasses.AddToDatabase;
import chatapp.ucr.com.ucrapp.DatabaseClasses.UsersList;
import chatapp.ucr.com.ucrapp.Message.Message;
import chatapp.ucr.com.ucrapp.R;

public class ChatActivity extends AppCompatActivity {

    private String userID;
    private ChatAdapter adapter;
    private AddToDatabase addToDatabase = new AddToDatabase();
    private String chatID;
    private String username = "ERROR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getUserName();

        ListView mListView = (ListView) findViewById(R.id.chatListView);



            chatID = getIntent().getStringExtra("chatapp.ucr.com.ucrapp.CHAT_ID");


        final ChatAdapterDTB helper = new ChatAdapterDTB(this, FirebaseDatabase.getInstance().getReference().getRoot(), userID, chatID);
        adapter = helper.getAdapter();
        mListView.setAdapter(adapter);

        setupEnterButton();

    }

    //This sets up the Enter button on the activity_chat.xml
    private void setupEnterButton() {

        final Button enterButton = (Button) findViewById(R.id.enterButton);
        final TextView messageBox = (TextView) findViewById(R.id.messageEditText);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToDatabase.addMessage(new Message(messageBox.getText().toString(), username), chatID);
                messageBox.setText("");
            }
        });
    }

    //This gets the username for the user currently signed in.
    private void getUserName() {
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference().getRoot().child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(userID) != null) {
                            username = dataSnapshot.child(userID).child("userName")
                                    .getValue().toString();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
