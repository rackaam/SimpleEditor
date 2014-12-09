package fr.istic.mgrc.minieditor.commands.mementos;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class InsertCommandMemento extends CommandMemento {

    private final String text;

    public InsertCommandMemento(MiniEditor editor, String text) {
        super(editor);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
