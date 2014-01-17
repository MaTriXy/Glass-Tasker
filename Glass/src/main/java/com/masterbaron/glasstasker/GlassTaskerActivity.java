package com.masterbaron.glasstasker;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.speech.RecognizerIntent;
import android.util.Log;

import java.net.BindException;
import java.util.List;

/**
 * Created by Van Etten on 1/9/14.
 */
public class GlassTaskerActivity extends Activity {
    private static String TAG = GlassTaskerActivity.class.getName();

    private static int SPEECH_REQUEST = 1;

    private String taskToRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displaySpeechRecognizer();
    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent, SPEECH_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST) {
            if (resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                taskToRun = results.get(0);
                Log.i(TAG, "task to run:" + taskToRun);
                startTask();
            } else {
                this.finish();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    void startTask() {
        try {
            Intent in = new Intent();
            in.setClassName("com.masterbaron.intenttunnel", "com.masterbaron.intenttunnel.router.RouterService");
            if (!bindService(in, mConnection, Context.BIND_AUTO_CREATE)) {
                throw new BindException("failed to bind");
            }
        } catch (Exception e) {
            Log.e(TAG, "onStart error", e);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG, "onServiceConnected()");
            Intent action = new Intent(taskToRun);
            action.setClassName("com.masterbaron.glasstasker", "com.masterbaron.glasstasker.TaskerRelayReceiver");
            action.putExtra("task", taskToRun);

            Messenger messengerService = new Messenger(service);
            try {
                messengerService.send(Message.obtain(null, 1000, new Intent(action)));
            } catch (RemoteException e) {
                Log.e(TAG, "TaskerIntent", e);
            }
            unbindService(this);
            GlassTaskerActivity.this.finish();
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "onServiceDisconnected()");
        }
    };
}