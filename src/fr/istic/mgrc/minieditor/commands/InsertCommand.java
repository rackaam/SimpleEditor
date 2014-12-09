package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;
import fr.istic.mgrc.minieditor.commands.mementos.InsertCommandMemento;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class InsertCommand extends MacroCompatibleCommand {

    private String text;

    public InsertCommand() {
    }

    public InsertCommand(MiniEditor editor, String s) {
        super(editor);
        this.text = s;
    }

    @Override
    public void execute() {
        super.execute();
        editor.insert(text);
    }

    @Override
    public CommandMemento saveToMemento() {
        return new InsertCommandMemento(editor, text);
    }

    @Override
    public void restoreFromMemento(CommandMemento memento) {
        if (memento instanceof InsertCommandMemento) {
            this.editor = memento.getEditor();
            this.text = ((InsertCommandMemento) memento).getText();
        }
    }
}
