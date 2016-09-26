package Bean;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Tnecesoc on 2016/9/7.
 */
public class MessageBean {

    private String topic;
    private String publishTime;

    private String author;
    private String content;

    public MessageBean() {}

    public MessageBean(String topic, String publishTime, String author, String content) {
        this.topic = topic;
        this.publishTime = publishTime;
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
