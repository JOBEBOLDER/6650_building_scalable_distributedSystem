package album.model.shape;


/**
 * AbstractShape provides a foundational implementation of the IShape interface,
 * encapsulating common properties and behaviors for shapes such as position and color.
 * Concrete shape classes should extend AbstractShape and implement the abstract methods.
 */
public abstract class AbstractShape implements IShape {
  protected int x,y; // general shape position features
  protected int[] color; //general color feature

  /**
   * Moves the shape by the specified deltas in the x and y directions.
   *
   * @param deltaX The change in the x-coordinate.
   * @param deltaY The change in the y-coordinate.
   */
  @Override
  public void move(int deltaX, int deltaY) {
    this.x += deltaX;
    this.y +=deltaY;
  }


  @Override
  public abstract void resize(double factorWidth, double factorHeight);


  /**
   * Changes the color of the shape to the specified RGB values.
   *
   * @param r The red component of the new color (0-1 range).
   * @param g The green component of the new color (0-1 range).
   * @param b The blue component of the new color (0-1 range).
   */
  public void changeColor(int r, int g, int b) {
    this.color = new int[] {r, g, b};

  }


  /**
   * Abstract method to get a textual description of the shape. Concrete implementations
   * must provide a description that includes relevant properties such as position, dimensions, and color.
   *
   * @return A string describing the shape.
   */
  @Override
  public abstract String getDescription();

  public abstract int getX();
  public abstract int getY();


  @Override
  public AbstractShape clone() { // Make sure it's public and returns AbstractShape
    try {
      return (AbstractShape) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(); // This should never happen since we are Cloneable
    }
  }
}
