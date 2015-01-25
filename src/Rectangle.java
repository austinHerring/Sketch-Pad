import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
* This is the Tool the mimics the behavior of drawing a Rectangle on a canvas.
* @author Austin Herring
* @version 1.0
*/
public class Rectangle  extends AbstractTool {

    private double startX;
    private double startY;
    private double prevX;
    private double prevY;
    private double prevW;
    private double prevH;

    /**
    * The constructor for a Rectangle
    * @param color Color to draw with
    * @param color2 The color of the background
    */
    public Rectangle(Color color, Color color2) {
        c = color;
        back = color2;
    }

    /**
     * Tool method that is called when the mouse is pressed.
     * Stores the starting postion for the rectangle.
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        startX = e.getX();
        startY = e.getY();
        prevX = e.getX();
        prevY = e.getY();
        prevW = 0;
        prevH = 0;
    }

    /**
     * Tool method that is called when the mouse is dragged.
     * It shows where the current rectangle will be drawn if mouse is released
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.setFill(back);
        g.fillRect(prevX, prevY, prevW, prevH);
        g.setFill(c);
        if (startX < e.getX() && startY > e.getY()) {
            g.fillRect(startX, e.getY(), e.getX() - startX,
                startY - e.getY());
            prevX = startX;
            prevY = e.getY();
            prevW = e.getX() - startX;
            prevH = startY - e.getY();
        } else if (startX < e.getX() && startY < e.getY()) {
            g.fillRect(startX, startY, e.getX() - startX,
                e.getY() - startY);
            prevX = startX;
            prevY = startY;
            prevW = e.getX() - startX;
            prevH = e.getY() - startY;
        } else if (startX > e.getX() && startY > e.getY()) {
            g.fillRect(e.getX(), e.getY(), startX - e.getX(),
                startY - e.getY());
            prevX = e.getX();
            prevY = e.getY();
            prevW = startX - e.getX();
            prevH = startY - e.getY();
        } else {
            g.fillRect(e.getX(), startY, startX - e.getX(),
                e.getY() - startY);
            prevX = e.getX();
            prevY = startY;
            prevW = startX - e.getX();
            prevH = e.getY() - startY;
        }
    }

    /**
     * Tool method that is called when the mouse is released.
     * It will finish drawing the rectangle
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.setStroke(c);
        g.setFill(c);
        if (startX < e.getX() && startY > e.getY()) {
            g.strokeRect(startX, e.getY(), e.getX() - startX,
                startY - e.getY());
            g.fillRect(startX, e.getY(), e.getX() - startX,
                startY - e.getY());
        } else if (startX < e.getX() && startY < e.getY()) {
            g.strokeRect(startX, startY, e.getX() - startX,
                e.getY() - startY);
            g.fillRect(startX, startY, e.getX() - startX, e.getY() - startY);
        } else if (startX > e.getX() && startY > e.getY()) {
            g.strokeRect(e.getX(), e.getY(), startX - e.getX(),
                startY - e.getY());
            g.fillRect(e.getX(), e.getY(), startX - e.getX(),
                startY - e.getY());
        } else {
            g.strokeRect(e.getX(), startY, startX - e.getX(),
                e.getY() - startY);
            g.fillRect(e.getX(), startY, startX - e.getX(), e.getY() - startY);
        }

    }

    /**
     * The name of this tool.
     * @return This tool's name.
     */
    public String getName() {
        return "Rectangle";
    }
}
