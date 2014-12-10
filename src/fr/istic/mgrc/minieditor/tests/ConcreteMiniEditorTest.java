package fr.istic.mgrc.minieditor.tests;

import fr.istic.mgrc.minieditor.commands.InsertCommand;
import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.MiniEditor;
import fr.istic.mgrc.minieditor.editor.Selection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcreteMiniEditorTest {

    @Test
    public void testSimpleInsert() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        assertEquals("azerty", editor.getBuffer());
    }

    @Test
    public void testSelect() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 4);
        Selection expectedSelection = new Selection();
        expectedSelection.set(2, 4);
        assertEquals(expectedSelection, editor.getSelection());
    }

    @Test
    public void testSelectExceptionInf() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(-2, 3);
        Selection expectedSelection = new Selection();
        expectedSelection.set(0, 3);
        assertEquals(expectedSelection, editor.getSelection());
    }

    @Test
    public void testSelectExceptionSup() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 8);
        Selection expectedSelection = new Selection();
        expectedSelection.set(2, 6);
        assertEquals(expectedSelection, editor.getSelection());
    }

    @Test
    public void testCopy() throws Exception {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 4);
        editor.copy();
        assertEquals("er", editor.getClipboard());
    }

    @Test
    public void testCopyPaste() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 4);
        editor.copy();
        editor.select(0, 0);
        editor.paste();
        assertEquals("erazerty", editor.getBuffer());
    }

    @Test
    public void testPasteReplaceSelection() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 4);
        editor.copy();
        editor.select(0, 3);
        editor.paste();
        assertEquals("errty", editor.getBuffer());
    }

    @Test
    public void testCut() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 4);
        editor.cut();
        editor.select(0, 0);
        editor.paste();
        assertEquals("erazty", editor.getBuffer());
    }

    @Test
    public void testDelete() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 2);
        editor.delete();
        assertEquals("aerty", editor.getBuffer());
    }

    @Test
    public void testDeleteSelection() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("azerty");
        editor.select(2, 5);
        editor.delete();
        assertEquals("azy", editor.getBuffer());
    }

    @Test
    public void testPlay() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.startRecording();
        InsertCommand command = new InsertCommand(editor, "test");
        command.execute();
        editor.endRecording();
        editor.playRecording();
        assertEquals("testtest", editor.getBuffer());
    }

    @Test
    public void testUndo() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("test");
        editor.undo();
        assertEquals("", editor.getBuffer());
    }

    @Test
    public void testRedo() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("test");
        editor.undo();
        editor.redo();
        assertEquals("test", editor.getBuffer());
    }

    @Test
    public void insertCleansRedoStack() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("test");
        editor.undo();
        editor.insert("1");
        editor.redo();
        assertEquals("1", editor.getBuffer());
    }

    @Test
    public void undoMacro() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.startRecording();
        new InsertCommand(editor, "test1").execute();
        new InsertCommand(editor, "test2").execute();
        editor.endRecording();
        editor.playRecording();
        editor.undo();
        assertEquals("test1test2", editor.getBuffer());
    }

    @Test
    public void redoMacro() {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.startRecording();
        new InsertCommand(editor, "test1").execute();
        new InsertCommand(editor, "test2").execute();
        editor.endRecording();
        editor.playRecording();
        assertEquals("test1test2test1test2", editor.getBuffer());
    }

}