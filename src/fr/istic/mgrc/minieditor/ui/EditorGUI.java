package fr.istic.mgrc.minieditor.ui;


import fr.istic.mgrc.minieditor.Observer;
import fr.istic.mgrc.minieditor.commands.*;
import fr.istic.mgrc.minieditor.editor.ConcreteMiniEditor;
import fr.istic.mgrc.minieditor.editor.MiniEditor;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EditorGUI extends Application implements Observer {

    private MiniEditor editor;
    private TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        editor = new ConcreteMiniEditor();
        editor.registerObserver(this);

        primaryStage.setTitle("Editor GUI");

        textArea = new TextArea();
        final EventDispatcher defaultDispatcher = textArea.getEventDispatcher();
        textArea.setEventDispatcher(new EventDispatcher() {
            @Override
            public Event dispatchEvent(Event event, EventDispatchChain eventDispatchChain) {
//                System.out.println(event);
                if (event instanceof KeyEvent) {
                    KeyEvent keyEvent = (KeyEvent) event;

                    // Shortcuts
                    if (keyEvent.isControlDown()) {
                        if (keyEvent.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                            switch (keyEvent.getCode()) {
                                case X:
                                    Command cutCommand = new CutCommand(editor);
                                    cutCommand.execute();
                                    break;
                                case C:
                                    Command copyCommand = new CopyCommand(editor);
                                    copyCommand.execute();
                                    break;
                                case V:
                                    Command pasteCommand = new PasteCommand(editor);
                                    pasteCommand.execute();
                                    break;
                                case R://start record macro
                                    Command startRecord = new StartRecCommand(editor);
                                    startRecord.execute();
                                    break;
                                case S://stop record
                                    Command stopRecord = new StopRecCommand(editor);
                                    stopRecord.execute();
                                    break;
                                case P://Play a macro
                                    Command playCommand = new PlayCommand(editor);
                                    playCommand.execute();
                                    break;
                                default:
                                    break;
                            }
                        }
                        return null;
                    }

                    // Typed characters
                    if (keyEvent.getEventType().equals(KeyEvent.KEY_TYPED) && keyEvent.getCharacter() != null) {
                        if (keyEvent.getCharacter().equals("\b")) {
                            Command deleteCommand = new DeleteCommand(editor);
                            deleteCommand.execute();
                        } else if (keyEvent.getCharacter().equals("\r")) { // Touche entrée
                            Command insertCommand = new InsertCommand(editor, "\n");
                            insertCommand.execute();
                        } else {
                            Command insertCommand = new InsertCommand(editor, keyEvent.getCharacter());
                            insertCommand.execute();
                        }
                        return null;
                    }

                    // Caret position / Selection
                    if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED &&
                            (keyEvent.getCode() == KeyCode.LEFT
                                    || keyEvent.getCode() == KeyCode.RIGHT
                                    || keyEvent.getCode() == KeyCode.UP
                                    || keyEvent.getCode() == KeyCode.DOWN)) {
                        defaultDispatcher.dispatchEvent(event, eventDispatchChain);
                        Command selectCommand = new SelectCommand(editor, textArea.getSelection().getStart(),
                                textArea.getSelection().getEnd());
                        selectCommand.execute();
                        return null;
                    }
                }
                // Mouse selection
                if (event.getEventType() == javafx.scene.input.MouseEvent.MOUSE_RELEASED) {
                    defaultDispatcher.dispatchEvent(event, eventDispatchChain);
                    Command selectCommand = new SelectCommand(editor, textArea.getSelection().getStart(),
                            textArea.getSelection().getEnd());
                    selectCommand.execute();
                    return null;
                }
                return defaultDispatcher.dispatchEvent(event, eventDispatchChain);
            }
        });


        StackPane root = new StackPane();
        root.getChildren().add(textArea);
        primaryStage.setScene(new Scene(root, 400, 320));
        primaryStage.show();
    }

    @Override
    public void update(Object o) {
        if (o instanceof MiniEditor) {
            MiniEditor editor = (MiniEditor) o;
            textArea.setText(editor.getBuffer());
            textArea.selectRange(editor.getSelection().getStart(), editor.getSelection().getEnd());
        }
    }
}
