
package team1.cs100.ucrchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivty extends AppCompatActivity {

    //widget and component instances
    private Button mRegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activty);

        //buttons
        mRegButton = findViewById(R.id.start_register_button);

        //listen for button click
        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //test
                //sending the user to register activity
                Intent regIntent = new Intent(StartActivty.this, RegisterActivity.class);
                startActivity(regIntent);

            }
        });

    }
}