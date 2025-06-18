package com.example.montecarlo.geometry;

import java.awt.geom.Path2D;
import java.util.List;

public class PolygonShape implements Shape, Comparable<PolygonShape> {
    private final Path2D.Double path;
    private final double area;

    public PolygonShape(List<Point> vertices) {
        path = new Path2D.Double();
        Point first = vertices.get(0);
        path.moveTo(first.x, first.y);
        for (int i = 1; i < vertices.size(); i++) {
            Point p = vertices.get(i);
            path.lineTo(p.x, p.y);
        }
        path.closePath();
        area = Math.abs(computeArea(vertices));
    }

    private double computeArea(List<Point> v) {
        double sum = 0;
        int n = v.size();
        for (int i = 0; i < n; i++) {
            Point a = v.get(i);
            Point b = v.get((i + 1) % n);
            sum += a.x * b.y - b.x * a.y;
        }
        return sum / 2.0;
    }

    @Override
    public double exactArea() {
        return area;
    }

    @Override
    public boolean contains(Point p) {
        return path.contains(p.x, p.y);
    }

    @Override
    public int compareTo(PolygonShape other) {
        return Double.compare(this.exactArea(), other.exactArea());
    }
}
