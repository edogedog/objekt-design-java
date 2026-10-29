package geometry;
import java.util.Objects;

/** Oföränderligt koordinatvärde, y växer nedåt på ritytan. */
public record Point2(double x, double y) {
	public Point2 {
		if (!Double.isFinite(x) || !Double.isFinite(y))
			throw new IllegalArgumentException("Koordinater måste vara ändliga.");
	}

	/** Euklidiskt avstånd; punkterna ändras inte. Null avvisas. */
	public double distance(Point2 other) {

		Objects.requireNonNull(other);
		return Math.hypot(x - other.x(), y - other.y());

	}

	public Point2 translated(double dx, double dy) {
		return new Point2(x + dx, y + dy);
	}

	/** Härlett mittvärde utan överflöd vid addition av stora koordinater. */
	public Point2 midpoint(Point2 other) {
		java.util.Objects.requireNonNull(other, "other");
		return new Point2(x / 2 + other.x / 2, y / 2 + other.y / 2);
	}
}
