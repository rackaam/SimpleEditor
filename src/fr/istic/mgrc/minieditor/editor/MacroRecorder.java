package fr.istic.mgrc.minieditor.editor;

import fr.istic.mgrc.minieditor.commands.MacroCompatibleCommand;
import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;

import java.util.ArrayList;
import java.util.List;

/**
 * Realise l'enregistrement et le lancement de macro
 * Joue le rôle de Caretaker dans le pattern Memento
 */
public class MacroRecorder {

    private boolean isRecording;
    // Sauvegarde du type de class à la place d'une instance de la classe
    private List<java.lang.Class<? extends MacroCompatibleCommand>> commands = new ArrayList<java.lang.Class<? extends MacroCompatibleCommand>>();
    private List<CommandMemento> commandsStates = new ArrayList<CommandMemento>();

    // Utilisaton de temporaires pour permettre l'utilisation de macro pendant l'enregistrement d'une nouvelle macro
    private List<java.lang.Class<? extends MacroCompatibleCommand>> tempCommands = new ArrayList<java.lang.Class<? extends MacroCompatibleCommand>>();
    private List<CommandMemento> tempCommandsStates = new ArrayList<CommandMemento>();

    public boolean isRecording() {
        return isRecording;
    }

    public void startRecording() {
        isRecording = true;
    }

    /**
     * Stop l'enregistrement. La macro précédemment enregistrée devient indisponible
     */
    public void stopRecording() {
        if (isRecording) {
            isRecording = false;
            commands = tempCommands;
            commandsStates = tempCommandsStates;
            tempCommands = new ArrayList<java.lang.Class<? extends MacroCompatibleCommand>>();
            tempCommandsStates = new ArrayList<CommandMemento>();
        }
    }

    /**
     * Enregistre une nouvelle commande
     * @param command
     */
    public void record(MacroCompatibleCommand command) {
        if (isRecording) {
            tempCommandsStates.add(command.saveToMemento());
            tempCommands.add(command.getClass());
        }
    }

    /**
     * Rejoue les commandes enregistrées
     */
    public void play() {
        for (int i = 0; i < commands.size(); i++) {
            try {
                MacroCompatibleCommand command = commands.get(i).newInstance();
                command.restoreFromMemento(commandsStates.get(i));
                command.execute();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Class<? extends MacroCompatibleCommand>> getCommands() {
        return commands;
    }

    public List<CommandMemento> getCommandsStates() {
        return commandsStates;
    }
}
