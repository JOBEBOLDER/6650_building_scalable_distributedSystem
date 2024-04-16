package album.model.Transformation;

import album.model.shape.IShape;


/**
 * Defines the contract for transformations that can be applied to shapes.
 * Implementations of this interface represent different types of transformations,
 * such as moving, resizing, rotating, or changing the color of a shape.
 */
public interface ITransformation {

  /**
   * Applies this transformation to a specified shape. The exact effect of the
   * transformation depends on the implementation, ranging from altering the
   * shape's position, size, color, or other attributes.
   *
   * @param shape The shape to which the transformation will be applied.
   */
  void apply(IShape shape);

}
