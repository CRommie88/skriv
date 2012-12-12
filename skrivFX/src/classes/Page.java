package classes;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

public class Page extends Tab {

    private static final double scale = .65;
    private Canvas canvas;
    private GraphicsContext gc;
    private double curX, curY; // Cursor
    // Maybe...
    // A Page can only have one set of Notes
    //private Notes notes;

    public Page(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height) {
        this(width, height, "Untitled");
    }

    public Page(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height, String title) {
        super();
        this.setText(title);
        makeCanvas(width, height);
        this.setContent(canvas);
        curX = 20;
        curY = 20;
    }

    private void makeCanvas(ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height) {
        canvas = new Canvas();
        canvas.widthProperty().bind(width);
        canvas.heightProperty().bind(height);

        gc = canvas.getGraphicsContext2D();
    }

    public void drawWord(Word w) {
        if (canvas.getWidth() - curX < w.getWidth()) {
            curX = 20;
            curY = curY + w.getHeight() + 20;
        }
        gc.drawImage(w.getImage(), curX, curY, scale * w.getWidth(), scale * w.getHeight());
        curX = curX + 20 + w.getWidth();
    }

    public void handle(MouseEvent e) {
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
            mousePressedEvent(e);
        }
    }

    private void mousePressedEvent(MouseEvent e) {
        // this is obvious but it needs  reference to the list of words to check
        // if the x,y pair are within bounds... hmmm
        double posX = e.getSceneX();
        double posY = e.getSceneY();
    }
}