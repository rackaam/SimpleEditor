package fr.istic.mgrc.minieditor.commands.mementos;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class CommandMemento {

    private MiniEditor editor;

    public CommandMemento(MiniEditor editor) {
        this.editor = editor;
    }

    public MiniEditor getEditor() {
        return editor;
    }
}
