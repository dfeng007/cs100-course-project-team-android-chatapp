
package team1.cs100.ucrchatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "Register Page";

    //widget and component instances
    private TextInputLayout mRegUserName;
    private TextInputLayout mRegUserEmail;
    private TextInputLayout mRegUserPassword;

    //button
    private Button mRegCreateButton;

    /*Firebase Authentication*/
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*initialize the FirebaseAuth instance*/
        mAuth = FirebaseAuth.getInstance();

        //assignment of fields and widgets
        mRegUserName = findViewById(R.id.reg_name_field);
        mRegUserEmail = findViewById(R.id.reg_email_field);
        mRegUserPassword = findViewById(R.id.reg_password_field);

        mRegCreateButton = findViewById(R.id.reg_create_button);

        //listen for button click
        mRegCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get strings from all the fields
                String user_name = mRegUserName.getEditText().getText().toString();
                String user_email = mRegUserEmail.getEditText().getText().toString();
                String user_password = mRegUserPassword.getEditText().getText().toString();

                //create account
                createAccount(user_name, user_email, user_password);
            }
        });
    }

    private void createAccount(String user_name, String user_email, String user_password) {
        Log.d(TAG, "createAccount:" + user_email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(user_email, user_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            /*NOTE:
                            * will also add user name to firebase I don't know how to do that
                            * yet if any of you know please add it*/

                            //send user to MainActvity which is the users home page
                            Intent mainIntent = new Intent(RegisterActivity.this,
                                                                                MainActivity.class);
                            startActivity(mainIntent);
                            //we don't want the user to return to the page
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mRegUserEmail.getEditText().getText().toString();
        if (TextUtils.isEmpty(email)) {
            mRegUserEmail.setError("Required.");
            valid = false;
        } else {
            mRegUserEmail.setError(null);
        }

        String password = mRegUserPassword.getEditText().getText().toString();
        if (TextUtils.isEmpty(password)) {
            mRegUserPassword.setError("Required.");
            valid = false;
        } else {
            mRegUserPassword.setError(null);
        }

        return valid;
    }
}
