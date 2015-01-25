import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.control.ColorPicker;

/**
 * This class creates a canvas a user can draw on.
 * @author Austin Herring
 * @version 1.0
 */
public class SketchPad extends Application {

    private GraphicsContext gc;
    private GraphicsContext gc1;
    private final Color[] colors = {Color.WHITE, Color.RED, Color.ORANGE,
        Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.BROWN,
        Color.BLACK};
    private int colorIdx = 0;
    private Color currentWritingColor;
    private AbstractTool pencilThin = new Pencil(5, currentWritingColor, 0);
    private AbstractTool pencilThick = new Pencil(18, currentWritingColor, 0);
    private AbstractTool crazy = new Pencil(8, currentWritingColor, 1);
    private AbstractTool rect = new Rectangle(currentWritingColor,
        colors[colorIdx]);
    private AbstractTool circ = new Circle(currentWritingColor,
        colors[colorIdx]);
    private AbstractTool ersr = new Pencil(30, colors[colorIdx], 0);
    private AbstractTool currentTool = new Pencil(5, Color.BLACK, 0);;


    @Override
    public void start(Stage stage) {
        stage.setTitle("Sketch Pad");
        Canvas canvas = new Canvas(600, 500);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(colors[colorIdx]);
        gc.fillRect(0, 0, 600, 500);
        Canvas canvas1 = new Canvas(600, 500);
        canvas1.setOnMousePressed((e) -> currentTool.onPress(e, gc1));
        canvas1.setOnMouseDragged((e) -> currentTool.onDrag(e, gc1));
        canvas1.setOnMouseReleased((e) -> currentTool.onRelease(e, gc1));
        gc1 = canvas.getGraphicsContext2D();

        Label label1 = new Label("Your Tools");
        label1.setFont(new Font("Cambria", 25));
        label1.setTextFill(Color.WHITE);
        label1.setUnderline(true);

        Button chgButton = new Button("Different Colored Canvas");
        chgButton.setMaxWidth(Double.MAX_VALUE);
        chgButton.setFont(new Font(20));
        chgButton.setOnAction(event -> {
                colorIdx += 1;
                gc.setFill(colors[colorIdx % 9]);
                gc.fillRect(0, 0, 600, 500);
                canvas.toBack();
                if (currentTool.getName().equals("Rectangle")
                    || currentTool.getName().equals("Circle")) {
                    currentTool.setColorBack(colors[colorIdx]);
                }
                if (currentTool.getName().equals("Eraser")) {
                    currentTool.setColor(colors[colorIdx]);
                }
            });

        Button penSButton = new Button("Pencil - Thin");
        penSButton.setMaxWidth(Double.MAX_VALUE);
        penSButton.setFont(Font.font(20));
        penSButton.setOnAction(event -> {
                currentTool = pencilThin;
                currentTool.setColor(currentWritingColor);
            });

        Button penBButton = new Button("Pencil - Thick");
        penBButton.setMaxWidth(Double.MAX_VALUE);
        penBButton.setFont(Font.font(20));
        penBButton.setOnAction(event -> {
                currentTool = pencilThick;
                currentTool.setColor(currentWritingColor);
            });

        Button cBButton = new Button("CRAAZZAAYY");
        cBButton.setMaxWidth(Double.MAX_VALUE);
        cBButton.setFont(Font.font(20));
        cBButton.setOnAction(event -> {
                currentTool = crazy;
                currentTool.setColor(currentWritingColor);
            });

        Button recButton = new Button("Rectangle");
        recButton.setMaxWidth(Double.MAX_VALUE);
        recButton.setFont(new Font(20));
        recButton.setOnAction(event -> {
                currentTool = rect;
                currentTool.setColor(currentWritingColor);
                currentTool.setColorBack(colors[colorIdx]);
            });

        Button cirButton = new Button("Circle");
        cirButton.setMaxWidth(Double.MAX_VALUE);
        cirButton.setFont(new Font(20));
        cirButton.setOnAction(event -> {
                currentTool = circ;
                currentTool.setColor(currentWritingColor);
                currentTool.setColorBack(colors[colorIdx]);
            });

        Button eraButton = new Button("Eraser");
        eraButton.setMaxWidth(Double.MAX_VALUE);
        eraButton.setFont(new Font(20));
        eraButton.setOnAction(event -> {
                currentTool = ersr;
                currentTool.setColor(colors[colorIdx]);
            });

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setMaxWidth(Double.MAX_VALUE);
        currentWritingColor = colorPicker.getValue();
        colorPicker.setOnAction(event -> {
                currentWritingColor = colorPicker.getValue();
                currentTool.setColor(colorPicker.getValue());
            });

        Button clrButton = new Button("Clear");
        clrButton.setMaxWidth(Double.MAX_VALUE);
        clrButton.setFont(new Font(20));
        clrButton.setOnAction(event -> {
                gc.clearRect(0, 0, 600, 500);
                gc1.clearRect(0, 0, 600, 500);
                colorIdx = 0;
                gc.setFill(colors[0]);
                gc.fillRect(0, 0, 600, 500);
                if (currentTool.getName().equals("Eraser")) {
                    currentTool.setColor(colors[colorIdx]);
                }
                if (currentTool.getName().equals("Rectangle")
                    || currentTool.getName().equals("Circle")) {
                    currentTool.setColorBack(colors[colorIdx]);
                }
            });

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setStyle("-fx-background-color: #000000;");
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(label1, colorPicker, penSButton, penBButton,
            recButton, cirButton, eraButton, cBButton, chgButton, clrButton);

        HBox root2 = new HBox();
        Pane pane = new Pane();
        pane.getChildren().addAll(canvas, canvas1);
        canvas1.toFront();
        root2.getChildren().addAll(root, pane);
        stage.setScene(new Scene(root2));
        stage.show();
    }
}
