package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class StartRecCommand extends EditorCommand {

    public StartRecCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.startRecording();
    }

}
