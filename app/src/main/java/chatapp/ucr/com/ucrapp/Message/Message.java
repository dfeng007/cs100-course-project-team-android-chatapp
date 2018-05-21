package chatapp.ucr.com.ucrapp.Message;

public abstract class Message {

    private String title;
    private String message;

    protected Message() {
        this.title = "";
        this.message = "";
    }

     protected Message(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.message;
    }

}
