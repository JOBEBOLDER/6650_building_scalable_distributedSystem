package album.views;

import album.controller.PhotoAlbumController;
import album.model.PhotoAlbumModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The main window for the graphical view of the photo album.
 */
public class GraphicalView extends JFrame {
  private PhotoAlbumController controller;
  private JButton prevButton, nextButton, selectButton, exitButton;
  private JLabel snapshotDisplay;
  private DrawingPanel paintPanel;
  private int currentSnapshotIndex;


  /**
   * Constructs a default graphical view.
   */
  public GraphicalView() {
    super("Shapes Photo Album");
    setSize(800, 800);
    initializeUI();
    this.controller.initView();
    this.add(paintPanel);
  }

  /**
   * Constructs a graphical view with a specified model and dimensions.
   *
   * @param newModel  The PhotoAlbumModel that this view will display.
   * @param viewWidth  The width of the view window.
   * @param viewHeight The height of the view window.
   */
  public GraphicalView(PhotoAlbumModel newModel, int viewWidth, int viewHeight) {
    super("Shapes Photo Album");
    this.controller = new PhotoAlbumController(newModel, this);
    setSize(viewWidth, viewHeight);
    initializeUI();
    this.controller.initView();
    this.add(paintPanel);
  }


  /**
   * Initializes the user interface components.
   */
  private void initializeUI() {
    //UI set up
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    this.prevButton = new JButton("<< Prev <<");
    this.nextButton = new JButton(">> Next >>");
    this.selectButton = new JButton("^^Select^^");
    this.exitButton = new JButton(" xx Exit xx");
    this.prevButton.addActionListener((e) -> {
      this.controller.showPreviousSnapshot();
    });
    this.nextButton.addActionListener((e) -> {
      this.controller.showNextSnapshot();
    });
    this.selectButton.addActionListener((e) -> {
      this.selectSnapshot();
    });
    this.exitButton.addActionListener((e) -> {
      System.exit(0);
    });
    JPanel buttonPanel = new JPanel(new FlowLayout(1));
    buttonPanel.add(this.prevButton);
    buttonPanel.add(this.nextButton);
    buttonPanel.add(this.selectButton);
    buttonPanel.add(this.exitButton);
    this.setLayout(new BorderLayout());
    this.add(buttonPanel, "South");

    //initialize paintPanel
    paintPanel = new DrawingPanel();
    this.add(paintPanel, BorderLayout.CENTER);
  }

  /**
   * Sets the panel used for drawing shapes.
   *
   * @param paintPanel The panel that contains the drawing logic.
   */
  public void setPaintPanel(DrawingPanel paintPanel) {
    if (this.paintPanel != null){
      this.remove(this.paintPanel);
    }
    this.paintPanel = paintPanel;
    this.add(paintPanel, BorderLayout.CENTER);
    this.validate();
    this.repaint();
  }

  /**
   * Assigns the controller that handles user interactions.
   *
   * @param controller The controller that coordinates between the view and model.
   */
  public void setController(PhotoAlbumController controller) {
    this.controller = controller;
  }

  /**
   * Displays an error message to the user.
   *
   * @param message The error message to display.
   */
  public void displayErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  /**
   * Enables or disables the navigation buttons based on the application state.
   *
   * @param enablePrev Whether to enable the "previous" button.
   * @param enableNext Whether to enable the "next" button.
   */
  public void setNavigationButtonsEnabled(boolean enablePrev, boolean enableNext) {
    prevButton.setEnabled(enablePrev);
    nextButton.setEnabled(enableNext);
  }


  /**
   * Displays a dialog for the user to select a snapshot to display.
   */
  private void selectSnapshot() {
    List<String> snapshotTitles = controller.getModelSnapshotTitles();
    JComboBox<String> snapshotSelector = new JComboBox<>(snapshotTitles.toArray(new String[0]));

    // 'this' should refer to the instance of GraphicalView, which is a JFrame, hence a Component.
    int result = JOptionPane.showConfirmDialog(this, snapshotSelector, "Select Snapshot",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      int selectedIndex = snapshotSelector.getSelectedIndex();
      controller.jumpToSnapshot(selectedIndex);
    }
  }




}

