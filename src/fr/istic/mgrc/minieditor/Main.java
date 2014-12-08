package fr.istic.mgrc.minieditor;

import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

public class Main {

    public static void main(String[] args) {
        MiniEditor editor = new ConcreteMiniEditor();
        editor.insert("Ceci est un test");
        editor.select(2, 5);
        editor.cut();
        editor.select(0, 1);
        editor.paste();
        System.out.println(editor);
    }
}
