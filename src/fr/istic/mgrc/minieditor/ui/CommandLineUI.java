package fr.istic.mgrc.minieditor.ui;

import fr.istic.mgrc.minieditor.commands.*;
import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.MiniEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interface en ligne de commande utilisant un MiniEditor
 */
public class CommandLineUI {

    private static final String INSERT_REGEX = "insert [a-zA-Z0-9 .,;:!?\\n&\"'\\t-_#$%<>()@/]+";
    private static final String SELECT_REGEX = "select \\d+ \\d+";
    private static final String MOVE_REGEX = "move (start|end|\\d+)";
    private static final String COPY_REGEX = "copy";
    private static final String CUT_REGEX = "cut";
    private static final String PASTE_REGEX = "paste";
    private static final String DELETE_REGEX = "delete";

    private MiniEditor editor;

    /**
     * Instancie l'éditeur et commence la boucle d'entrée de l'utilisateur
     */
    public CommandLineUI() {
        editor = new ConcreteMiniEditor();
        waitForInput();
    }

    /**
     * Boucle d'attente d'une entrée de l'utilsaiteur et du traitement de celle-ci
     * Affiche également l'état de l'editeur
     */
    public void waitForInput() {
        System.out.print("=>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException ioe) {
            System.err.println("Bad input");
        }
        if (input == null)
            waitForInput();
        else if (input.matches(INSERT_REGEX))
            insert(input);
        else if (input.matches(SELECT_REGEX))
            select(input);
        else if (input.matches(MOVE_REGEX))
            move(input);
        else if (input.matches(COPY_REGEX))
            copy();
        else if (input.matches(CUT_REGEX))
            cut();
        else if (input.matches(PASTE_REGEX))
            paste();
        else if (input.matches(DELETE_REGEX))
            delete();
        printEditor();
        waitForInput();
    }

    /**
     * Execution de DeleteCommand
     */
    private void delete() {
        Command deleteCommand = new DeleteCommand(editor);
        deleteCommand.execute();
    }

    /**
     * Execution d'InsertCommand
     *
     * @param command String conforme à #INSERT_REGEX
     */
    private void insert(String command) {
        String arg = command.split(" ", 2)[1];
        Command insertCommand = new InsertCommand(editor, arg);
        insertCommand.execute();
    }

    /**
     * Execution de SelectCommand
     *
     * @param command String conforme à #SELECT_REGEX
     */
    private void select(String command) {
        String args[] = command.split(" ");
        int from = Integer.valueOf(args[1]);
        int to = Integer.valueOf(args[2]);
        Command selectCommand = new SelectCommand(editor, from, to);
        selectCommand.execute();
    }

    /**
     * Execution de SelectCommand facilitant le déplacement dans le buffer (pas de selection possible, selection.start == selection.end)
     *
     * @param command String conforme à #MOVE_REGEX
     */
    private void move(String command) {
        String arg = command.split(" ", 2)[1];
        int pos;
        if (arg.equals("start")) {
            pos = 0;
        } else if (arg.equals("end")) {
            pos = editor.getBufferLength();
        } else {
            pos = Integer.valueOf(arg);
        }
        Command selectCommand = new SelectCommand(editor, pos, pos);
        selectCommand.execute();
    }

    /**
     * Execution de CopyCommand
     */
    private void copy() {
        Command copyCommand = new CopyCommand(editor);
        copyCommand.execute();
    }

    /**
     * Execution de CutCommand
     */
    private void cut() {
        Command cutCommand = new CutCommand(editor);
        cutCommand.execute();
    }

    /**
     * Execution de PasteCommand
     */
    private void paste() {
        Command pasteCommand = new PasteCommand(editor);
        pasteCommand.execute();
    }

    /**
     * Affiche l'état de l'editeur
     */
    private void printEditor() {
        System.out.print(editor);
    }

}
