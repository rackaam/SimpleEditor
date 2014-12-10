package fr.istic.mgrc.minieditor.editor;


/**
 * Représente l'état de la selection d'un éditeur
 */
public class Selection {
    private int start;
    private int end;

    /**
     * Renvoie l'index de début de la selection (inclu)
     *
     * @return Index de début
     */
    public int getStart() {
        return start;
    }

    /**
     * Met à jour l'index de début (inclu)
     *
     * @param start Nouvelle valeur de l'index de début
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Renvoie l'index de fin de la sélection (exclu)
     *
     * @return Index de fin
     */
    public int getEnd() {
        return end;
    }

    /**
     * Met à jour l'index de fin (exclu)
     *
     * @param end Nouvelle valeur de l'index de fin
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Met à jour l'index de début et l'index de fin de la selection
     *
     * @param start Index de début (inclu)
     * @param end   Index de fin (exclu)
     */
    public void set(int start, int end) {
        setStart(start);
        setEnd(end);
    }

    /**
     * Retourne la longueur de la sélection (fin - début)
     *
     * @return Longueur de la selection
     */
    public int getLength() {
        return getEnd() - getStart();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Selection)) return false;
        Selection other = (Selection) obj;
        if (other.getStart() == start && other.getEnd() == end)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Selection: " + start + "/" + end;
    }
}

