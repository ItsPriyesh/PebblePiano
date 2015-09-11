package me.priyesh.pebblepiano;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PianoView extends LinearLayout {

    @Bind({R.id.key0, R.id.key1, R.id.key2})
    List<Button> keys;

    private final SoundManager soundManager;

    public PianoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.piano_view, this);
        ButterKnife.bind(this);
        soundManager = SoundManager.getInstance();

        for (Button key : keys) {
            key.setOnClickListener(v -> soundManager.playNote(getContext(), Note.values()[keys.indexOf(key)]));
        }
    }

    public void pressKey(final int key) {
        final int[] coordinates = new int[2];
        keys.get(key).getLocationOnScreen(coordinates);

        final MotionEvent press = generateClickEvent(true, coordinates[0], coordinates[1]);
        final MotionEvent release = generateClickEvent(false, coordinates[0], coordinates[1]);

        keys.get(key).dispatchTouchEvent(press);
        keys.get(key).dispatchTouchEvent(release);

        press.recycle();
        release.recycle();
    }

    private MotionEvent generateClickEvent(final boolean down, final float x, final float y) {
        return MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                down ? MotionEvent.ACTION_DOWN : MotionEvent.ACTION_UP, x, y, 0);
    }

}
