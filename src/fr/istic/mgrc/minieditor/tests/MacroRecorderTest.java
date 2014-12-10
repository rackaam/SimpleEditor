package fr.istic.mgrc.minieditor.tests;

import fr.istic.mgrc.minieditor.commands.InsertCommand;
import fr.istic.mgrc.minieditor.commands.MacroCompatibleCommand;
import fr.istic.mgrc.minieditor.commands.mementos.InsertCommandMemento;
import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.MacroRecorder;
import fr.istic.mgrc.minieditor.editor.MiniEditor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MacroRecorderTest {

    @Test
    public void testStartRecording() {
        MacroRecorder macroRecorder = new MacroRecorder();
        macroRecorder.startRecording();
        assertEquals(true, macroRecorder.isRecording());
    }

    @Test
    public void testStopRecording() {
        MacroRecorder macroRecorder = new MacroRecorder();
        macroRecorder.startRecording();
        macroRecorder.stopRecording();
        assertEquals(false, macroRecorder.isRecording());
    }

    @Test
    public void testRecord() {
        MacroRecorder macroRecorder = new MacroRecorder();
        macroRecorder.startRecording();
        MiniEditor editor = new ConcreteMiniEditor();
        MacroCompatibleCommand command = new InsertCommand(editor, "123");
        macroRecorder.record(command);
        macroRecorder.stopRecording();
        assertEquals(1, macroRecorder.getCommands().size());
        assertEquals(InsertCommand.class, macroRecorder.getCommands().get(0));
        assertEquals("123", ((InsertCommandMemento) macroRecorder.getCommandsStates().get(0)).getText());
    }

}
