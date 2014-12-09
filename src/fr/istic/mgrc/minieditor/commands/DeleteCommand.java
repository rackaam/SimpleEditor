package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class DeleteCommand extends MacroCompatibleCommand {

    public DeleteCommand() {
    }

    public DeleteCommand(MiniEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        super.execute();
        editor.delete();
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
