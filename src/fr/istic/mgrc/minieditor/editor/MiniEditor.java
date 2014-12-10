package fr.istic.mgrc.minieditor.editor;

import fr.istic.mgrc.minieditor.Observable;


public interface MiniEditor extends Observable {

    /**
     * Renvoie le contenu du buffer prêt à être affiché dans une GUI
     *
     * @return Contenu du buffer
     */
    public String getBuffer();

    /**
     * Retourne la longueur du buffer
     *
     * @return Longueur du buffer
     */
    public int getBufferLength();

    /**
     * Renvoie le String correspondant aux index de début et de fin de la sélection
     *
     * @return Texte selectionné
     */
    public String getSelectionString();

    /**
     * Renvoie la selection
     *
     * @return Selection
     */
    public Selection getSelection();

    /**
     * Renvoie le contenu du clipboard
     *
     * @return Contenu du clipboard
     */
    public String getClipboard();

    /**
     * Insert du texte à la position de Selection.start
     *
     * @param substring
     */
    public void insert(String substring);

    /**
     * Met à jour les index de début et de fin de la sélection
     *
     * @param start Index de début (inclu)
     * @param end   Index de fin (exclu)
     */
    public void select(int start, int end);

    /**
     * Copie dans le clipboard le texte renvoyé par #getSelectionString()
     */
    public void copy();

    /**
     * Copie dans le clipboard le texte renvoyé par #getSelectionString() et le supprime du buffer
     */
    public void cut();

    /**
     * Insert le texte contenu de clipboard à la position de selection.start
     */
    public void paste();

    /**
     * Supprime le texte présent dans la selection si selection.length > 0.
     * Sinon supprime le caractère à la position selection.start - 1 s'il existe.
     */
    public void delete();

    /**
     * Démarre l'enregistrement d'une macro
     */
    public void startRecording();

    /**
     * Termine l'enregistrement d'une macro
     */
    public void endRecording();

    /**
     * Rejoue la dernière marco enregistrée
     */
    public void playRecording();

    /**
     * Annule la dernière action
     */
    public void undo();

    /**
     * Rejoue la dernière action annulée si aucune autre action n'a été executée depuis.
     */
    public void redo();
}

