package album;

import album.utility.CommandLineProcessor;

/**
 * Main entry point for the Photo Album application.
 * This class initializes the application by parsing command line arguments and executing commands based on those arguments.
 */
public class PhotoAlbumMain {

  /**
   * The main method for the Photo Album application.
   * It creates an instance of CommandLineProcessor to handle the input commands and manage the flow of the application.
   *
   * @param args Command line arguments passed to the program.
   */
  public static void main(String[] args) {
    try {

      // Initialize the command line processor with the provided arguments
      CommandLineProcessor commandLineProcessor = new CommandLineProcessor(args);

      // Execute the command line processor to start the application logic
      commandLineProcessor.execute();
    } catch (Exception e) {
      // Print error message and stack trace if an exception occurs
      System.err.println("Error during execution: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);// Exit the program with an error status
    }
  }
}