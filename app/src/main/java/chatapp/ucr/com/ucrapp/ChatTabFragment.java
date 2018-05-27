package chatapp.ucr.com.ucrapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.EditorInfo;
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

    protected FirebaseListAdapter<TextMessage> adapter;

    public ChatTabFragment() {
            //Required empty constructor
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.chat_tab, container, false);
        //ListView l;
        //final ArrayList<String> days = new ArrayList<>();

        displayChatMessage(rootView);

//        FloatingActionButton fab;
//        fab = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton2);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText input = (EditText)rootView.findViewById(R.id.text_box);
//
//                // Read the input field and push a new instance
//                // of ChatMessage to the Firebase database
//                Message msg = new TextMessage(input.getText().toString());
//                AddToDatabase database = new AddToDatabase();
//
//                database.addTextMessage(msg);
//
//
//                displayChatMessage(rootView);
//
//                // Clear the input
//                input.setText("");
//            }
//        });

        EditText messageView = (EditText) rootView.findViewById(R.id.text_box);
        messageView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                boolean handled = false;
                if (id == EditorInfo.IME_ACTION_SEND) {
                    //my own action here, put method
                    EditText input = (EditText)rootView.findViewById(R.id.text_box);

                    // Read the input field and push a new instance
                    // of ChatMessage to the Firebase database
                    Message msg = new TextMessage(input.getText().toString());
                    AddToDatabase database = new AddToDatabase();

                    database.addTextMessage(msg);


                    displayChatMessage(rootView);

                    // Clear the input
                    input.setText("");
                    handled = true;
                }

                return handled;
            }

        });
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void displayChatMessage(View view) {
        ListView listOfMessages = (ListView) view.findViewById(R.id.list);

        //Suppose you want to retrieve "chats" in your Firebase DB:
        Query query = FirebaseDatabase.getInstance().getReference().child("messages");
//The error said the constructor expected FirebaseListOptions - here you create them:
        FirebaseListOptions<TextMessage> options = new FirebaseListOptions.Builder<TextMessage>()
                .setQuery(query, TextMessage.class)
                .setLifecycleOwner(this)
                .setLayout(android.R.layout.simple_list_item_1)
                .build();
        //Finally you pass them to the constructor here:

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
