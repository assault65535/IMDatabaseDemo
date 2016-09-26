package Util;

import Bean.MessageBean;
import Util.ClauseFormatter;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Tnecesoc on 2016/9/7.
 */
public class MessageRepository extends DataSource {

    public static final Gson gson = new Gson();

    public static void insertMessage(MessageBean message) {

        String valuesClause = ClauseFormatter.create("('%s',now(),'%s','%s')", message.getTopic(), message.getAuthor(), message.getContent());

        DataSource.doSQLAction(() -> DataSource.statement.execute("INSERT INTO message_table (topic, publish_time, author, content) VALUES " + valuesClause));

    }

    public static void insertMessage(String newMessageJSON) {

        MessageBean message = gson.fromJson(newMessageJSON, MessageBean.class);

        insertMessage(message);

    }

    public static String findMessageJSONListLaterThan(String topic, Date date) {
        return gson.toJson(findMessagesLaterThan(topic, date));
    }

    public static String findMessageJSON(String topic) {
        return gson.toJson(findMessages(topic));
    }

    public static List<MessageBean> findMessages(String topic) {
        List<MessageBean> ans = new ArrayList<>();

        DataSource.doSQLAction(() -> {
            ResultSet resultSet = DataSource.statement.executeQuery("SELECT * FROM message_table WHERE topic = '" + topic + "' ORDER BY publish_time");

            //日期格式： 年-月-日 时:分:秒
            while (resultSet.next()) {
                ans.add(new MessageBean(
                        resultSet.getString("topic"),
                        resultSet.getString("publish_time").substring(0, 19),
                        resultSet.getString("author"),
                        resultSet.getString("content")
                ));
            }

        });

        return ans;
    }

    public static List<MessageBean> findMessagesLaterThan(String topic, Date date) {

        List<MessageBean> ans = new ArrayList<>();


        DataSource.doSQLAction(() -> {

            String _date = new SimpleDateFormat("Y-M-d H:m:s").format(date);

            String whereClause = ClauseFormatter.create("topic='%s' AND publish_time>'%s'", topic, _date);

            ResultSet resultSet = DataSource.statement.executeQuery("SELECT * FROM message_table WHERE " + whereClause + " ORDER BY publish_time");
            //日期格式： 年-月-日 时:分:秒
            while (resultSet.next()) {
                ans.add(new MessageBean(
                        resultSet.getString("topic"),
                        resultSet.getString("publish_time").substring(0, 19),
                        resultSet.getString("author"),
                        resultSet.getString("content")
                ));
            }

        });

        return ans;

    }

}