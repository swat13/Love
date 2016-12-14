package ir.masterArt.yourLove.Firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * Created by Alif on 10/5/2016.
 */


/**
 * Created by Belal on 5/27/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
  private static final String TAG = "MyFirebaseMsgService";



  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {
    //Displaying data in log
    //It is optional
    Log.e(TAG, "From: " + remoteMessage.getFrom());
    Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

  }

}