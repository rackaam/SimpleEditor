package fr.istic.mgrc.minieditor.editor;

public class Selection {
    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {

        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void set(int start, int end) {
        setStart(start);
        setEnd(end);
    }

    public int getLength() {
        return getEnd() - getStart();
    }

}
