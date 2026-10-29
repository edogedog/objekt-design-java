package tools;
import geometry.Point2;
import rendering.DrawingContext;

/** Aktivt verktyg tolkar musgester. Inga AWT-händelser i kontraktet. */
public interface Tool
  {
  void pressed(Point2 point, boolean extend);
  void dragged(Point2 point);
  void released(Point2 point);
  void cancel();
  void drawPreview(DrawingContext context);
  }
