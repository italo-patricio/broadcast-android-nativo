package br.com.italopatricio.broadcast;

import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class CounterBroadcast {
    int count = 0;

    LocalBroadcastManager broadcastManager;

    static final String EVENT_ACTION_INCREMENT = "increment";
    static final String EVENT_ACTION_DECREMENT = "decrement";
    static final String EXTRA_COUNT = "count";

    CounterBroadcast(Context context){
      this.broadcastManager  = LocalBroadcastManager.getInstance(context);
    }

    int increment(){
        count+=1;
        Intent intent =  new Intent();
        intent.setAction(EVENT_ACTION_INCREMENT);
        intent.putExtra(EXTRA_COUNT, count);

        broadcastManager.sendBroadcast(intent);
        return count;
    }

    int decrement(){
        Intent intent =  new Intent();

        intent.setAction(EVENT_ACTION_DECREMENT);
        intent.putExtra(EXTRA_COUNT, count);
        broadcastManager.sendBroadcast(intent);

        return (count-=1);
    }

}
