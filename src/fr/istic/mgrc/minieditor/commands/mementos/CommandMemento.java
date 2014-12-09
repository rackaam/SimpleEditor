package fr.istic.mgrc.minieditor.commands.mementos;

import fr.istic.mgrc.minieditor.editor.MiniEditor;

/**
 * Memento utilisé pour sauvegarder l'etat d'une commande
 */
public class CommandMemento {

    private MiniEditor editor;

    public CommandMemento(MiniEditor editor) {
        this.editor = editor;
    }

    public MiniEditor getEditor() {
        return editor;
    }
}
