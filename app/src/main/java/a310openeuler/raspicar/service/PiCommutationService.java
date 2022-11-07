package a310openeuler.raspicar.service;
import android.provider.Telephony;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

// 使用websocket连接，并发送控制指令
// 已测试可连接
public class PiCommutationService {
    private static final String TAG = "PiControlService";
    private static String SERVER_URI = "ws://192.168.137.144:617";
    private static WebSocketClient client = null;

    public static void connect(String url){
        SERVER_URI = url;
        if (client == null){
            client = new WebSocketClient(URI.create(SERVER_URI)) {
                @Override
                public void onOpen(ServerHandshake handShakeData) {
                    System.out.println("opened connection");
                }

                //todo 在这里处理接收到的消息
                @Override
                public void onMessage(String message) {
                    System.out.println("received: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Connection closed by " + (remote ? "remote peer" : "us"));
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("Error: " + ex);
                }
            };
        }
        client.connect();
        //等待连接成功
        while (!client.isOpen()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect(){
        if (client != null){
            client.close();
        }
    }

    //发送字符串信息
    public static void send(String message) {
        if (client != null && client.isOpen()) {
            client.send(message);
            //Log.d(TAG, "send: " + message);
        }
        //Log.d(TAG, "send: " + "client is null or closed");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        disconnect();
    }
}