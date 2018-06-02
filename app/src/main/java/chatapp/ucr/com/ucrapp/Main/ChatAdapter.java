package chatapp.ucr.com.ucrapp.Main;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import chatapp.ucr.com.ucrapp.DatabaseClasses.UserInformation;
import chatapp.ucr.com.ucrapp.Message.Message;
import chatapp.ucr.com.ucrapp.R;

public class ChatAdapter extends BaseAdapter{
    private ArrayList<Message> messageList;
    private LayoutInflater mInflater;
    private String userID;

    public ChatAdapter(Context c, ArrayList<Message> messageList, String userID){
        this.messageList = messageList;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userID = userID;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        if(messageList.size() > position )
            return messageList.get(position);
        else{
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.received_messages, null);

        if(messageList.get(position).getUrl().isEmpty()) {
            TextView userNameTextView = (TextView) v.findViewById(R.id.text_message_name);
            TextView messageBodyTextView = (TextView) v.findViewById(R.id.text_message_body);
            TextView dateTextView = (TextView) v.findViewById(R.id.text_message_time);

            userNameTextView.setText(messageList.get(position).getUsername());
            messageBodyTextView.setText(messageList.get(position).getMessage());
            dateTextView.setText(messageList.get(position).getDate());
        }
        else {
            v = mInflater.inflate(R.layout.image_received_messages, null);
            TextView userNameTextView = (TextView) v.findViewById(R.id.text_message_name);
            ImageView imageMessageView = (ImageView) v.findViewById(R.id.image_message_body);
            TextView dateTextView = (TextView) v.findViewById(R.id.text_message_time);

            userNameTextView.setText(messageList.get(position).getUsername());
            Picasso.get().load(messageList.get(position).getUrl()).resize(300,300).into(imageMessageView);
            dateTextView.setText(messageList.get(position).getDate());
        }
        return v;
    }


}
