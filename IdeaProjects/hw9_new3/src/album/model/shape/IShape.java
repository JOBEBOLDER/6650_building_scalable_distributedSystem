package album.model.shape;


/**
 * Defines the contract for shapes within the application.
 * This interface outlines basic operations that all shapes must support, including movement,
 * resizing, rotation, color changes, and obtaining a textual description of the shape.
 */
public interface IShape extends Cloneable {

  /**
   * Moves the shape by the specified amounts along the X and Y axes.
   *
   * @param deltaX The amount to move the shape along the X-axis.
   * @param deltaY The amount to move the shape along the Y-axis.
   */
  void move(int deltaX, int deltaY);
  IShape clone();


  void resize(double widthFactor, double heightFactor);


  /**
   * Changes the color of the shape to the specified RGB values. Each component should
   * be in the range of 0 to 1, representing the intensity of the respective color channel.
   *
   * @param r The red component of the new color.
   * @param g The green component of the new color.
   * @param b The blue component of the new color.
   */
  void changeColor(int r, int g, int b);


  /**
   * Generates a textual description of the shape, including details such as its position,
   * size, color, and any other relevant attributes.
   *
   * @return A string describing the shape.
   */
  String getDescription();
  int[] getColor();


  String getId();

  void setX(int newX);

  void setY(int newY);

  void setVisible(boolean visible);

  /**
   * check if the shape is currently visible
   * @return true if the shape is visible ,false otherwise
   */
  boolean isVisible();

  int getX();


}
