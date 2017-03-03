package ru.job4j.condition;

/**
 * Triangle class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 03.03.17
 */
public class Triangle {
    /**
     * Point a coordinate.
     */
    private Point aPoint;
    /**
     * Point b coordinate.
     */
    private Point bPoint;
    /**
     * Point c coordinate.
     */
    private Point cPoint;
    /**
     * Constructor.
     * @param aPoint point A
     * @param bPoint point B
     * @param cPoint point C
     */
    public Triangle(Point aPoint, Point bPoint, Point cPoint) {
        this.aPoint = aPoint;
        this.bPoint = bPoint;
        this.cPoint = cPoint;
    }

    /**
     * Calculate triangle area.
     * @return area value
     */
    public double area() {
        double aLength = getEdgeLength(aPoint, bPoint);
        double bLength = getEdgeLength(bPoint, cPoint);
        double cLength = getEdgeLength(cPoint, aPoint);
        double pValue = (aLength + bLength + cLength) / 2;
        return Math.sqrt(pValue * (pValue - aLength) * (pValue - bLength) * (pValue - cLength));
    }

    /**
     * Calculate edge length.
     * @param aPoint start edge point coordinate
     * @param bPoint finish edge point coordinate
     * @return edge length
     */
    private double getEdgeLength(Point aPoint, Point bPoint) {
        return Math.sqrt(getDiff(bPoint.getX(), aPoint.getX()) + getDiff(bPoint.getY(), aPoint.getY()));
    }

    /**
     * Calculate difference between two coordinates.
     * @param a value
     * @param b value
     * @return (a minus b)^2
     */
    private double getDiff(int a, int b) {
        return Math.pow((double) (a - b), 2);
    }

    /**
     * Test that triangle is valid.
     * @return boolean
     */
    public boolean isValid() {
        return area() > 0.01;
    }
}
