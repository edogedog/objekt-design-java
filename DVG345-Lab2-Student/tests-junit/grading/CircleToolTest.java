package grading;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import figures.*;
import geometry.Point2;
import java.util.List;

@Timeout(5)
public class CircleToolTest
  {
  @Test @DisplayName("Cirkelgest: inget dokumentobjekt före släpp")
  void preview()
    {
    var document = new model.DrawingDocument(List.of());
    var tool = new tools.CircleTool(document);
    tool.pressed(new Point2(10, 10), false);
    tool.dragged(new Point2(13, 14));
    assertTrue(document.figures().isEmpty());
    tool.released(new Point2(13, 14));
    assertEquals(1, document.figures().size());
    Support.assertDrawing(document.figures().get(0), List.of(new Support.Ring(new Point2(10, 10), 5)));
    }

  @Test @DisplayName("Cirkelgest: avbruten gest infogar ingenting")
  void cancel()
    {
    var document = new model.DrawingDocument(List.of());
    var tool = new tools.CircleTool(document);
    tool.pressed(new Point2(10, 10), false);
    tool.dragged(new Point2(20, 20));
    tool.cancel();
    tool.released(new Point2(20, 20));
    assertTrue(document.figures().isEmpty());
    }

  @Test @DisplayName("Cirkelgest: nollradie ignoreras")
  void zeroRadius()
    {
    var document = new model.DrawingDocument(List.of());
    var tool = new tools.CircleTool(document);
    tool.pressed(new Point2(10, 10), false);
    tool.released(new Point2(10, 10));
    assertTrue(document.figures().isEmpty());
    }

  }
