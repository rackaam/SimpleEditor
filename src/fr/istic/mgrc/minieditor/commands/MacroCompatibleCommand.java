package fr.istic.mgrc.minieditor.commands;

import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;
import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

/**
 * Commande enregistrable dans une macro.
 * Elle s'enregistre elle même automatiquement si l'enregistrement de macro est activé.
 * Elle joue le rôle d'Originator dans le pattern Memento
 */
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

    /**
     * Sauvegarde l'etat de la commande dans un memento
     *
     * @return
     */
    public abstract CommandMemento saveToMemento();

    public abstract void restoreFromMemento(CommandMemento memento);
}
