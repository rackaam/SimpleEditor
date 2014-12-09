package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;
import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

public abstract class MacroCompatibleCommand extends EditorCommand {

    public MacroCompatibleCommand() {
    }

    public MacroCompatibleCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        if (editor instanceof ConcreteMiniEditor) {
            if (((ConcreteMiniEditor) editor).getMacroRecorder().isRecording()) {
                ((ConcreteMiniEditor) editor).getMacroRecorder().record(this);
            }
        }
    }

    public abstract CommandMemento saveToMemento();

    public abstract void restoreFromMemento(CommandMemento memento);
}
