package android.example.com.squawker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationUtils {

    private static final int NOTIFICTAION_ID_FCM_MESSAGE = 1234;

    private static final String NOTIFICATION_CHANNEL_ID_FCM_MESSAGE = "fcm_notification_channel";

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public static void createFcmMessageNotification(Context context, String message) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID_FCM_MESSAGE,
                    context.getString(R.string.fcm_message_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        // Create notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID_FCM_MESSAGE)
                .setSmallIcon(R.drawable.ic_duck)
                .setContentTitle(context.getString(R.string.fcm_message_notification_title))
                .setContentText(message.substring(0,29));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }

        notificationManager.notify(NOTIFICTAION_ID_FCM_MESSAGE, notificationBuilder.build());

    }
}
