package br.com.italopatricio.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewCount, textViewResult;
    Button btnIncrement, btnDecrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CounterBroadcast broadcast = new CounterBroadcast(this);

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(new CounterBroadCastReceiver(new Increment()), new IntentFilter(CounterBroadcast.EVENT_ACTION_INCREMENT));
        localBroadcastManager.registerReceiver(new CounterBroadCastReceiver(new Decrement()), new IntentFilter(CounterBroadcast.EVENT_ACTION_DECREMENT));

        textViewCount = findViewById(R.id.textCount);
        textViewResult = findViewById(R.id.textResult);

        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int count = broadcast.increment();
               textViewCount.setText(String.format("%d",count));
            }
        });

        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = broadcast.decrement();
                textViewCount.setText(String.format("%d",count));
            }
        });
    }

    class Increment implements CounterBroadCastReceiver.CounterBroadCastDelegate {

        @Override
        public void ReceiveEventDelegate(int countCurrent) {
            textViewResult.setText(String.format("Incrementando valor para %d", countCurrent));
        }
    }

    class Decrement implements CounterBroadCastReceiver.CounterBroadCastDelegate {

        @Override
        public void ReceiveEventDelegate(int countCurrent) {
            textViewResult.setText(String.format("Decrementando valor para %d", countCurrent));
        }
    }

}