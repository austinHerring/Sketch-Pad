import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcType;

/**
* This is the Tool the mimics the behavior of a Pencil on a canvas.
* @author Austin Herring
* @version 1.0
*/
public class Pencil extends AbstractTool {

    private int girth;
    private int n;
    private double startX;
    private double startY;

    /**
    * The constructor for a Pencil
    * @param girth int for thinkness of pencil
    * @param color Color to draw with
    * @param number to activate CRAAZZAYY
    */
    public Pencil(int girth, Color color, int number) {
        this.girth = girth;
        c = color;
        n = number;
    }

    /**
     * Tool method that is called when the mouse is pressed.
     * Begins drawing something with the specified color where pressed.
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        startX = e.getX();
        startY = e.getY();
        g.setFill(c);
        g.fillArc(e.getX() - girth / 2, e.getY() - girth / 2, girth,
            girth, 0.0, 360.0, ArcType.ROUND);
    }

    /**
     * Tool method that is called when the mouse is dragged.
     * Continues to place points where the mouse moves.
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.setStroke(c);
        g.setFill(c);

        // This distributes 14 dots between the last location and the
        // new location of the click drag. This allows for a smoother drawing.
        if (n == 0) {
            double halfX = (startX + e.getX()) / 2;
            double halfY = (startY + e.getY()) / 2;
            double fourthX = (startX + halfX) / 2;
            double fourthY = (startY + halfY) / 2;
            double thirdX = (halfX + e.getX()) / 2;
            double thirdY = (halfY + e.getY()) / 2;
            double eigth1X = (startX + fourthX) / 2;
            double eigth1Y = (startY + fourthY) / 2;
            double eigth2X = (fourthX + halfX) / 2;
            double eigth2Y = (fourthY + halfY) / 2;
            double eigth3X = (halfX + thirdX) / 2;
            double eigth3Y = (halfY + thirdY) / 2;
            double eigth4X = (thirdX + e.getX()) / 2;
            double eigth4Y = (thirdY + e.getY()) / 2;
            g.fillArc(eigth1X - girth / 2, eigth1Y - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(fourthX - girth / 2, fourthY - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(eigth2X - girth / 2, eigth2Y - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(halfX - girth / 2, halfY - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(eigth3X - girth / 2, eigth3Y - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(thirdX - girth / 2, thirdY - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(eigth4X - girth / 2, eigth4Y - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(e.getX() - girth / 2, e.getY() - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
        } else {
            double halfX = (startX + e.getX()) / 2;
            double halfY = (startY + e.getY()) / 2;
            double fourthX = (startX + e.getX()) / 4;
            double fourthY = (startY + e.getY()) / 4;
            double thirdX = (startX + e.getX()) * 3 / 4;
            double thirdY = (startY + e.getY()) * 3 / 4;
            g.fillArc(fourthX - girth / 2, fourthY - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(halfX - girth / 2, halfY - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(thirdX - girth / 2, thirdY - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
            g.fillArc(e.getX() - girth / 2, e.getY() - girth / 2, girth, girth,
                0.0, 360.0, ArcType.ROUND);
        }
        startX = e.getX();
        startY = e.getY();
    }

    /**
     * Tool method that is called when the mouse is released.
     * Finishes changing the graphic of the pencil
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
        g.setStroke(c);
        g.setFill(c);
        g.fillArc(e.getX() - girth / 2, e.getY() - girth / 2, girth, girth,
            0.0, 360.0, ArcType.ROUND);
    }

    /**
     * The name of this tool.
     * @return This tool's name.
     */
    public String getName() {
        if (girth == 30) {
            return "Eraser";
        } else if (girth == 5) {
            return "PencilThin";
        } else {
            return "PencilThick";
        }
    }

    /**
    * The method allows change to a tools backing Color immediatley
    * after change in color picker.
    * @param color The color that a tool will be set to use.
    */
    public void setColorBack(Color color) {

    }
}
