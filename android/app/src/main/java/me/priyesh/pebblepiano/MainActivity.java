package me.priyesh.pebblepiano;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PebbleClickReceiver.PebbleClickListener {

    @Bind(R.id.piano_view)
    PianoView pianoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PebbleClickReceiver.register(this, getApplicationContext());
    }

    @Override
    public void onUpClicked() {
        pianoView.pressKey(0);
    }

    @Override
    public void onSelectClicked() {
        pianoView.pressKey(1);
    }

    @Override
    public void onDownClicked() {
        pianoView.pressKey(2);
    }

}
