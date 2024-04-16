package album.views;

import album.model.PhotoAlbumModel;
import album.model.shape.Ovals;
import album.model.shape.Rectangle;
import album.views.WebView;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class WebViewTest {

  private static final String TEST_OUTPUT = "test.html";
  private WebView webView;
  private PhotoAlbumModel model;

  @BeforeEach
  void setUp() {
    // Set up your model with any initial data as needed
    model = new PhotoAlbumModel();
    // Consider adding some shapes and snapshots to the model here for testing

    // Initialize WebView with the test model
    webView = new WebView(model, 800, 600);
  }

  @AfterEach
  void tearDown() throws IOException {
    // Clean up after tests
    Files.deleteIfExists(Paths.get(TEST_OUTPUT));
  }

  @Test
  void buildHTMLShouldReturnCorrectFormat() {
    String html = webView.buildHTML();
    assertNotNull(html);
    assertTrue(html.startsWith("<!DOCTYPE html>"));
    assertTrue(html.contains("<title>Photo Album</title>"));
    // Add more assertions here to validate the HTML structure
  }

  @Test
  void generateHTMLShouldCreateFile() {
    webView.generateHTML(TEST_OUTPUT);

    assertDoesNotThrow(() -> {
      Path path = Paths.get(TEST_OUTPUT);
      assertTrue(Files.exists(path));
      // Read the content of the file to verify it's not empty or has correct content
      String content = Files.readString(path);
      assertNotNull(content);
      assertFalse(content.isEmpty());
    });
  }

  @Test
  void buildHTMLShouldContainHeader() {
    String html = webView.buildHTML();
    assertTrue(html.contains("<h1>Photo Album</h1>"));
  }

  @Test
  void buildHTMLShouldContainDivForSnapshots() {
    // 设置环境
    PhotoAlbumModel model = new PhotoAlbumModel();
    WebView webView = new WebView(model, 800, 600);

    // 添加形状到模型
    model.addShape(new Rectangle("myrect", 200, 200, 50, 100,255, 0, 0));
    model.addShape(new Ovals("myoval", 500, 100, 60, 30, 0, 255, 0));

    // 添加快照到模型
    model.takeSnapshot("Test snapshot");

    // 生成HTML并进行断言测试
    String html = webView.buildHTML();
    assertTrue(html.contains("<div class=\"snapshot\">"));
  }

  @Test
  void buildHTMLShouldContainSVGElements() {
    PhotoAlbumModel model = new PhotoAlbumModel();
    WebView webView = new WebView(model, 800, 600);

    model.addShape(new Rectangle("rect1", 10, 10, 100, 200, 255, 0, 0));
    model.takeSnapshot("Snapshot with rectangle");

    String html = webView.buildHTML();

    assertTrue(html.contains("<svg"));
    assertTrue(html.contains("rect"));
    assertTrue(html.contains("</svg>"));
  }

  @Test
  void buildHTMLShouldContainShapeDescriptions() {
    PhotoAlbumModel model = new PhotoAlbumModel();
    WebView webView = new WebView(model, 800, 600);

    Rectangle rect = new Rectangle("rect1", 10, 10, 100, 200, 255, 0, 0);
    model.addShape(rect);
    model.takeSnapshot("Snapshot with rectangle");

    String html = webView.buildHTML();

    String shapeDescription = rect.getDescription();
    assertTrue(html.contains(shapeDescription));
  }


  @Test
  void generateHTMLShouldWriteExpectedContentToFile() {
    webView.generateHTML(TEST_OUTPUT);

    String expectedContent = "<!DOCTYPE html>";
    Path path = Paths.get(TEST_OUTPUT);
    assertAll("File content",
            () -> assertTrue(Files.exists(path)),
            () -> {
              String content = Files.readString(path);
              assertTrue(content.contains(expectedContent));
            }
    );
  }

  @Test
  void generateHTMLWithNoSnapshotsShouldStillCreateValidFile() {
    webView.generateHTML(TEST_OUTPUT);

    String expectedBeginning = "<!DOCTYPE html>";
    Path path = Paths.get(TEST_OUTPUT);
    assertAll("File content",
            () -> assertTrue(Files.exists(path)),
            () -> {
              String content = Files.readString(path);
              assertTrue(content.startsWith(expectedBeginning));
              // Since no snapshots were added, verify the absence of snapshot specific content
              assertFalse(content.contains("<div class=\"snapshot\">"));
            }
    );
  }


}
