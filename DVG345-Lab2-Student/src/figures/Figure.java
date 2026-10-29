package figures;
import geometry.Point2;
/** Lab2 utökar figurkontraktet med flyttförmåga. */
public interface Figure extends Drawable, Movable
  {
  Point2 getCenter();
  }
