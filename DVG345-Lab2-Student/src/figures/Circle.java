package figures;

import java.util.Objects;

import geometry.Point2;
import rendering.DrawingContext;

/** STUDENTUPPGIFT: en cirkel med centrum och positiv, ändlig radie. */
public final class Circle implements Figure {
	// TODO: välj instansvariabler som beskriver cirkelns geometri.
	private final double radius;
	private Point2 center;

	/** Skapar cirkeln. Null och ogiltig radie ska avvisas. */
	public Circle(Point2 center, double radius) {
		// checkar center ej null
		this.center = Objects.requireNonNull(center, "Circle center");

		// checkar om bra radius
		if (radius <= 0) {
			throw new IllegalArgumentException("större än noll!");
		}
		// checkar om infinite eller nan
		if (!Double.isFinite(radius)) {
			throw new IllegalArgumentException("måste vara vanligt double");
		}
		this.radius = radius;		
	}

	@Override
	public void draw(DrawingContext context) {
		
		context.circle(center, radius);
		
	}

	@Override
	public Point2 getCenter() {
		return new Point2(center.x(), center.y());
	}
	@Override
	public void move(double dx, double dy) {
		//translated point2.x.y blir pont2.x+dx.y+dy;
	    Point2 newCenter = center.translated(dx, dy);

	    if (!Double.isFinite(newCenter.x()) ||
	        !Double.isFinite(newCenter.y())) {
	        throw new IllegalArgumentException();
	    }
	    
	    center = newCenter;
	}
}
