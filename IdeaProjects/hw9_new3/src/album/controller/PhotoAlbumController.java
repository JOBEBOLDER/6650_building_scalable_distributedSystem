package album.controller;

import album.model.PhotoAlbumModel;
import album.model.Snapshot;
import album.model.shape.IShape;
import album.views.GraphicalView;
import album.views.DrawingPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * The PhotoAlbumController is responsible for managing interactions between the model and the graphical view.
 * It facilitates navigation between snapshots and updates the view with the current snapshot's state.
 */
public class PhotoAlbumController {
  private PhotoAlbumModel model;
  private GraphicalView view;
  private int currentSnapshotIndex = 0;

  /**
   * Constructs a PhotoAlbumController with the specified model and view.
   *
   * @param model The model that this controller will manage.
   * @param view  The graphical view this controller will update and interact with.
   */
  public PhotoAlbumController(PhotoAlbumModel model, GraphicalView view) {
    this.model = model;
    this.view = view;
    this.view.setController(this);
  }

  /**
   * Initializes the view with the current state of the model.
   * This method should be called to set up the initial snapshot view.
   */
  public void initView() {
    updateViewToCurrentSnapshot();
    }


  /**
   * Displays the previous snapshot in the album if available.
   * Shows an error message if there are no earlier snapshots.
   */
  public void showPreviousSnapshot() {
    if (currentSnapshotIndex > 0) {
      currentSnapshotIndex--;
      updateViewToCurrentSnapshot();
    } else {
      view.displayErrorMessage("This is the first snapshot!");
    }
  }

  /**
   * Displays the next snapshot in the album if available.
   * Shows an error message if there are no further snapshots.
   */
  public void showNextSnapshot() {
    if (currentSnapshotIndex < model.getSnapshotCount() - 1) {
      currentSnapshotIndex++;
      updateViewToCurrentSnapshot();
    } else {
      view.displayErrorMessage("This is the last snapshot!");
    }
  }

  /**
   * Jumps to a specified snapshot index and updates the view.
   *
   * @param snapshotIndex The index of the snapshot to display.
   */
  public void jumpToSnapshot(int snapshotIndex){
    // Check if the index is within the range of available snapshots
    if (snapshotIndex >= 0 && snapshotIndex < model.getSnapshotCount()) {
      //update the current index
      currentSnapshotIndex = snapshotIndex;
      updateViewToCurrentSnapshot();
    } else {
      //if the index is out of range,display an error message
      view.displayErrorMessage("Snapshot index out of bounds");
    }
  }

  /**
   * Updates the view to show the current snapshot.
   * This method retrieves the shapes from the current snapshot and refreshes the drawing panel.
   */
  private void updateViewToCurrentSnapshot() {
      List<Snapshot> allSnapshots = model.getAllSnapshots();
      Snapshot snapshot = allSnapshots.get(currentSnapshotIndex);
      List<IShape> shapes = snapshot.getShapes();

      DrawingPanel drawingPanel = new DrawingPanel();
    for (IShape shape : shapes) {
      drawingPanel.addShape(shape); // add shape to the drawingpanel
    }
    view.setPaintPanel(drawingPanel); // set currentview to drawingpanel
  }

  /**
   * Retrieves the titles of all snapshots in the model.
   * Each title includes the snapshot's ID and timestamp.
   *
   * @return A list of titles for all snapshots.
   */
  public List<String> getModelSnapshotTitles() {
    List<String> titles = new ArrayList<>();
    for (Snapshot snapshot : model.getAllSnapshots()) {
      String title = "Snapshot ID: " + snapshot.getSnapshotID() + ", TimeStamp: "
              + snapshot.getTimeStamp();
      titles.add(title);
    }
    return titles;
  }

}

