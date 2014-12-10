package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class UndoCommand extends EditorCommand {

    public UndoCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.undo();
    }

}