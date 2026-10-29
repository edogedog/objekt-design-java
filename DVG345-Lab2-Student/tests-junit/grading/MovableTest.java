package grading;
import figures.*;
import geometry.Point2;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(5)
public class MovableTest
  {
  @Test @DisplayName("Figure utökar Movable, vars move är abstrakt och returnerar void")
  void contract() throws Exception
    {
    assertTrue(Movable.class.isAssignableFrom(Figure.class));
    var method = Movable.class.getMethod("move", double.class, double.class);
    assertEquals(void.class, method.getReturnType());
    assertFalse(method.isDefault());
    }

  @Test @DisplayName("Centrum följer flyttningen utan att ett gammalt Point2-värde ändras")
  void centerFollowsMove()
    {
    for (Figure f : List.of(new Line(new Point2(2, 4), new Point2(10, 16)),
        new Circle(new Point2(2, 4), 8), new Rectangle(new Point2(2, 4), 6, 10),
        new Square(new Point2(2, 4), 6)))
      {
      Point2 old = f.getCenter();
      double x = old.x(), y = old.y();
      f.move(3, -2);
      assertEquals(x + 3, f.getCenter().x(), 1e-8);
      assertEquals(y - 2, f.getCenter().y(), 1e-8);
      assertEquals(x, old.x());
      assertEquals(y, old.y());
      }
    }
  }
