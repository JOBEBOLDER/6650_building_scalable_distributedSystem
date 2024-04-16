package album.model.Transformation;

import album.model.shape.IShape;

/**
 * Represents a movement transformation that can be applied to shapes.
 * This transformation shifts a shape by specified amounts along the X and Y axes.
 */
public class Move implements ITransformation {
  private int deltaX, deltaY;// The distances to move the shape along the X and Y axes, respectively.

  /**
   * Constructs a {@code Move} transformation with specified deltas for X and Y directions.
   *
   * @param deltaX The distance to move the shape along the X-axis.
   * @param deltaY The distance to move the shape along the Y-axis.
   */
  public Move(int deltaX, int deltaY) {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }


  /**
   * Applies the movement to the specified shape. The shape's position is adjusted
   * by the deltas specified in this transformation.
   *
   * @param shape The shape to which the movement will be applied.
   */
  @Override
  public void apply(IShape shape) {
    shape.move(deltaX, deltaY);
  }
}
