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
public class Rectangle extends Figure {

    private final double edge1;
    private final double edge2;

    Rectangle(Color color, double edge1, double edge2) {
        super(color);
        this.edge1 = edge1;
        this.edge2 = edge2;
    }

    Rectangle(double edge1, double edge2) {
        super();
        this.edge1 = edge1;
        this.edge2 = edge2;
    }

    @Override
    public double getArea() {
        return edge1 * edge2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (edge1 + edge2);
    }

    public String getDescription() {
        return String.format("%-8s %-16s (%5.2f, %5.2f),\t", "Rechteck", "mit Kantenlängen", edge1, edge2);
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
