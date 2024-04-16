package album.model;

import album.model.Transformation.ITransformation;
import album.model.shape.IShape;

/**
 * The {@code IModel} interface defines the contract for a photo album album.model.
 * It specifies the operations that can be performed on a photo album, including
 * adding and removing shapes, applying transformations, managing snapshots, and resetting the album.
 */
public interface IModel {
  /**
   * Adds a shape to the photo album.
   *
   * @param shape The shape to be added.
   */
  void addShape(IShape shape);

  /**
   * Removes a specified shape from the photo album.
   *
   * @param shape The shape to be removed.
   */
  void removeShape(IShape shape);

  /**
   * Applies a transformation to all shapes in the photo album.
   *
   * @param transformation The transformation to apply.
   */
  void applyTransformation(ITransformation transformation);

  /**
   * Takes a snapshot of the current state of the photo album. This snapshot includes
   * textual representations of all shapes currently in the album.
   *
   * @return A string representation of the current album state.
   */
  void takeSnapshot(String description);


  /**
   * Prints all snapshots taken of the photo album to the standard output.
   */
  void printSnapshots();

  /**
   * Resets the photo album. This clears all shapes from the album and removes all snapshots.
   */
  void resetAlbum();//reset the album

  /**
   * Retrieves a specific snapshot by its index.
   *
   * @param index The index of the snapshot to retrieve.
   * @return The string representation of the snapshot at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= number of snapshots).
   */
  String getSnapshot(int index);//retrieve a specific snapshot



}
