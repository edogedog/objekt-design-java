package grading;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import figures.Figure;
import figures.Square;
import geometry.Point2;
import java.util.List;

/** Prövar det publika kontraktet, utan krav på arv eller intern representation. */
@Timeout(5)
public class SquareTest
  {
  @Test @DisplayName("Kvadrat: fyra lika sidor vid rätt hörn, valfri ritordning")
  void squareGeometry()
    {
    Point2 a = new Point2(-2.5, 3.25);
    Point2 b = new Point2(5, 3.25);
    Point2 c = new Point2(5, 10.75);
    Point2 d = new Point2(-2.5, 10.75);
    Figure square = new Square(a, 7.5);
    Support.assertDrawing(square, List.of(
        new Support.Stroke(a, b), new Support.Stroke(b, c),
        new Support.Stroke(c, d), new Support.Stroke(d, a)));
    }

  @Test @DisplayName("Kvadrat: upprepad ritning ändrar inte geometrin")
  void drawIsStable()
    {
    Figure square = new Square(new Point2(10, 20), 4);
    var before = Support.drawing(square);
    Support.assertDrawing(square, before);
    Support.assertDrawing(square, before);
    }

  @Test @DisplayName("Kvadrat: null-hörn avvisas")
  void nullCorner()
    {
    assertThrows(NullPointerException.class, () -> new Square(null, 2));
    }

  @Test @DisplayName("Kvadrat: noll, negativa och icke ändliga sidlängder avvisas")
  void invalidSide()
    {
    for (double side : new double[] {0, -1, Double.NaN,
        Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
      assertThrows(IllegalArgumentException.class,
          () -> new Square(new Point2(1, 2), side), "Sidlängden " + side + " ska avvisas");
    }

  @Test @DisplayName("Kvadrat: överflöd i x- eller y-hörn avvisas")
  void overflowingCorners()
    {
    assertAll(
        () -> assertThrows(IllegalArgumentException.class,
            () -> new Square(new Point2(Double.MAX_VALUE, 0), Double.MAX_VALUE)),
        () -> assertThrows(IllegalArgumentException.class,
            () -> new Square(new Point2(0, Double.MAX_VALUE), Double.MAX_VALUE)));
    }
  }
