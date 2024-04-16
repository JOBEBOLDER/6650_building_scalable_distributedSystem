package album.model.Transformation;

import album.model.shape.IShape;

/**
 * Represents a resize transformation that can be applied to shapes.
 * This transformation adjusts the size of a shape by a specified scaling factor.
 */
public class Resize implements ITransformation {

  private double widthFactor;
  private double heightFactor;

  /**
   * Constructs a {@code Resize} transformation with a specified scaling factor.
   * A factor greater than 1.0 enlarges the shape, while a factor less than 1.0 shrinks it.
   *
   * @param factor The scaling factor to apply to the shape.
   */
  public Resize(double widthFactor, double heightFactor) {
    this.heightFactor = heightFactor;
    this.widthFactor = widthFactor;
  }


  /**
   * Applies the resize transformation to the specified shape.
   * The shape's dimensions are scaled by the factor defined in this transformation.
   *
   * @param shape The shape to which the resizing will be applied.
   */
  public void apply(IShape shape) {
    shape.resize(widthFactor, heightFactor);
  }
}
