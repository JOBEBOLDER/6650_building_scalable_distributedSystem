package album.model.Transformation;

import album.model.shape.IShape;


/**
 * Represents a color change transformation that can be applied to shapes.
 * This transformation changes the color of a shape to a new specified color.
 */
public class ChangeColor implements ITransformation {
  private int r,g,b;

  /**
   * Constructs a {@code ChangeColor} transformation with the specified color components.
   *
   * @param r The red component of the new color (0-1 range).
   * @param g The green component of the new color (0-1 range).
   * @param b The blue component of the new color (0-1 range).
   */
  public ChangeColor(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Applies the color change to the specified shape. This method changes the
   * color of the shape to the color defined in this {@code ChangeColor} transformation.
   *
   * @param shape The shape to which the color change will be applied.
   */
  public void apply(IShape shape) {
    shape.changeColor(r,g,b);
  }
}
