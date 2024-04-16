package album.model.shape;


public class Ovals extends AbstractShape {
  private String id;
  private int radiusX, radiusY;// Radii along the X and Y axes
  private boolean visible = true;//default to visible

  public Ovals(String id, int x, int y, int radiusX, int radiusY, int r, int g, int b) {
    this.id = id; //set the id
    this.x = x;
    this.y = y;
    this.radiusX = radiusX;
    this.radiusY = radiusY;
    this.color = new int[] {r, g, b};
  }

  @Override
  public void resize(double widthFactor, double heightFactor) {
    this.radiusX *= widthFactor;
    this.radiusY *= heightFactor;
  }

  @Override
  public String getDescription() {
    return  String.format("Oval at center (%d, %d) with radiusX %d and radiusY %d and color RGB(%d, %d, %d)",
            x, y, radiusX, radiusY, color[0], color[1], color[2]);
  }

  public int getX() {
    return this.x; // Assuming 'x' is a field in AbstractShape
  }

  public int getY() {
    return this.y; // Assuming 'y' is a field in AbstractShape
  }

  public void setX(int newX) {
    this.x = newX;
  }

  @Override
  public void setY(int newY) {
    this.y = newY;

  }
  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;

  }

  @Override
  public boolean isVisible() {
    return this.visible;
  }

  public int[] getColor() {
    return this.color; // Assuming 'color' is a field in AbstractShape
  }

  public int getRadiusX() {
    return this.radiusX; // 'radius' is a field in Circle
  }

  public int getRadiusY() {
    return this.radiusY;
  }

  @Override
  public String getId() {
    return id;
  }



}
