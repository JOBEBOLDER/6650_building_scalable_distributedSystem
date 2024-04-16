package album.model.shape;


public class Rectangle extends AbstractShape {
  private String id;
  private int width, height;// Width and height of the rectangle
  private double rotationAngle = 0; // Initial rotation angle
  private boolean visible = true;//default to visible



  public Rectangle(String id, int x, int y, int width, int height, int r, int g, int b) {
    this.id = id;// set the ID
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
    this.color = new int[]{r, g, b};
  }

  @Override
  public void resize(double widthFactor, double heightFactor) {
    // Adjusting the width and height according to the given factor.
    this.width *= widthFactor;
    this.height *= heightFactor;
  }



  @Override
  public String getDescription() {
    return String.format("Rectangle at (%d, %d) with width %d and height %d and color RGB(%d, %d, %d)",
            x, y, width, height, color[0], color[1], color[2]);
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

  public int getX() {
    return this.x; // Assuming 'x' is a field in AbstractShape
  }

  public int getY() {
    return this.y; // Assuming 'y' is a field in AbstractShape
  }

  public int[] getColor() {
    return this.color; // Assuming 'color' is a field in AbstractShape
  }

  public int getWidth() {
    return this.width; // 'radius' is a field in Circle
  }

  public int getHeight() {
    return this.height;
  }


  @Override
  public String getId() {
    return id;
  }

}
