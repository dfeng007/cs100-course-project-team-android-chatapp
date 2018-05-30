package chatapp.ucr.com.ucrapp.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import chatapp.ucr.com.ucrapp.DatabaseClasses.UserInformation;
import chatapp.ucr.com.ucrapp.DatabaseClasses.UsersList;
import chatapp.ucr.com.ucrapp.R;

public class ChatInfoAdapter extends BaseAdapter {

    private ArrayList<UserInformation> usersInformation;
    private LayoutInflater mInflater;
    private ArrayList<Boolean> isCheckedList = new ArrayList<>();
    private  UsersList usersList;

    public ChatInfoAdapter(Context c, ArrayList<UserInformation> usersInformation, UsersList usersList){
        this.usersInformation = usersInformation;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.usersList = usersList;
    }

    @Override
    public int getCount() {
        return usersInformation.size();
    }

    @Override
    public Object getItem(int position) {
        if((usersInformation.size() - 1) >= position )
            return usersInformation.get(position);
        else{
            return null;
        }
    }

    public ArrayList<Boolean> getIsCheckedList() {
        return isCheckedList;
    }

    public ArrayList<UserInformation> getUsersInformation() {
        return usersInformation;
    }

    public UsersList getUsersList() {
        return usersList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.my_friends_list_detail, null);

        TextView usernameTextView = (TextView) v.findViewById(R.id.usernameTextView);
        TextView onlineTextField = (TextView) v.findViewById(R.id.onlieTextField);
        final CheckBox selectCheckBox = (CheckBox) v.findViewById(R.id.selectCheckBox);

        String username = usersInformation.get(position).getUserName();
        Boolean online = usersInformation.get(position).getOnline();

        if(online){
            onlineTextField.setText("Online");
        }
        else{
            onlineTextField.setText("Offline");
        }

        if((isCheckedList.size() - 1) >= position ){
            isCheckedList.set(position, selectCheckBox.isChecked());
        }
        else{
            isCheckedList.add(selectCheckBox.isChecked());
        }

        selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheckedList.set(position, selectCheckBox.isChecked());
            }
        });

        usernameTextView.setText(username);

        return v;
    }
}
