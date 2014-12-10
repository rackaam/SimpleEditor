package fr.istic.mgrc.minieditor.editor;

import java.util.Stack;

public class EditorCaretaker {

    private ConcreteMiniEditor editor;
    private Stack<EditorMemento> states = new Stack<EditorMemento>();
    private Stack<EditorMemento> undoStates = new Stack<EditorMemento>();

    public EditorCaretaker(ConcreteMiniEditor editor) {
        this.editor = editor;
    }

    public void save() {
        undoStates.clear();
        EditorMemento save = editor.saveToMemento();
        states.push(save);
        System.out.println(save.getBuffer().toString() + " : " + save.getSelection().getStart() + "/" + save.getSelection().getEnd());
    }

    public void undo() {
        if (states.size() > 1) {
            undoStates.push(states.pop());
            editor.restoreFromMemento(states.peek());
        }
    }

    public void redo() {
        if (!undoStates.empty()) {
            EditorMemento memento = undoStates.pop();
            editor.restoreFromMemento(memento);
            states.push(new EditorMemento(new StringBuffer(memento.getBuffer()), new Selection(memento.getSelection())));
        }
    }
}
