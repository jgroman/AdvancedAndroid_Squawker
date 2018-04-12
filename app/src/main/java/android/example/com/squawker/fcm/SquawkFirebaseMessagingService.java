package android.example.com.squawker.fcm;

import android.content.ContentValues;
import android.example.com.squawker.NotificationUtils;
import android.example.com.squawker.R;
import android.example.com.squawker.provider.SquawkContract;
import android.example.com.squawker.provider.SquawkProvider;
import android.support.v4.app.NotificationCompat;

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

        if (message != null && message.length() > 0) {

            NotificationUtils.createFcmMessageNotification(getApplicationContext(), message);

            ContentValues values = new ContentValues();
            values.put(SquawkContract.COLUMN_AUTHOR, author);
            values.put(SquawkContract.COLUMN_AUTHOR_KEY, authorKey);
            values.put(SquawkContract.COLUMN_MESSAGE, message);
            values.put(SquawkContract.COLUMN_DATE, date);

            getApplicationContext().getContentResolver().insert(SquawkProvider.SquawkMessages.CONTENT_URI, values);

        }
    }
}
