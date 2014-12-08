package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class CopyCommand extends EditorCommand {

    public CopyCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.copy();
    }
}
