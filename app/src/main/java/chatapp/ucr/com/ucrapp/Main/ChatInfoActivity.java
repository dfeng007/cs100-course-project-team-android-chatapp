package chatapp.ucr.com.ucrapp.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

import chatapp.ucr.com.ucrapp.Chat.Chat;
import chatapp.ucr.com.ucrapp.DatabaseClasses.AddToDatabase;
import chatapp.ucr.com.ucrapp.DatabaseClasses.FriendsList;
import chatapp.ucr.com.ucrapp.R;

public class ChatInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_info);

        ListView mListView = findViewById(R.id.friendsListView);

        final TextView topicTextView = findViewById(R.id.topicEditText);
        final TextView descriptionTextView = findViewById(R.id.descriptionEditText);
        Button addFriendsButton = findViewById(R.id.addFriendsButton);

        addFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FriendsListActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("TITLE", topicTextView.getText().toString());
                bundle.putString("DESCRIPTION", descriptionTextView.getText().toString());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
