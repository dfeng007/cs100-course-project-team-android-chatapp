package com.androidchatapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private static final String TAG = "Register Page";
    TextView register;
    EditText email_login, password;
    private ProgressDialog mProgressDialog;
    Button loginButton;
    String user, pass, email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);

        register = (TextView)findViewById(R.id.register);
        email_login = (EditText)findViewById(R.id.Login_email);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email_login.getText().toString();
                pass = password.getText().toString();


                    mProgressDialog.setTitle("Signing in...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    signIn(email, pass);
//                    String url = "https://androidchatapp1-5500b.firebaseio.com/users.json";
//                    final ProgressDialog pd = new ProgressDialog(Login.this);
//                    pd.setMessage("Loading...");
//                    pd.show();
//
//                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
//                        @Override
//                        public void onResponse(String s) {
//                            if(s.equals("null")){
//                                Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
//                            }
//                            else{
//                                try {
//                                    JSONObject obj = new JSONObject(s);
//
//                                    if(!obj.has(user)){
//                                        Toast.makeText(Login.this, "user not found", Toast.LENGTH_LONG).show();
//                                    }
//                                    else if(obj.getJSONObject(user).getString("password").equals(pass)){
//                                        email = obj.getJSONObject(user).getString("email").toString();
//
//                                        UserDetails.email = email;
//                                        UserDetails.username = user;
//                                        UserDetails.password = pass;
//                                        startActivity(new Intent(Login.this, mainmenu.class));
//                                    }
//                                    else {
//                                        Toast.makeText(Login.this, "incorrect password", Toast.LENGTH_LONG).show();
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            pd.dismiss();
//                        }
//                    },new Response.ErrorListener(){
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            System.out.println("" + volleyError);
//                            pd.dismiss();
//                        }
//                    });
//
//                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
//                    rQueue.add(request);


            }
        });
    }
    private void signIn(String email, String password) {
//        if (!validateForm()) {
//            return;
//        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //dismiss progress dialog
                            mProgressDialog.dismiss();

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();

                            //move to the MainActivity
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserDetails.id = user.getUid();
                            //get firebase user
                            UserDetails.email = user.getEmail();
                            UserDetails.username = user.getDisplayName();
                            Intent mainIntent = new Intent(Login.this,
                                    mainmenu.class);
                            //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            //we don't want the user to return to the page
                            finish();

                        } else {
                            //hide progress dialog
                            mProgressDialog.hide();

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

}
// FirebaseUser user = FirebaseAuth.getIntance().getCurrentUser();
// how to get ID
// id = user.getUid()

// use FirebaseDatabase.getInstance()
// mIDR = mFirebaseDatabase.getINstance()
// mIDR.child("users").child(id).child("username").setValue(USername)