package grading;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import figures.*;

public class EncapsulationTest
  {
  @Test @DisplayName("Cirkel: instansfälten är privata, även i basklasser")
  void circleFieldsArePrivate()
    {
    Support.assertPrivateInstanceFields(Circle.class);
    }

  @Test @DisplayName("Rektangel: instansfälten är privata, även i basklasser")
  void rectangleFieldsArePrivate()
    {
    Support.assertPrivateInstanceFields(Rectangle.class);
    }

  @Test @DisplayName("Kvadrat: instansfälten är privata, även i basklasser")
  void squareFieldsArePrivate()
    {
    Support.assertPrivateInstanceFields(Square.class);
    }

  @Test @DisplayName("Linje: instansfälten är privata, även i basklasser")
  void lineFieldsArePrivate()
    {
    Support.assertPrivateInstanceFields(Line.class);
    }
  }
