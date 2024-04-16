package album.model;

import album.model.Transformation.ITransformation;
import album.model.Transformation.Remove;
import album.model.shape.IShape;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code PhotoAlbumModel} class implements the {@code IModel} interface,
 * managing a collection of shapes and snapshots of their states within a photo album.
 * It supports operations such as adding and removing shapes, applying transformations,
 * and handling snapshots to capture and retrieve the current state of the album.
 */
public class PhotoAlbumModel implements IModel{
  private Map<String, IShape> shapes = new LinkedHashMap<>();
  private List<Snapshot> snapshots = new ArrayList<>();


  /**
   * Adds a shape to the album if it does not already exist.
   *
   * @param shape The shape to be added.
   */
  @Override
  public void addShape(IShape shape) {
    if (shape != null && !shapes.containsKey(shape.getId())) {
      shapes.put(shape.getId(), shape);
    }
  }


  /**
   * Removes a specified shape from the album.
   *
   * @param shape The shape to be removed.
   */
  @Override
  public void removeShape(IShape shape) {
    if (shape != null) {
      shapes.remove(shape.getId());
    }
  }


  /**
   * Resets the photo album. Clears all shapes and snapshots.
   */
  @Override
  public void resetAlbum() {
    shapes.clear();
    snapshots.clear();
  }


  /**
   * Retrieves a specific snapshot by its index.
   *
   * @param index The index of the snapshot to retrieve.
   * @return The string representation of the snapshot at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= number of snapshots).
   */
  @Override
  public String getSnapshot(int index) {
    if (index < 0 || index >= snapshots.size()) {
      throw new IndexOutOfBoundsException("Snapshot index out of bounds");
    }
    return snapshots.get(index).toString();
  }


  /**
   * Retrieves the total number of snapshots in the photo album.
   *
   * @return The total number of snapshots.
   */
  public int getSnapshotCount() {
    return snapshots.size();
  }


  /**
   * Applies a transformation to all shapes in the album.
   * A snapshot is taken after the transformation is applied.
   *
   * @param transformation The transformation to apply to each shape.
   */
  public void applyTransformation(ITransformation transformation) {
    shapes.values().forEach(transformation::apply);
    takeSnapshot("Applied transformation: " + transformation.getClass().getSimpleName());
    }

  /**
   * Applies a transformation to a specific shape and takes a snapshot.
   *
   * @param transformation The transformation to apply.
   * @param shapeId The ID of the shape to transform.
   */
  public void applyTransformationToShape(ITransformation transformation, String shapeId) {
    if (shapes.containsKey(shapeId)) {
      transformation.apply(shapes.get(shapeId));
      takeSnapshot("Transformed shape: " + shapeId);
    }
  }

  /**
   * Captures the current state of the album into a snapshot.
   *
   * @param description Description of the snapshot.
   */
  public void takeSnapshot(String description) {
    List<IShape> shapesCopy = shapes.values().stream().map(IShape::clone).collect(Collectors.toList());
    Snapshot snapshot = new Snapshot(shapesCopy, description);
    snapshots.add(snapshot);
  }


  /**
   * Prints all snapshots taken of the photo album to the standard output.
   */
  public void printSnapshots() {
    if (snapshots.isEmpty()) {
      System.out.println("No snapshots taken yet.");
    } else {
      snapshots.forEach(System.out::println);
    }
  }

  /**
   * Retrieves all snapshots in the album.model as a list of string descriptions.
   * @return A list of snapshot descriptions.
   */
  public List<Snapshot> getAllSnapshots() {
    return snapshots;
  }

  /**
   * Moves a shape identified by its ID to a new location.
   *
   * @param id The unique identifier of the shape to move.
   * @param newX The new x-coordinate of the shape.
   * @param newY The new y-coordinate of the shape.
   * @throws IllegalArgumentException If no shape with the specified ID is found.
   */
  public void moveShape(String id, int newX, int newY) {
    if (shapes.containsKey(id)) {
      IShape shape = shapes.get(id);
      shape.setX(newX);
      shape.setY(newY);
      takeSnapshot("Moved shape: " + id);
      }
    }

  /**
   * Resizes a shape identified by its ID using specified width and height factors.
   *
   * @param id The unique identifier of the shape to resize.
   * @param factorWidth The factor by which the shape's width is to be multiplied.
   * @param factorHeight The factor by which the shape's height is to be multiplied.
   * @throws IllegalArgumentException If no shape with the specified ID is found.
   */
  public void resizeShape(String id, double factorWidth, double factorHeight) {
    IShape shapeToResie = shapes.get(id);
    if (shapeToResie == null) {
      throw new IllegalArgumentException("Shape with ID" + id + "not found");

    }
  }


  /**
   * Removes a shape from the model by its ID.
   *
   * @param id The unique identifier of the shape to be removed.
   * @return true if the shape was successfully removed, false if the shape was not found.
   */
  public boolean removeShapeById(String id) {
    if (shapes.containsKey(id)) {
      Remove remove = new Remove();
      remove.apply(shapes.get(id));
      shapes.remove(id);
      return true;
    }
    return false;
  }

}
