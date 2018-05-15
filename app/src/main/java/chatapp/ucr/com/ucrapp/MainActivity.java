package chatapp.ucr.com.ucrapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    /*widget and views instances*/
    private android.support.v7.widget.Toolbar mMainToolBar;


    /*Firebase Authentication*/
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*initialize the FirebaseAuth instance*/
        mAuth = FirebaseAuth.getInstance();

        //define
        mMainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mMainToolBar);
        getSupportActionBar().setTitle("UCR Chat App");


    }

    @Override
    public void onStart() {
        super.onStart();

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
            //send to start activity
            sendToStart();

        } else {
            //user is already signed in. Therefor display a welcome toast
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String name = user.getDisplayName();
            Toast.makeText(this, "Welcome Back!" + name, Toast.LENGTH_SHORT).show();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        return  true;
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
