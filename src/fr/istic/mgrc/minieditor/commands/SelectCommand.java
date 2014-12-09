package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;
import fr.istic.mgrc.minieditor.commands.mementos.SelectCommandMemento;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class SelectCommand extends MacroCompatibleCommand {

    private int start, end;

    public SelectCommand() {
    }

    /**
     * @param editor
     * @param start  Index de début de la selection (inclu)
     * @param end    Index de fin de la selection (exclu)
     */
    public SelectCommand(MiniEditor editor, int start, int end) {
        super(editor);
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        super.execute();
        editor.select(start, end);
    }

    @Override
    public CommandMemento saveToMemento() {
        return new SelectCommandMemento(editor, start - editor.getSelection().getStart(), end - editor.getSelection().getStart());
    }

    @Override
    public void restoreFromMemento(CommandMemento memento) {
        if (memento instanceof SelectCommandMemento) {
            editor = memento.getEditor();
            start = editor.getSelection().getStart() + ((SelectCommandMemento) memento).getOffsetStart();
            end = editor.getSelection().getStart() + ((SelectCommandMemento) memento).getOffsetEnd();
        }
    }
}
