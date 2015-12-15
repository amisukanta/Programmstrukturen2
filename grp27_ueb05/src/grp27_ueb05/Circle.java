/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp27_ueb05;

/**
 *
 * @author Robin
 */
public class Circle extends Figure {

    private final double diameter;

    Circle(Color color, double diameter) {
        super(color);
        this.diameter = diameter;
    }

    Circle(double diameter) {
        super();
        this.diameter = diameter;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(diameter / 2, 2);
    }

    @Override
    public double getPerimeter() {
        return Math.PI * diameter;
    }

    public String getDescription() {
        return String.format("%-8s %-16s (%5.2f),\t\t", "Kreis", "mit Durchmesser", diameter);
    }

    @Override
    public String toString() {
        if (color != null) {
            return String.format("%10s %s %s", color.toString(), getDescription(), super.toString());
        } else {
            return String.format("%10s %s %s", "farblos", getDescription(), super.toString());
        }
    }
}
