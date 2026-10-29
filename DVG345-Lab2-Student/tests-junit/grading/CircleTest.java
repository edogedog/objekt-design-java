package grading;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import figures.*;
import geometry.Point2;
import java.util.List;

@Timeout(5)
public class CircleTest
  {
  @Test @DisplayName("Cirkel: centrum och radie skickas till ritporten")
  void circleGeometry()
    {
    var circle = new Circle(new Point2(12.5, 24.25), 3.5);
    Support.assertDrawing(circle, List.of(new Support.Ring(new Point2(12.5, 24.25), 3.5)));
    }

  @Test @DisplayName("Cirkel: upprepad ritning ändrar inte geometrin")
  void circleDrawIsStable()
    {
    var circle = new Circle(new Point2(10, 20), 5);
    var before = Support.drawing(circle);
    Support.assertDrawing(circle, before);
    Support.assertDrawing(circle, before);
    }

  @Test @DisplayName("Cirkel: ogiltig radie avvisas")
  void invalidRadius()
    {
    for (double value : new double[] {0, -1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
      assertThrows(IllegalArgumentException.class, () -> new Circle(new Point2(1, 2), value),
          "Radien " + value + " ska avvisas");
    }

  @Test @DisplayName("Cirkel: null-centrum avvisas")
  void nullCenter()
    {
    assertThrows(NullPointerException.class, () -> new Circle(null, 2));
    }

  }
