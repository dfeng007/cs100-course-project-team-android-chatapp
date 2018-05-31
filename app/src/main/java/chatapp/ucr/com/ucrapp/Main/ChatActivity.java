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
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final TextView messageBox = (TextView) findViewById(R.id.messageEditText);
        ListView mListView = (ListView) findViewById(R.id.chatListView);
        final Button enterButton = (Button) findViewById(R.id.enterButton);

        chatID = getIntent().getExtras().getString("CHATID");

        final ChatAdapterDTB helper = new ChatAdapterDTB(this, FirebaseDatabase.getInstance().getReference().getRoot(), userID, chatID);

        adapter = helper.getAdapter();
        mListView.setAdapter(adapter);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDatabase.addMessage(new Message(messageBox.getText().toString(), "TestUser"), chatID);
                messageBox.setText("");
            }
        });
    }
}
