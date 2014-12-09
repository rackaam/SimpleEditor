package fr.istic.mgrc.minieditor.commands.mementos;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class SelectCommandMemento extends CommandMemento {

    private final int offsetStart;
    private final int offsetEnd;

    /**
     * @param editor
     * @param offsetStart offset entre l'index de début de la selection et la position actuelle du curseur
     * @param offsetEnd   offset entre l'index de fin de la selection et la position actuelle du curseur
     */
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
