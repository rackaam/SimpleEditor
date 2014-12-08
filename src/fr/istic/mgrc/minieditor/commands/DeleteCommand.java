package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class DeleteCommand extends EditorCommand {

    public DeleteCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.delete();
    }
}
