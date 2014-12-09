package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class StopRecCommand extends EditorCommand {

    public StopRecCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.endRecording();
    }

}