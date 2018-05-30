package chatapp.ucr.com.ucrapp.Message;

import java.text.SimpleDateFormat;

public class Message {
    private String message;
    private String username;
    private long timestamp;
    private String date;

    public Message() {

        this.message = "";
        username = "";
        timestamp = 0;
    }

     public Message(String message, String username) {
        this.message = message;
        this.username = username;
        timestamp = System.currentTimeMillis();
         SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\n hh:mm:ss a");
         date = sdf.format(timestamp);
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
