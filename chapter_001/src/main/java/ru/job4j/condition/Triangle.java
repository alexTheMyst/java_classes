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
        double result = 0d;
        if (aLength < bLength + cLength) {
            double pValue = (aLength + bLength + cLength) / 2;
            result = Math.sqrt(pValue * (pValue - aLength) * (pValue - bLength) * (pValue - cLength));
        }
        return result;
    }

    /**
     * Calculate edge length.
     * @param aPoint start edge point coordinate
     * @param bPoint finish edge point coordinate
     * @return edge length
     */
    private double getEdgeLength(Point aPoint, Point bPoint) {
        return Math.sqrt(Math.pow((double) (bPoint.getX() - aPoint.getX()), 2)
                + Math.pow((double) (bPoint.getY() - aPoint.getY()), 2));
    }
}