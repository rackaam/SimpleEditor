package fr.istic.mgrc.minieditor.editor;

public class EditorMemento {

    private final StringBuffer buffer;
    private final Selection selection;

    public EditorMemento(StringBuffer buffer, Selection selection) {
        this.buffer = buffer;
        this.selection = selection;
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    public Selection getSelection() {
        return selection;
    }

}
