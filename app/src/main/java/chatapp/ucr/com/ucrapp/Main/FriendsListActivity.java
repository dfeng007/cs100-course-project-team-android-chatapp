package chatapp.ucr.com.ucrapp.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import chatapp.ucr.com.ucrapp.DatabaseClasses.ChatMetaData;
import chatapp.ucr.com.ucrapp.DatabaseClasses.FriendsList;
import chatapp.ucr.com.ucrapp.DatabaseClasses.UsersList;
import chatapp.ucr.com.ucrapp.MainActivity;
import chatapp.ucr.com.ucrapp.R;

public class FriendsListActivity extends AppCompatActivity {

    private String userID;
    private ChatInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Button addFriendsButton = (Button) findViewById(R.id.addButton);
        ListView mListView = (ListView) findViewById(R.id.friendsListView);

        ChatInfoAdapterDTB helper = new ChatInfoAdapterDTB(this, FirebaseDatabase.getInstance().getReference().getRoot(),userID);

        adapter = helper.getAdapter();
        mListView.setAdapter(adapter);



        addFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Boolean> isCheckedList = adapter.getIsCheckedList();
                UsersList usersList = adapter.getUsersList();
                ArrayList<String> chatMembers = new ArrayList<>();

                for (int i = 0; i < isCheckedList.size(); i++) {
                    if (isCheckedList.get(i)) {
                        chatMembers.add(usersList.getUsersList().get(i));
                    }
                }

                for (int i = 0; i < (chatMembers.size()); i++) {



                }

                Intent intent = new Intent(FriendsListActivity.this, ChatActivity.class);
                intent.putStringArrayListExtra("CHAT_MEMBER", chatMembers);
                startActivity(intent);
            }
        });
    }
}
