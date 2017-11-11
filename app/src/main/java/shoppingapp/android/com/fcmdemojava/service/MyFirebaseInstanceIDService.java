package shoppingapp.android.com.fcmdemojava.service;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import shoppingapp.android.com.fcmdemojava.app.config;

import static android.content.ContentValues.TAG;

/**
 * Created by Vikrant on 11-11-2017.
 */

@SuppressLint("Registered")
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        SharedPreferences preferences = getSharedPreferences(config.SHARED_PREF, MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
        editor.putString("refreshedToken", refreshedToken);
        editor.apply();
    }
}
