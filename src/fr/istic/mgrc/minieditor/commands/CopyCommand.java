package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class CopyCommand extends MacroCompatibleCommand {

    public CopyCommand() {
    }

    public CopyCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        super.execute();
        editor.copy();
    }

    @Override
    public CommandMemento saveToMemento() {
        return new CommandMemento(editor);
    }

    @Override
    public void restoreFromMemento(CommandMemento memento) {
        editor = memento.getEditor();
    }
}
