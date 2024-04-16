package album.model.Transformation;

import album.model.shape.IShape;

/**
 * Represents a remove transformation that can be applied to shapes to remove them from the album.
 */
public class Remove implements ITransformation {

  /**
   * Applies the remove transformation to the specified shape by setting its visibility to false
   * or by any other logic that indicates the shape should be considered removed.
   *
   * @param shape The shape to which the removal will be applied.
   */
  @Override
  public void apply(IShape shape) {
    // Implement the logic to mark a shape as removed.
    // This could be setting a 'visible' flag to false, or if the shapes are stored in a list,
    // you can directly remove the shape from the list in the PhotoAlbumModel class.
    shape.setVisible(false); // Assuming there is a setVisible method in IShape interface.
  }
}
