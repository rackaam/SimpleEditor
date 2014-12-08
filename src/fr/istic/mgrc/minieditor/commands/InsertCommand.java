package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class InsertCommand extends EditorCommand {

    private String inserted;

    public InsertCommand(MiniEditor editor, String s) {
        super(editor);
        this.inserted = s;
    }

    @Override
    public void execute() {
        editor.insert(inserted);
    }
}
