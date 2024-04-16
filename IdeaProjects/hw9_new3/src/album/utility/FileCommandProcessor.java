package album.utility;

import album.model.PhotoAlbumModel;
import album.model.Transformation.ChangeColor;
import album.model.Transformation.ITransformation;
import album.model.shape.IShape;
import album.model.shape.Ovals;
import album.model.shape.Rectangle;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Processes file commands and applies them to the PhotoAlbumModel.
 */
public class FileCommandProcessor {

  private PhotoAlbumModel model;

  /**
   * Constructs a new FileCommandProcessor with a given PhotoAlbumModel.
   *
   * @param model the PhotoAlbumModel to which commands will be applied
   */
  public FileCommandProcessor(PhotoAlbumModel model) {
    this.model = model;
  }

  /**
   * Processes a single line of text as a command.
   *
   * @param line the line of text representing a command
   */
  public void processLine(String line) {
    // Trim and check for empty or comment lines
    line = line.trim();
    if (line.isEmpty() || line.startsWith("#")) {
      //ignore the blank space and comment line
      return;
    }

    // Split the line into parts and execute the corresponding command
    String[] commandParts = line.split("\\s+");
    if (commandParts.length == 0) {
      return; // if after split do not have command ,then return
    }
    String command = commandParts[0].toLowerCase();

    // Handle the command based on the first keyword
    switch (command) {
      case "shape":
        processCreateShapeCommand(commandParts);
        break;
      case "move":
        processMoveCommand(commandParts);
        break;
      case "color":
        processChangeColorCommand(commandParts);
        break;
      case "resize":
        processResizeCommand(commandParts);
        break;
      case "remove":
        processRemoveCommand(commandParts);
        break;
      case "snapshot":
        processSnapshotCommand(commandParts);
        break;
      default:
        throw new IllegalArgumentException("Unknown command: " + commandParts[0]);
    }
  }

  /**
   * Processes the 'color' command to change the color of a shape.
   *
   * @param commandParts The array of strings after splitting the command line by spaces.
   *                     Expected format: color <shape-id> <red> <green> <blue>
   */
  private void processChangeColorCommand(String[] commandParts) {
    String shapeId = commandParts[1];
    int red  = Integer.parseInt(commandParts[2]);
    int green = Integer.parseInt(commandParts[3]);
    int blue = Integer.parseInt(commandParts[4]);

    ITransformation colorChange = new ChangeColor(red, green, blue);
    model.applyTransformationToShape(colorChange,shapeId);
  }

  /**
   * Processes the "shape" command to create and add a new shape to the model.
   *
   * @param commandParts the components of the "shape" command including type, dimensions, and color
   */
  private void processCreateShapeCommand(String[] commandParts) {
    //Assuming commandParts follow the pattern:
    //[ shape, id, type, x,y, width, height,red, green , blue ]
    if (commandParts.length != 10) {
      throw new IllegalArgumentException("Invalid command format for creating a shape,please check again");
    }

    String id = commandParts[1];
    String type = commandParts[2];
    int x = Integer.parseInt(commandParts[3]);
    int y = Integer.parseInt(commandParts[4]);
    int width = Integer.parseInt(commandParts[5]);
    int height = Integer.parseInt(commandParts[6]);
    int red = Integer.parseInt(commandParts[7]);
    int green = Integer.parseInt(commandParts[8]);
    int blue = Integer.parseInt(commandParts[9]);

    Color color = new Color(red, green ,blue);
    IShape shape1;
    switch (type.toLowerCase()) {
      case "rectangle":
        shape1 = new Rectangle(id, x, y, width, height, red, green, blue);
        model.addShape(shape1);
        break;
      case "oval" :
        shape1 = new Ovals(id, x,y,width,height,red, green, blue);
        model.addShape(shape1);
        break;
      default:
        throw new IllegalArgumentException("Unsupported shape type:" + type);
    }
  }

  /**
   * Processes the 'move' command to move a shape within the model.
   *
   * @param commandParts The array of strings after splitting the command line by spaces.
   *                     Expected format: move <shape-id> <new-x> <new-y>
   */
  private void processMoveCommand(String[] commandParts){
    if (commandParts.length != 4) {
      throw new IllegalArgumentException("Invalid command format for .Expected format: move <id> <x> <y>");
    }
    String id = commandParts[1];
    int newX = Integer.parseInt(commandParts[2]);
    int newY = Integer.parseInt(commandParts[3]);
    System.out.println("ssss");
    System.out.println(id);
    model.moveShape(id, newX, newY);
  }

  /**
   * Processes the 'resize' command to resize a shape within the model.
   *
   * @param commandParts The array of strings after splitting the command line by spaces.
   *                     Expected format: resize <shape-id> <factor-width> <factor-height>
   */
  private void processResizeCommand(String[] commandParts) {
    //ensure the commandparts array has the correct length
    if (commandParts.length != 4) {
      throw new IllegalArgumentException("Invalid number of arguments for resize command,please check again");
    }

    //Parse the command parts
    String shapeId = commandParts[1];
    double factorWidth;
    double factorHeight;
    try {
      factorWidth = Double.parseDouble(commandParts[2]);
      factorHeight = Double.parseDouble(commandParts[3]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Width and height must be integers", e);
    }

    model.resizeShape(shapeId, factorWidth, factorHeight);
  }

  /**
   * Processes the 'remove' command to remove a shape from the model.
   *
   * @param commandParts The array of strings after splitting the command line by spaces.
   *                     Expected format: remove <shape-id>
   */
  private void processRemoveCommand(String[] commandParts) {
    if (commandParts.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments for remove command");
    }

    String shapeId = commandParts[1];
    boolean isRemoved = model.removeShapeById(shapeId);

    if (isRemoved) {
      System.out.println("Shape with ID: " + shapeId + " was removed successfully.");
    } else {
      System.out.println("Shape with ID: " + shapeId + " not found and cannot be removed.");
    }
  }


  /**
   * Takes a snapshot of the current model state.
   *
   * @param commandParts the command parts, where the first element is "snapshot"
   *                     and the following elements are the description of the snapshot
   */
  private void processSnapshotCommand(String[] commandParts) {
    //the first part is the "snapshot" keyword, so the description if any) is the combination of the rest
    String description = commandParts.length > 1
            ? String.join(" ", Arrays.copyOfRange(commandParts,1, commandParts.length))
            : "";

    // Format the current timestamp for the snapshot.
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    String timestamp = sdf.format(new Date());

    // Take a snapshot with the description and timestamp.
//    model.takeSnapshot(description, timestamp);
    model.takeSnapshot(description);
  }

}
