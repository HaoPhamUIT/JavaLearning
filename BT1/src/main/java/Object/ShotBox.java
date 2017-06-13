package object;

/**
 * Created by hao-pham on 6/12/17.
 */
public class ShotBox {

    private long id;
    private String userName;
    private String content;
    private String dateTimeText;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTimeText() {
        return dateTimeText;
    }

    public void setDateTimeText(String dateTimeText) {
        this.dateTimeText = dateTimeText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
