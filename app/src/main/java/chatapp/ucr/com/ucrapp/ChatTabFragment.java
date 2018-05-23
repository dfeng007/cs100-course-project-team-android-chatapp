package chatapp.ucr.com.ucrapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import chatapp.ucr.com.ucrapp.DatabaseClasses.AddToDatabase;
import chatapp.ucr.com.ucrapp.Message.Message;
import chatapp.ucr.com.ucrapp.Message.TextMessage;


public class ChatTabFragment extends Fragment {

    public ChatTabFragment() {
            //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.chat_tab, container, false);

        displayChatMessage(rootView);
        sendButtonSetup(rootView);
        return rootView;
    }

    //This class takes in the root View object (xml) in which the button is found
    //It tells the send button what to do when clicked
    public void sendButtonSetup(final View view) {
        FloatingActionButton fab;
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText)view.findViewById(R.id.text_box);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                Message msg = new TextMessage(input.getText().toString());
                AddToDatabase database = new AddToDatabase();

                database.addTextMessage(msg);


                displayChatMessage(view);

                // Clear the input
                input.setText("");
            }
        });
    }

    //This method sends a message to the firebase list options how to
    private void displayChatMessage(View rootView) {
        ListView listOfMessages = (ListView) rootView.findViewById(R.id.list);

        //Suppose you want to retrieve "chats" in your Firebase DB:
        Query query = FirebaseDatabase.getInstance().getReference().child("messages");
//The error said the constructor expected FirebaseListOptions - here you create them:
        FirebaseListOptions<TextMessage> options = new FirebaseListOptions.Builder<TextMessage>()
                .setQuery(query, TextMessage.class)
                .setLifecycleOwner(this)
                .setLayout(android.R.layout.simple_list_item_1)
                .build();
        //Finally you pass them to the constructor here:

        FirebaseListAdapter<TextMessage> adapter;
        adapter = new FirebaseListAdapter<TextMessage>(options) {

            @Override
            protected void populateView(View v, TextMessage model, int position) {
                TextView messageText = (TextView)v.findViewById(android.R.id.text1);
//                TextView messageText2 = (TextView)v.findViewById(android.R.id.text2);

                messageText.setText(model.getMessage());
//                messageText2.setText(model.getTitle());
            }

        };
        listOfMessages.setAdapter(adapter);
    }

}
