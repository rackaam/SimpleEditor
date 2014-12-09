package fr.istic.mgrc.minieditor.commands.mementos;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class SelectCommandMemento extends CommandMemento {

    private final int offsetStart;
    private final int offsetEnd;

    public SelectCommandMemento(MiniEditor editor, int offsetStart, int offsetEnd) {
        super(editor);
        this.offsetStart = offsetStart;
        this.offsetEnd = offsetEnd;
    }

    public int getOffsetStart() {
        return offsetStart;
    }

    public int getOffsetEnd() {
        return offsetEnd;
    }
}
