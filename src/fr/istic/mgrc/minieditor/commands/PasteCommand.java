package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class PasteCommand extends EditorCommand {

    public PasteCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.paste();
    }
}
