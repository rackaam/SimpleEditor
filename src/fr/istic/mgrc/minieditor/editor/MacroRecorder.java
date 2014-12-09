package fr.istic.mgrc.minieditor.editor;

import fr.istic.mgrc.minieditor.commands.MacroCompatibleCommand;
import fr.istic.mgrc.minieditor.commands.mementos.CommandMemento;

import java.util.ArrayList;
import java.util.List;

public class MacroRecorder {

    private boolean isRecording;
    private List<java.lang.Class<? extends MacroCompatibleCommand>> commands = new ArrayList<java.lang.Class<? extends MacroCompatibleCommand>>();
    private List<CommandMemento> commandsStates = new ArrayList<CommandMemento>();


    public boolean isRecording() {
        return isRecording;
    }

    public void startRecording() {
        commands.clear();
        commandsStates.clear();
        isRecording = true;
    }

    public void stopRecording() {
        isRecording = false;
    }

    public void record(MacroCompatibleCommand command) {
        commandsStates.add(command.saveToMemento());
        commands.add(command.getClass());
    }

    public void play() {
        if (isRecording)
            return;
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
}
