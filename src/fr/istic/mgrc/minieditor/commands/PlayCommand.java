package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class PlayCommand extends EditorCommand {

    public PlayCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        editor.playRecording();
    }

}
