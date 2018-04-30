package team1.cs100.ucrchatapp;

import android.content.Intent;
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





        //test
        /*
         * NOTE: To team members I need to include this because without it ounce your
         * sign in you can't signed out because of the google "smartlock".
         * When the sign out logic is implemented go ahead and remove this.
         * Also this will skip the MainActivaity page becuase the user is never going tp be signed
         * in. So if you are testing the registering and it keeps taking you back to start activity
         * thats why.
         */
         //mAuth.signOut();

    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

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
            Toast.makeText(this, "Welcome Back!!!" + FirebaseAuth.getInstance()
                    .getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();

        }
    }

    private void sendToStart() {
        //Start sign in/sign up process
        Intent startActivityIntent = new Intent(MainActivity.this, StartActivty.class);
        startActivity(startActivityIntent);
        finish();
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

            Toast.makeText(this, "Sign Out Successfull", Toast.LENGTH_SHORT).show();
        } else {

        }

        return true;
    }
}
