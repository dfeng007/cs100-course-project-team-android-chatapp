package chatapp.ucr.com.ucrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import chatapp.ucr.com.ucrapp.DatabaseClasses.ChatMetaData;
import chatapp.ucr.com.ucrapp.Main.ChatActivity;
import chatapp.ucr.com.ucrapp.Main.ChatInfoActivity;
import chatapp.ucr.com.ucrapp.Main.MetaDataAdapter;
import chatapp.ucr.com.ucrapp.Main.MetaDataAdapterDTB;


public class MainActivity extends AppCompatActivity {


    /*Firebase Authentication*/
    private FirebaseAuth mAuth;
    private DatabaseReference root;
    private MetaDataAdapterDTB adapterHelper;
    private String userID;
    private MetaDataAdapter adapter;
    private String TAG = "MAIN";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"Main Test");

        /*initialize the FirebaseAuth instance*/
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance().getReference();
        userID = mAuth.getCurrentUser().getUid();

        Button editProfileButton = (Button) findViewById(R.id.editProfileButton);
        Button friendsListButton = (Button) findViewById(R.id.friendsListButton);
        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        Button createChatButton = (Button) findViewById(R.id.createChatButton);

        Log.d(TAG,"Main Test");

        ListView mainListView = (ListView) findViewById(R.id.mainListView);

        adapterHelper = new MetaDataAdapterDTB(MainActivity.this, root, userID);

        adapter = adapterHelper.getAdapter();
        mainListView.setAdapter(adapter);
        
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToProfile();
            }
        });
        
        friendsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFriendsList();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                sendToStart();
            }
        });

        createChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToCreateChat();
            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<ChatMetaData> metaDataList = adapter.getMetaDataList();

                sendToChat(metaDataList.get(position).getChatID());
            }
        });

    }

    public void sendToChat(String chatID){
        Intent intent = new Intent(this, ChatActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("chatapp.ucr.com.ucrapp.USER_ID", userID);
        bundle.putString("chatapp.ucr.com.ucrapp.CHAT_ID", chatID);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    private void sendToCreateChat() {
        Intent intent = new Intent(this, ChatInfoActivity.class);
        startActivity(intent);
    }

    private void sendFriendsList() {
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "On Start");
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);  //if user is Signed in then UI updates to MainActivity and if not signed in then to ActivityStart


    }

    private void updateUI(FirebaseUser currentUser) {
        /*
         * ************************************************************************************
         * Checking if current user is logged in if yes then perform and load chat logic and ui.
         * Else move to StartPageActivty to sign in or register the current user
         **************************************************************************************/
        if (currentUser == null) {
            Log.d(TAG, "updateUI");

            //send to start activity
            sendToStart();

        } else {
            //user is already signed in. Therefor display a welcome toast
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Toast.makeText(this, "Welcome Back!", Toast.LENGTH_SHORT).show();

        }
    }

    private void sendToStart() {
        //Start sign in/sign up process
        Intent startActivityIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startActivityIntent);
        finish();
    }

    private void sendToProfile() {
        //Send to profile page
        Intent startActivityIntent = new Intent(MainActivity.this, CustomizeActivity.class);
        startActivity(startActivityIntent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_action_signout) {
            //Signout button in the toolbar was pressed log the user out
            mAuth.signOut();

            //send to start activity
            sendToStart();

            Toast.makeText(this, "Sign Out Successful", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.main_action_profile){
            sendToProfile();
        }
        return true;
    }
}
