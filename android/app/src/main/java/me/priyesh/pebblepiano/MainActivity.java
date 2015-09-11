package me.priyesh.pebblepiano;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.getpebble.android.kit.PebbleKit;

public class MainActivity extends AppCompatActivity implements PebbleClickReceiver.UpDownListener {

    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        label = (TextView) findViewById(R.id.label);

        PebbleClickReceiver.register(this, getApplicationContext());
        updateLabel(PebbleKit.isWatchConnected(this) ? "Connected" : "Not connected");
    }

    @Override
    public void onUpClicked() {
        updateLabel("Up");
    }

    @Override
    public void onSelectClicked() {
        updateLabel("Select");
    }

    @Override
    public void onDownClicked() {
        updateLabel("Down");
    }

    private void updateLabel(String s) {
        label.setText(s);
    }
}
