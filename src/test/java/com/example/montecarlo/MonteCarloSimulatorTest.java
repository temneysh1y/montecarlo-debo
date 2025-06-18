package com.example.montecarlo;

import com.example.montecarlo.geometry.Point;
import com.example.montecarlo.geometry.PolygonShape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class MonteCarloSimulatorTest {
    @Test
    void testRectangle() {
        // прямоугольник 0≤x≤2, 0≤y≤3 ⇒ площадь 6
        double minX=0, maxX=2, minY=0, maxY=3;
        PolygonShape rect = new PolygonShape(Arrays.asList(
                new Point(0,0), new Point(2,0), new Point(2,3), new Point(0,3)
        ));
        MonteCarloSimulator sim = new MonteCarloSimulator(minX,maxX,minY,maxY,rect);
        double est = sim.estimateArea(1000000);
        assertEquals(6.0, est, 0.05);  // допускаем погрешность ±0.05
    }
    @Test
    void testShapeComparison() {
        PolygonShape small = new PolygonShape(Arrays.asList(
                new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(0, 1)
        ));
        PolygonShape large = new PolygonShape(Arrays.asList(
                new Point(0, 0), new Point(2, 0), new Point(2, 1), new Point(0, 1)
        ));
        assertTrue(large.compareTo(small) > 0);
    }

}
