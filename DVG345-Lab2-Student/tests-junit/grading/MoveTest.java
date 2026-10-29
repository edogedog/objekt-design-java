package grading;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import figures.*;
import geometry.Point2;
import java.util.List;

@Timeout(5)
public class MoveTest
  {
  private void checkMove(Figure figure)
    {
    var before = Support.drawing(figure);
    var expected = new java.util.ArrayList<Object>();
    for (Object op : before)
      if (op instanceof Support.Ring ring)
        expected.add(new Support.Ring(ring.center().translated(0.5, -2.25), ring.radius()));
      else
        {
        var stroke = (Support.Stroke) op;
        expected.add(new Support.Stroke(stroke.start().translated(0.5, -2.25),
            stroke.end().translated(0.5, -2.25)));
        }
    figure.move(0.5, -2.25);
    Support.assertDrawing(figure, expected);
    figure.move(-0.5, 2.25);
    Support.assertDrawing(figure, before);
    }

  @Test @DisplayName("Linje: båda ändpunkterna flyttas")
  void lineMove()
    {
    checkMove(new Line(new Point2(2, 3), new Point2(20, 30)));
    }

  @Test @DisplayName("Cirkel: centrum flyttas, radien bevaras")
  void circleMove()
    {
    checkMove(new Circle(new Point2(2, 3), 5));
    }

  @Test @DisplayName("Rektangel: hela konturen flyttas")
  void rectangleMove()
    {
    checkMove(new Rectangle(new Point2(2, 3), 5, 9));
    }

  @Test @DisplayName("Kvadrat: flyttning bevarar fyra lika sidor och storleken")
  void squareMove()
    {
    checkMove(new Square(new Point2(2, 3), 5));
    }

  @Test @DisplayName("Kvadrat: överflöd lämnar hela geometrin orörd")
  void atomicSquareMove()
    {
    Figure square = new Square(new Point2(0, 0), Double.MAX_VALUE);
    var before = Support.drawing(square);
    assertThrows(IllegalArgumentException.class, () -> square.move(Double.MAX_VALUE, 0));
    Support.assertDrawing(square, before);
    }

  @Test @DisplayName("Felaktig flyttning ändrar inte figuren")
  void invalidMove()
    {
    for (Figure figure : List.of(new Circle(new Point2(2, 3), 5),
        new Rectangle(new Point2(2, 3), 5, 9), new Square(new Point2(2, 3), 5),
        new Line(new Point2(2, 3), new Point2(4, 5))))
      {
      for (double invalid : new double[] {
          Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY })
        {
        var before = Support.drawing(figure);
        assertThrows(IllegalArgumentException.class, () -> figure.move(invalid, 2));
        Support.assertDrawing(figure, before);
        assertThrows(IllegalArgumentException.class, () -> figure.move(2, invalid));
        Support.assertDrawing(figure, before);
        }
      }
    }

  @Test @DisplayName("Överflöd i linjens andra punkt lämnar båda punkterna orörda")
  void atomicMove()
    {
    var line = new Line(new Point2(0, 0), new Point2(Double.MAX_VALUE, 1));
    var before = Support.drawing(line);
    assertThrows(IllegalArgumentException.class, () -> line.move(Double.MAX_VALUE, 0));
    Support.assertDrawing(line, before);
    }

  }
