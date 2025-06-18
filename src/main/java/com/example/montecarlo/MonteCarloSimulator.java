package com.example.montecarlo;

import com.example.montecarlo.geometry.Point;
import com.example.montecarlo.geometry.Shape;

import java.util.Random;

public class MonteCarloSimulator {
    private final double minX, maxX, minY, maxY;
    private final Shape shape;
    private final Random rnd = new Random();

    public MonteCarloSimulator(double minX, double maxX, double minY, double maxY, Shape shape) {
        this.minX = minX; this.maxX = maxX;
        this.minY = minY; this.maxY = maxY;
        this.shape = shape;
    }

    public double estimateArea(int n) {
        int inside = 0;
        for (int i = 0; i < n; i++) {
            double x = minX + rnd.nextDouble() * (maxX - minX);
            double y = minY + rnd.nextDouble() * (maxY - minY);
            if (shape.contains(new Point(x, y))) inside++;
        }
        double rectArea = (maxX - minX) * (maxY - minY);
        return rectArea * ((double) inside / n);
    }
}
