package me.priyesh.pebblepiano;

public enum Note {

    A(R.raw.note_a), B(R.raw.note_b), C(R.raw.notes_c);

    final int resourceId;

    Note(int resourceId) { this.resourceId = resourceId; }

}