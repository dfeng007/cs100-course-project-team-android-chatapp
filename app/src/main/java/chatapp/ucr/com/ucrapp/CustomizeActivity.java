package chatapp.ucr.com.ucrapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CustomizeActivity extends AppCompatActivity {


    //Profile Pic displayed
    //private ImageView profile_pic;
    private String TAG;

    //textInput instances 2
    private TextInputLayout my_newName;
    private TextInputLayout my_newEmail;
    private TextInputLayout my_newImage;
    //private android.support.v7.widget.Toolbar mRegToolBar;

    //button
    private Button SaveBtn;
    private Button CancelBtn;
    //private Button editBtn;


    /*Firebase Authentication*/
    private FirebaseAuth mAuth;

//    private String userID;
//
//
    //progress dialog
    private ProgressDialog mRegProgressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        //initialize Firebase Instance
        mAuth = FirebaseAuth.getInstance();

        //assignment if fields and widgets
        my_newName = findViewById(R.id.reg_name_field);
        my_newEmail = findViewById(R.id.reg_email_field);
        my_newImage = findViewById(R.id.reg_image_field);

        SaveBtn = findViewById(R.id.reg_save_button);
        CancelBtn = findViewById(R.id.reg_cancel_button);

        mRegProgressDialog = new ProgressDialog(this);
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get all string fields
                String new_name = my_newName.getEditText().getText().toString();
                String new_email = my_newEmail.getEditText().getText().toString();
                String new_image =  my_newImage.getEditText().getText().toString();

                //show the progress dialog
                mRegProgressDialog.setTitle("Updating User Information");
                mRegProgressDialog.setMessage("Please wait while we manage your account.");
                mRegProgressDialog.setCanceledOnTouchOutside(false);
                mRegProgressDialog.show();

                //function or object that handles updating the database
                //updateAccount(new_name, new_email, new_image);

                //there are two functions that update Authentication info,
                // aside from the Realtime Database

//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                        .setDisplayName("Jane Q. User")
//                        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
//                        .build();
//
//                user.updateProfile(profileUpdates)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Log.d(TAG, "User profile updated.");
//                                }
//                            }
//                        });
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                user.updateEmail("user@example.com")
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Log.d(TAG, "User email address updated.");
//                                }
//                            }
//                        });


                //SendToMain();
            }


        });
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //the cancel button should technically just go back to the previous activity
            }
        });

    }


    private void updateAccount(final String new_name, final String new_email, final String new_image){
        Log.d(TAG, "updateAccount:" + new_name);
        //NEEDS WORK HERE




    }














    private void sendToMain() {
        //send user to MainActvity which is the users home page
        Intent mainIntent = new Intent(CustomizeActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
//        //we don't want the user to return to the page
//        finish();
    }
}
