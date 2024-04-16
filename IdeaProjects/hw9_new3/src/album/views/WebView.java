package album.views;

import album.model.PhotoAlbumModel;
import album.model.Snapshot;
import album.model.shape.IShape;
import album.model.shape.Ovals;
import album.model.shape.Rectangle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class represents the web view for the photo album model.
 * It generates HTML content displaying the shapes present in the snapshots.
 */
public class WebView {

  private final PhotoAlbumModel model;
  private final int viewWidth;
  private final int viewHeight;

  /**
   * Constructor for WebView.
   *
   * @param newModel  The photo album model to be displayed in the web view.
   * @param viewWidth  The preferred width for the view.
   * @param viewHeight The preferred height for the view.
   */
  public WebView(PhotoAlbumModel newModel, int viewWidth, int viewHeight) {
    this.model = newModel;
    this.viewHeight = viewHeight;
    this.viewWidth = viewWidth;
  }

  /**
   * Builds the HTML representation of the photo album.
   *
   * @return A string containing the HTML content.
   */
  public String buildHTML() {
    // Start building the HTML content
    StringBuilder htmlBuilder = new StringBuilder();

    // Append the doctype, html, head and body tags with required attributes and elements
    // Continue with the body of the HTML, starting with the main title
    htmlBuilder.append("<!DOCTYPE html>\n")
            .append("<html lang=\"en\">\n")
            .append("<head>\n")
            .append("<meta charset=\"UTF-8\">\n")
            .append("<title>Photo Album</title>\n")
            .append("<style>body { font-family: Arial, sans-serif; }</style>\n") // some basic format
            .append("</style>\n")
            .append("</head>\n")
            .append("<body>\n")
            .append("<h1>Photo Album</h1>\n"); // the header

    //traverl all the snapshots
    List<Snapshot> snapshots = model.getAllSnapshots();
    for (Snapshot snapshot : snapshots) {
      htmlBuilder.append("<div class=\"snapshot\">\n");

      // Create the SVG container for shapes
      htmlBuilder.append("<svg width=\"" + this.viewWidth + "\" height=\"" + this.viewHeight + "\" xmlns=\"http://www.w3.org/2000/svg\">\n");

      List<IShape> shapes = snapshot.getShapes();
        for (IShape shape : shapes){

          // For each shape, determine its type (Rectangle or Ovals) and add the corresponding SVG element
          if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            htmlBuilder.append(String.format(" <rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"rgb(%d,%d,%d)\"/>\n",
            rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(),
            rectangle.getColor()[0], rectangle.getColor()[1], rectangle.getColor()[2]));
          }
          if (shape instanceof Ovals){
            Ovals ovals = (Ovals) shape;
            htmlBuilder.append(String.format(" <ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"rgb(%d,%d,%d)\" />\n",
            ovals.getX() + ovals.getRadiusX(),
            ovals.getY() + ovals.getRadiusY(),
            ovals.getRadiusX(), ovals.getRadiusY(),
            ovals.getColor()[0], ovals.getColor()[1], ovals.getColor()[2]));
          }
        }

      htmlBuilder.append("</svg>\n");

      // Add a div with class "description" for the picture description
      htmlBuilder.append("<div class=\"description\">\n");
      htmlBuilder.append(snapshot.getPicDescription()).append("\n");
      htmlBuilder.append("</div>\n"); // Close the "description" div
      htmlBuilder.append("<hr>\n");
    }

    htmlBuilder.append("</body>\n").append("</html>");

    return htmlBuilder.toString();
  }

  /**
   * Generates HTML file from the model's snapshots and saves it to the given file.
   *
   * @param outputFile The path to the file where the HTML should be saved.
   */
  public void generateHTML(String outputFile) {
    String htmlContent = buildHTML(); //using the buildHTML method to generate html files
    saveToFile(outputFile, htmlContent);//save it to the file
  }


  /**
   * Saves the provided HTML content into a file.
   *
   * @param filename     The path to the file where to save the content.
   * @param htmlContent  The HTML content to write into the file.
   */
  public void saveToFile(String filename, String htmlContent) {
    // Write the htmlContent to a file
    try (PrintWriter out = new PrintWriter(filename)) {
      out.println(htmlContent);
    } catch (FileNotFoundException e) {
      System.err.println("Error saving HTML to file" + e.getMessage());
    }
  }
}
