import Bean.MessageBean;
import Util.MessageRepository;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by Tnecesoc on 2016/9/7.
 */
public class BrokerConnector {

    private static final Gson gson = new Gson();

    private static final String BROKER_URL = "tcp://10.52.26.33:1883";

//    private static final String BROKER_URL = "tcp://172.22.26.0:1883";

    private static final String CLIENT_ID = "_$root";

    private static void onMessageReceive(String s, MqttMessage mqttMessage) {

        String json = null;

        try {
            json = new String(mqttMessage.getPayload(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        if (json != null) {
            MessageBean message = gson.fromJson(json, MessageBean.class);

            if (message.getTopic() == null) {
                message.setTopic(s);
            }

            MessageRepository.insertMessage(message);
        }
    }

    private static MqttClient client;
    static {
        try {
            client = new MqttClient(BROKER_URL, CLIENT_ID);

            client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean b, String s) {
                    System.out.println("connected to " + s);
                }

                @Override
                public void connectionLost(Throwable throwable) {
                    throwable.printStackTrace();
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    onMessageReceive(s, mqttMessage);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("delivery completed");
                }
            });

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(false);

            client.connect(options);

            client.subscribe("#");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void activate() {}

    public static void main(String[] args) {

        activate();

    }


}



