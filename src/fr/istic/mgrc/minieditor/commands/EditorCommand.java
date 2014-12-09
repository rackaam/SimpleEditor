package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public abstract class EditorCommand implements Command {

    protected MiniEditor editor;

    public EditorCommand() {
    }

    public EditorCommand(MiniEditor editor) {
        this.editor = editor;
    }

}
