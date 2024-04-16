package album.utility;

import album.controller.PhotoAlbumController;
import album.model.PhotoAlbumModel;
import album.views.GraphicalView;
import album.views.WebView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The CommandLineProcessor is responsible for parsing command-line arguments
 * and executing the application based on these arguments.
 */
public class CommandLineProcessor {
  //Used to store the names and values of command line arguments.
  private Map<String, String> options = new HashMap<>();

  /**
   * The constructor takes an array of strings, args, that contains all the arguments passed in from the command line.
   * The constructor calls the parseArguments method to parse the arguments and stores the parsed arguments in the options HashMap.
   *
   * @param args
   */
  public CommandLineProcessor(String[] args) {
    parseArguments(args);
  }


  /**
   * Parses the command-line arguments and stores them as options.
   *
   * @param args Command-line arguments array to be parsed.
   */
  private void parseArguments(String[] args) {
    for (int i = 0; i < args.length; i += 2) {
      if (args[i].startsWith("-") && i + 1 < args.length) {
        options.put(args[i], args[i + 1]);
      }
    }
  }

  /**
   * Executes the application based on the parsed command-line arguments.
   * Reads command file, initializes the model, creates the appropriate view,
   * and starts the application.
   *
   * @throws IOException If an I/O error occurs reading from the file or a malformed input is found.
   */
  public void execute() throws IOException {
    try {
      // Retrieve the file path, view type, output file path, and view dimensions from the options.
      String commandFilePath = options.get("-in");
      String viewType = options.get("-view");
      String outputFile = options.get("-out");
      int viewWidth = Integer.parseInt(options.getOrDefault("-width", "1000"));
      int viewHeight = Integer.parseInt(options.getOrDefault("-height", "1000"));

      // Validate required parameters
      if (commandFilePath == null || viewType == null) {
        throw new IllegalArgumentException("Input file path and view type must be specified.");
      }

      // Read the command file and process each line to update the model's state.
      List<String> commandLines = Files.readAllLines(Paths.get(commandFilePath));
      PhotoAlbumModel model = new PhotoAlbumModel();
      FileCommandProcessor commandProcessor = new FileCommandProcessor(model);

      //Excute each line command to modify the model
      for (String line : commandLines) {
        commandProcessor.processLine(line);
      }

      // Output the total number of snapshots taken, as a simple console log. for debugging
      System.out.println("Model snapshots count: " + model.getAllSnapshots().size());

      // Initialize the requested view type with the constructed model.
      switch (viewType.toLowerCase()) {
        // Generate a web view if requested, and output it to the specified file.
        case "web":
          WebView webView = new WebView(model, viewWidth, viewHeight);
          webView.generateHTML(outputFile);
          System.out.println("Web view generated at: " + outputFile);
          break;
        case "graphical":
          // Display a graphical view if requested.
          GraphicalView view = new GraphicalView(model, 800,800);
          PhotoAlbumController controller = new PhotoAlbumController(model, view);
          view.setController(controller);
          controller.initView(); // This will display the first snapshot if available
          view.setVisible(true); // Make the view visible
          break;
        default:
          throw new IllegalArgumentException("Invalid view type specified: " + viewType);
      }
    } catch (Exception e) {
      // Catch and log any exceptions that occur during execution.
      System.err.println("Error during execution: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
