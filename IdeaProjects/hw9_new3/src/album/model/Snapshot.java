package album.model;

import album.model.shape.IShape;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a snapshot of the state of a photo album at a specific moment.
 * A snapshot contains various shapes and may include an optional description and image.
 */
public class Snapshot {
  private final String snapshotID;
  private final LocalDateTime timeStamp;
  private final List<IShape> shapes;
  private final String picDescription;


  /**
   * Constructs a new snapshot with specified shapes and a description.
   * The snapshot's ID and timestamp are automatically generated based on the current time.
   *
   * @param shapes the list of shapes to include in the snapshot.
   * @param picDescription the textual description of the snapshot.
   */
  public Snapshot(List<IShape> shapes, String picDescription) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    this.timeStamp = LocalDateTime.now();
    this.snapshotID = timeStamp.format(formatter);
    this.shapes = new ArrayList<>(shapes);
    this.picDescription = picDescription;

  }


  /**
   * Retrieves the unique ID of the snapshot.
   *
   * @return the snapshot ID.
   */
  public String getSnapshotID() {
    return snapshotID;
  }

  /**
   * Returns a list of shapes included in the snapshot.
   *
   * @return the list of shapes.
   */
  public List<IShape> getShapes() {
    return shapes;
  }


  /**
   * Provides a detailed description of the snapshot including its ID, timestamp, and a detailed list of shapes.
   *
   * @return a string representation of the snapshot's details.
   */
  public String getPicDescription() {
    StringBuilder shapesDescription = new StringBuilder();
    shapesDescription.append("<span style=\"color: red;\">Picture ID: " + getSnapshotID() + " TimeStamp: " + getTimeStamp() + "</span>").append("<br>");
    shapesDescription.append(" Picture description details: ").append("<br>");
    for (IShape shape : shapes) {
      shapesDescription.append(shape.getId());
      shapesDescription.append(shape.getDescription()).append("<br>"); // using <br> to switch the line
    }
    return shapesDescription.toString();
  }


  /**
   * Returns the formatted timestamp of when the snapshot was taken.
   *
   * @return the formatted timestamp.
   */
  public String getTimeStamp() {
    return timeStamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  /**
   * Provides a textual representation of the snapshot, detailing the ID, timestamp, description, and shape details.
   *
   * @return a string representation of the snapshot.
   */
  public String toString() {
    return "Snapshot ID: " + snapshotID +
            "\nTimestamp: " + getTimeStamp() +
            "\nDescription: " + picDescription +
            "\nShapes:\n" + shapes.stream().map(IShape::getDescription).collect(Collectors.joining("\n"));
  }
}


