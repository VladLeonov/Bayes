import java.util.ArrayList;

class Message {

    boolean isSpam;
    ArrayList<Integer> title, body;

    public Message(boolean isSpam, ArrayList<Integer> title, ArrayList<Integer> body) {
        this.isSpam = isSpam;
        this.title = title;
        this.body = body;
    }
}
