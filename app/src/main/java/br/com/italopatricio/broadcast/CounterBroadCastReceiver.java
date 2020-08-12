package br.com.italopatricio.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CounterBroadCastReceiver extends BroadcastReceiver {

    CounterBroadCastDelegate delegate;

    CounterBroadCastReceiver(CounterBroadCastDelegate counterBroadCastDelegate){
        delegate = counterBroadCastDelegate;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int count = intent.getIntExtra(CounterBroadcast.EXTRA_COUNT, 0);
        delegate.ReceiveEventDelegate(count);
    }

    interface CounterBroadCastDelegate {
        void ReceiveEventDelegate(int countCurrent);
    }
}
