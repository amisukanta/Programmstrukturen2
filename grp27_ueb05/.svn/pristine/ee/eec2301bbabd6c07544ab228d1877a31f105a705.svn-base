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
public class Triangle extends Figure {

    private final double edge1;
    private final double edge2;
    private final double edge3;

    Triangle(Color color, double edge1, double edge2, double edge3) {
        super(color);
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
    }

    Triangle(double edge1, double edge2, double edge3) {
        super();
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
    }

    @Override
    public double getArea() {
        double s = this.getPerimeter() / 2;
        return Math.sqrt(s * (s - this.edge1) * (s - this.edge2) * (s - this.edge3)); 
    }

    @Override
    public double getPerimeter() {
        return edge1 + edge2 + edge3;
    }

    public String getDescription() {
        return String.format("%-8s %-16s (%5.2f, %5.2f, %5.2f),\t", "Dreieck", "mit Kantenlängen", edge1, edge2, edge3);
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
