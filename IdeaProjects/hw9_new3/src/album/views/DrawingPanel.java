package album.views;

import album.model.shape.IShape;
import album.model.shape.Ovals;
import album.model.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DrawingPanel is a custom JPanel that handles the rendering of shapes.
 */
public class DrawingPanel extends JPanel {

  // A list to store the shapes to be drawn.
  private List<IShape> shapes = new ArrayList<>();

  /**
   * Adds a shape to the panel.
   *
   * @param shape The shape to be added.
   */
  public void addShape(IShape shape) {
    shapes.add(shape);
    // When a new shape is added, the panel needs to be repainted to reflect the change.
    repaint();
  }

  /**
   * Custom painting component for drawing shapes.
   *
   * @param g The Graphics object used for drawing.
   */
  protected void paintComponent(Graphics g) {
    // Always call super.paintComponent to ensure the panel is properly rendered.
    super.paintComponent(g);

    // Iterate over the shapes and draw each one.
    for (IShape shape : shapes) {
      if (shape.isVisible()) {
        int[] color = shape.getColor();
        // Set the drawing color based on the shape's color properties.
        g.setColor(new Color(color[0], color[1],color[2]));

        // Determine the type of the shape and draw it accordingly.
        if (shape instanceof Ovals) {
          Ovals oval = (Ovals) shape;
          g.fillOval(oval.getX() - oval.getRadiusX(), oval.getY() - oval.getRadiusY(),
                  2 * oval.getRadiusX(), 2 * oval.getRadiusY());
        } else if (shape instanceof Rectangle) {
          Rectangle rect = (Rectangle) shape;
          g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }
      }
    }
  }
}

