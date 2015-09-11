package me.priyesh.pebblepiano;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {

    private static SoundManager instance;
    private Map<Note, Integer> notesToResourceIds = new HashMap<>(3);

    private SoundManager() {
        for (Note note : Note.values()) notesToResourceIds.put(note, note.resourceId);
    }

    public static SoundManager getInstance() {
        if (instance == null) instance = new SoundManager();
        return instance;
    }

    public void playNote(Context context, Note note) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, notesToResourceIds.get(note));
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

}
