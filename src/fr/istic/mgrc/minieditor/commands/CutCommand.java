package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class CutCommand extends EditorCommand {

    public CutCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.cut();
    }
}
