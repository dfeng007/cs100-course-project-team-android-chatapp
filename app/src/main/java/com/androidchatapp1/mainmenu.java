package com.androidchatapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class mainmenu extends AppCompatActivity{
    Button tofriend;
    Button tofindfriend;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        tofriend = (Button)findViewById( R.id.user_button );
        tofindfriend = (Button)findViewById( R.id.button_findfriend );
        // tofriend function
        tofriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainmenu.this, Users.class));
            }
        });
        // to findfriend funtction
        tofindfriend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity( new Intent(mainmenu.this,findfriend.class) );
            }


        });

    }

}
