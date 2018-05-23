package chatapp.ucr.com.ucrapp.Message;



public class TextMessage extends Message {
    public TextMessage() {
        super();
    }

    public TextMessage(String message) {
        this.setMessage(message);
    }
    public TextMessage(String title, String message) {
        super(title, message);
    }

    @Override
    public void setMessage(String message) {
         super.setMessage(message);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

}

