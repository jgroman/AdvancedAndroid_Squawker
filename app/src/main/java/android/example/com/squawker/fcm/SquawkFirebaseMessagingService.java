package android.example.com.squawker.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class SquawkFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        String author = data.get("author");
        String authorKey = data.get("authorKey");
        String message = data.get("message");
        String date = data.get("date");
        String test = data.get("test");

    }
}
