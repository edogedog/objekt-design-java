package app;

import figures.Figure;
import figures.Line;
import geometry.Point2;
import java.util.List;

/** Här sätter gruppen ihop sin första hårdkodade ritning. */
public final class DemoContent
  {
  private DemoContent()
    {
    }

  public static List<Figure> create()
    {
    // List.of skapar här en lista som inte kan ändras.
    // Lägg till egna cirklar, rektanglar och kvadrater när de är implementerade.
    return List.of(
        new Line(new Point2(70, 80), new Point2(300, 80)),
        new Line(new Point2(70, 80), new Point2(70, 260)));
    }
  }
