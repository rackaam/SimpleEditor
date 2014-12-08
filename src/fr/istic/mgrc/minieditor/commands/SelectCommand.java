package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class SelectCommand extends EditorCommand {

    private int start, end;

    public SelectCommand(MiniEditor editor, int start, int end) {
        super(editor);
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        editor.select(start, end);
    }
}
