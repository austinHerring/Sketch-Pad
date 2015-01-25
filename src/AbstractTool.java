import javafx.scene.paint.Color;

/**
* This is an AbstractTool that implements behavior of tool but allows a
* subclass to set the colors.
* @author Austin Herring
* @version 1.0
*/
public abstract class AbstractTool implements Tool {

    protected Color c;
    protected Color back;
    /**
    * The method allows change to a tools Color immediatley after change
    * in color picker.
    *
    * @param color The color that a tool will be set to use.
    */
    public void setColor(Color color) {
        c = color;
    }

    /**
    * The method allows change to a tools backing Color immediatley
    * after change in color picker.
    *
    * @param color The color that a tool will be set to use.
    */
    public void setColorBack(Color color) {
        back = color;
    }
}
