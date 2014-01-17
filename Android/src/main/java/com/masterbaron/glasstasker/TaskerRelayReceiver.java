package com.masterbaron.glasstasker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import tasker.TaskerIntent;

/**
 * Created by Van Etten on 1/15/14.
 */
public class TaskerRelayReceiver extends BroadcastReceiver {
    private static final String TAG = TaskerRelayReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("task");

        Log.d(TAG, "launching task: " + name);

        if (TaskerIntent.testStatus(context).equals(TaskerIntent.Status.OK)) {
            TaskerIntent relay = new TaskerIntent(name);
            context.sendBroadcast(relay);

            relay = new TaskerIntent(capFirstLetter(name));
            context.sendBroadcast(relay);
        }
    }

    private String capFirstLetter(String toBeCapped) {
        String[] tokens = toBeCapped.split("\\s");
        StringBuffer capped = new StringBuffer();

        for (int i = 0; i < tokens.length; i++) {
            char capLetter = Character.toUpperCase(tokens[i].charAt(0));
            capped.append(" ").append(capLetter).append(tokens[i].substring(1, tokens[i].length()));
        }
        return capped.substring(1);
    }
}
