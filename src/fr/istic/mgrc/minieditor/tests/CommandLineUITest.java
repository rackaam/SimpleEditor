package fr.istic.mgrc.minieditor.tests;


import fr.istic.mgrc.minieditor.commands.InsertCommand;
import fr.istic.mgrc.minieditor.commands.mementos.InsertCommandMemento;
import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.Selection;
import fr.istic.mgrc.minieditor.ui.CommandLineUI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandLineUITest {

    @Test
    public void testInsert() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        assertEquals("azerty", commandLineUI.getEditor().getBuffer());
    }

    @Test
    public void testSelect() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("select 1 4");
        Selection expectedSelection = new Selection();
        expectedSelection.set(1, 4);
        assertEquals(expectedSelection, commandLineUI.getEditor().getSelection());
    }

    @Test
    public void testMoveStart() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("move start");
        Selection expectedSelection = new Selection();
        expectedSelection.set(0, 0);
        assertEquals(expectedSelection, commandLineUI.getEditor().getSelection());
    }

    @Test
    public void testMoveEnd() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("move start");
        commandLineUI.newInput("move end");
        Selection expectedSelection = new Selection();
        expectedSelection.set(commandLineUI.getEditor().getBufferLength(), commandLineUI.getEditor().getBufferLength());
        assertEquals(expectedSelection, commandLineUI.getEditor().getSelection());
    }

    @Test
    public void testMoveIdx() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("move 3");
        Selection expectedSelection = new Selection();
        expectedSelection.set(3, 3);
        assertEquals(expectedSelection, commandLineUI.getEditor().getSelection());
    }

    @Test
    public void testCopy() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("select 1 4");
        commandLineUI.newInput("copy");
        assertEquals("zer", commandLineUI.getEditor().getClipboard());
    }

    @Test
    public void testCut() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("select 1 4");
        commandLineUI.newInput("cut");
        assertEquals("zer", commandLineUI.getEditor().getClipboard());
        assertEquals("aty", commandLineUI.getEditor().getBuffer());
    }

    @Test
    public void testPaste() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("select 1 4");
        commandLineUI.newInput("copy");
        commandLineUI.newInput("move start");
        commandLineUI.newInput("paste");
        assertEquals("zerazerty", commandLineUI.getEditor().getBuffer());
    }

    @Test
    public void testDelete() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("insert azerty");
        commandLineUI.newInput("select 1 4");
        commandLineUI.newInput("delete");
        assertEquals("aty", commandLineUI.getEditor().getBuffer());
    }

    @Test
    public void testRecording() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("start rec");
        commandLineUI.newInput("insert test");
        commandLineUI.newInput("stop rec");
        assertEquals(1, ((ConcreteMiniEditor) commandLineUI.getEditor()).getMacroRecorder().getCommands().size());
        assertEquals(InsertCommand.class, ((ConcreteMiniEditor) commandLineUI.getEditor()).getMacroRecorder().getCommands().get(0));
        assertEquals("test", ((InsertCommandMemento) ((ConcreteMiniEditor) commandLineUI.getEditor()).getMacroRecorder().getCommandsStates().get(0)).getText());
    }

    @Test
    public void testPlay() {
        CommandLineUI commandLineUI = new CommandLineUI();
        commandLineUI.newInput("start rec");
        commandLineUI.newInput("insert test");
        commandLineUI.newInput("stop rec");
        commandLineUI.newInput("play");
        commandLineUI.newInput("play");
        assertEquals("testtesttest", commandLineUI.getEditor().getBuffer());
    }

}
