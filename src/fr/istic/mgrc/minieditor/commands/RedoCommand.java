package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class RedoCommand extends EditorCommand {

    public RedoCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.redo();
    }
}