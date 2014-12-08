package fr.istic.mgrc.minieditor.editor;

import fr.istic.mgrc.minieditor.Observable;

public interface MiniEditor extends Observable {

    public String getBuffer();

    public String getSelectionString();

    public Selection getSelection();

    public String getClipboard();

    public void insert(String substring);

    public void select(int start, int end);

    public void copy();

    public void cut();

    public void paste();

    public void delete();

    public void startRecording();

    public void endRecording();

    public void playRecording();

    public void undo();

    public void redo();
}

