package chatapp.ucr.com.ucrapp.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import chatapp.ucr.com.ucrapp.DatabaseClasses.ChatMetaData;
import chatapp.ucr.com.ucrapp.R;

public class MetaDataAdapter extends BaseAdapter {
    private ArrayList<ChatMetaData> metaDataList;
    private LayoutInflater mInflater;


    public MetaDataAdapter(Context c, ArrayList<ChatMetaData> metaData){
        metaDataList = metaData;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return metaDataList.size();
    }

    @Override
    public Object getItem(int position) {
        if((metaDataList.size() - 1) >= position )
        return metaDataList.get(position);
        else{
            return null;
        }
    }

    public ArrayList<ChatMetaData> getMetaDataList() {
        return metaDataList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView titleTextView = (TextView) v.findViewById(R.id.titleTextView);
        TextView usernameTextView = (TextView) v.findViewById(R.id.usernameEditText);
        TextView messageTextView = (TextView) v.findViewById(R.id.messageTextView);

        String title = metaDataList.get(position).getTitle();
        String username = metaDataList.get(position).getUsername();
        String message = metaDataList.get(position).getLastMessage();

        if(message.length() > 38){
            message = message.substring(0, 37) + "...";
        }

        titleTextView.setText(title);
        usernameTextView.setText(username);
        messageTextView.setText(message);

        return v;
    }
}
