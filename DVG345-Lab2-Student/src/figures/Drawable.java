package figures;
import rendering.DrawingContext;
/** Förmågan att rita utan att ändra objektets tillstånd. */
public interface Drawable
  {
  void draw(DrawingContext context);
  }
