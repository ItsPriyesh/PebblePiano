package me.priyesh.pebblepiano;

import android.content.Context;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

public class PebbleClickReceiver {

    public interface UpDownListener {
        void onUpClicked();
        void onSelectClicked();
        void onDownClicked();
    }

    private static final int KEY_BUTTON_UP = 0;
    private static final int KEY_BUTTON_SELECT = 1;
    private static final int KEY_BUTTON_DOWN = 2;

    private static final UUID APP_UUID = UUID.fromString("245b5782-02c0-4f80-adde-1f897add4407");

    public static void register(final UpDownListener listener, Context context) {
        PebbleKit.PebbleDataReceiver dataReceiver = new PebbleKit.PebbleDataReceiver(APP_UUID) {
            @Override
            public void receiveData(Context context, int i, PebbleDictionary dict) {
                PebbleKit.sendAckToPebble(context, i);
                if (dict.getInteger(KEY_BUTTON_UP) != null) listener.onUpClicked();
                if (dict.getInteger(KEY_BUTTON_DOWN) != null) listener.onDownClicked();
                if (dict.getInteger(KEY_BUTTON_SELECT) != null) listener.onSelectClicked();
            }
        };
        PebbleKit.registerReceivedDataHandler(context, dataReceiver);
    }
}
