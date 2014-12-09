package fr.istic.mgrc.minieditor.editor;

import fr.istic.mgrc.minieditor.Observer;

import java.util.HashSet;
import java.util.Set;

/**
 * Implémentation de MiniEditor
 * Documentation des méthodes surchargées dans MiniEditor.java
 */
public class ConcreteMiniEditor implements MiniEditor {

    private Set<Observer> observers = new HashSet<Observer>();
    private StringBuffer buffer = new StringBuffer();
    private String clipboard = null;
    private Selection selection = new Selection();

    @Override
    public String getBuffer() {
        return buffer.toString();
    }

    @Override
    public int getBufferLength() {
        return buffer.length();
    }

    @Override
    public String getSelectionString() {
        return buffer.substring(selection.getStart(), selection.getEnd());
    }

    @Override
    public Selection getSelection() {
        return selection;
    }

    @Override
    public String getClipboard() {
        return clipboard;
    }

    @Override
    public void insert(String substring) {
        if (selection.getLength() > 0) {
            delete();
        }
        buffer.insert(selection.getStart(), substring);
        int newSelectionStart = selection.getStart() + substring.length();
        selection.set(newSelectionStart, newSelectionStart);
        notifyObservers();
    }

    @Override
    public void select(int start, int end) {
        if (start < 0)
            start = 0;
        if (start > buffer.length())
            start = buffer.length();
        if (end < 0)
            end = 0;
        if (end > buffer.length())
            end = buffer.length();
        if (start > end)
            throw new IllegalArgumentException("The End value must be greater or equal to the Start value");
        selection.set(start, end);
        notifyObservers();
    }

    @Override
    public void copy() {
        if (selection.getLength() > 0) {
            clipboard = getSelectionString();
        }
    }

    @Override
    public void cut() {
        if (selection.getLength() > 0) {
            copy();
            delete(selection.getStart(), selection.getEnd());
        }
    }

    @Override
    public void paste() {
        if (clipboard == null)
            return;
        insert(getClipboard());
    }

    @Override
    public void delete() {
        if (selection.getLength() == 0) {
            if (selection.getStart() > 0 && buffer.length() > 0) {
                delete(selection.getStart() - 1, selection.getStart());
            }
        } else {
            delete(selection.getStart(), selection.getEnd());
        }
    }

    @Override
    public void startRecording() {
        throw new UnsupportedOperationException("v2 feature");
    }

    @Override
    public void endRecording() {
        throw new UnsupportedOperationException("v2 feature");
    }

    @Override
    public void playRecording() {
        throw new UnsupportedOperationException("v2 feature");
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("v3 feature");
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException("v3 feature");
    }

    /**
     * Removes the character between start and end
     *
     * @param start Start (inclusive)
     * @param end   End (exclusive)
     */
    private void delete(int start, int end) {
        buffer.delete(start, end);
        selection.set(start, start);
        notifyObservers();
    }


    @Override
    public String toString() {
        return "###Buffer:\n" + buffer.toString() + "\n"
                + "###Clipboard:\n" + clipboard + "\n"
                + "###Selection: " + selection.getStart() + "-" + selection.getEnd() + " length: " + selection.getLength() + "\n";
    }

    /**
     * Notifie les observeurs quand le contenu du buffer change (après un #insert() ou un #delete())
     */
    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.update(this);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }
}
