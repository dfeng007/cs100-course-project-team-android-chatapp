package com.androidchatapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    private static final String TAG = "Register Page";
    EditText username, password,emailname,id;
    Button registerButton;
    String user, pass,email;
    TextView login;
    //progress dialog
    private ProgressDialog mRegProgressDialog;
    //progress dialog
    /*Firebase Authentication*/
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_register);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        emailname = (EditText)findViewById( R.id.email );
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        mRegProgressDialog = new ProgressDialog( Register.this);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                email = emailname.getText().toString();
                mRegProgressDialog.setTitle("Registering user");
                mRegProgressDialog.setMessage("Please wait while we create your account");
                mRegProgressDialog.setCanceledOnTouchOutside(false);
                mRegProgressDialog.show();
                if(user.equals("")){
                    username.setError("can't be blank");
                }
                else if(pass.equals("")){
                    password.setError("can't be blank");
                }
                else if(!user.matches("[A-Za-z0-9]+")){
                    username.setError("only alphabet or number allowed");
                }
                else if(user.length()<5){
                    username.setError("at least 5 characters long");
                }
                else if(pass.length()<5){
                    password.setError("at least 5 characters long");
                }
                else if(email.equals("")){
                    emailname.setError( "can't be blank" );
                }
                else {
                    createAccount(user, email, pass);
                }
//                    final ProgressDialog pd = new ProgressDialog(Register.this);
//                    pd.setMessage("Loading...");
//                    pd.show();
//
//                    String url = "https://androidchatapp1-5500b.firebaseio.com/users.json";
//
//                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
//                        @Override
//                        public void onResponse(String s) {
//                            Firebase reference = new Firebase("https://androidchatapp1-5500b.firebaseio.com/users");
//                            if(s.equals("null")) {
//                                reference.child(user).child("email").setValue(email);
//                                reference.child(user).child("password").setValue(pass);
//                                //createAccount(user,pass,email);
//                                //reference1.child(email).child( "password").setValue( pass);
//                                //reference1.child(email).child( "user").setValue( user);
//                                Toast.makeText(Register.this, "registration successfulPlease click Login button to back to Login-menu", Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                try {
//                                    JSONObject obj = new JSONObject(s);
//
//                                    if (!obj.has(user)) {
//                                        reference.child(user).child("email").setValue(email);
//                                        reference.child(user).child("password").setValue(pass);
//                                        //createAccount(user,pass,email);
//                                        //reference.child(email).child( "password").setValue( pass);
//                                        //reference.child(email).child( "user").setValue( user);
//                                        Toast.makeText(Register.this, "registration successful Please click Login button to back to Login-menu", Toast.LENGTH_LONG).show();
//                                    } else {
//                                        Toast.makeText(Register.this, "username already exists Please click Login button to back to Login-menu", Toast.LENGTH_LONG).show();
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            pd.dismiss();
//                        }
//
//                    },new Response.ErrorListener(){
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            System.out.println("" + volleyError );
//                            pd.dismiss();
//                        }
//                    });
//
//                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
//                    rQueue.add(request);
//                }
            }
        });


        }

    private void createAccount(final String user_name, final String user_email, final String user_password) {
        Log.d(TAG, "createAccount:" + user_email);
        mAuth.createUserWithEmailAndPassword(user_email, user_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            //dismiss progress dialog
                            mRegProgressDialog.dismiss();

                            //add user name to firbase
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            //id = user.getUid();
                            //get firebase user
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.child("users").child(user.getUid()).child( "username").setValue( user_name );
                            ref.child("users").child(user.getUid()).child( "email").setValue( user_email );
                            ref.child("users").child(user.getUid()).child( "password").setValue( user_password );
                            // add 1
//                            //DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
//                            ref.child("name").child(user_name).child( "email").setValue( user_email );
//                            ref.child("name").child(user_name).child( "password").setValue( user_password );
//                            ref.child("name").child(user_name).child( "userID").setValue( user.getUid());

                                    //reference.child(user).child("email").setValue(email);
//                                reference.child(user).child("password").setValue(pass);
                            // FirebaseUser user = FirebaseAuth.getIntance().getCurrentUser();
                            // how to get ID
                            // id = user.getUid()

                            // use FirebaseDatabase.getInstance()
                            // mIDR = mFirebaseDatabase.getINstance()
                            // mIDR.child("users").child(id).child("username").setValue(USername)

                            /*NOTE:
                             * will also add user name to firebase I don't know how to do that
                             * yet if any of you know please add it*/
                            Toast.makeText(Register.this, "registration successfulPlease click Login button to back to Login-menu", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            //hind the progress bar
                            mRegProgressDialog.hide();

                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}