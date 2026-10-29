package figures;

import java.util.Objects;

import geometry.Point2;
import rendering.DrawingContext;

/**
 * STUDENTUPPGIFT: axelparallell rektangel, beskriven av övre vänstra hörnet,
 * bredd och höjd. Båda måtten ska vara positiva och ändliga.
 */

public final class Rectangle implements Figure {

	private Point2 topLeft;
	private final double width;
	private final double height;


	/** Skapar rektangeln. Även de beräknade hörnen måste vara ändliga. */
	public Rectangle(Point2 topLeft, double width, double height) {

		this.topLeft = Objects.requireNonNull(topLeft, "rectangle width");

		// checkar om bra width
		if (width <= 0) {
			throw new IllegalArgumentException("större än noll!");
		}
		// checkar om finite
		if (!Double.isFinite(topLeft.x() + width)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		// checkar om finite
		if (!Double.isFinite(width)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		this.width = width;

		// checkar om bra höjd
		if (height <= 0) {
			throw new IllegalArgumentException("större än noll!");
		}
		// checkar om finite
		if (!Double.isFinite(topLeft.y() + height)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		// checkar om finite
		if (!Double.isFinite(height)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		this.height = height;


	}

	@Override
	public void draw(DrawingContext context) {

		Point2 topRight = new Point2(topLeft.x() + width, topLeft.y());
		Point2 bottomLeft = new Point2(topLeft.x(), topLeft.y() + height);
		Point2 bottomright = new Point2(topLeft.x() + width, topLeft.y() + height);

		context.line(topLeft, topRight);
		context.line(topLeft, bottomLeft);
		context.line(bottomLeft, bottomright);
		context.line(topRight, bottomright);

	}

	@Override
	public Point2 getCenter() {
	    return new Point2(topLeft.x() + width / 2,
	                      topLeft.y() + height / 2);
	}

	@Override
	public void move(double dx, double dy) {
		Point2 newPoint = topLeft.translated(dx, dy);
		topLeft = newPoint;

	}
}
