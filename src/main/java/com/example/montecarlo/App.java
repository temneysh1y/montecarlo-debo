package com.example.montecarlo;

import com.example.montecarlo.config.ConfigReader;
import com.example.montecarlo.geometry.Point;
import com.example.montecarlo.geometry.PolygonShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        String configPath = "src/main/resources/config.ini";
        ConfigReader cfg = new ConfigReader(configPath);

        double minX = cfg.getDouble("rectangle", "minX");
        double maxX = cfg.getDouble("rectangle", "maxX");
        double minY = cfg.getDouble("rectangle", "minY");
        double maxY = cfg.getDouble("rectangle", "maxY");

        double midX = (minX + maxX) / 2.0;

        List<Point> debo = List.of(
                new Point(maxX, minY),  // D
                new Point(midX, maxY),  // E
                new Point(minX, maxY),  // B
                new Point(midX, minY)   // O
        );
        PolygonShape shape = new PolygonShape(debo);

        MonteCarloSimulator sim = new MonteCarloSimulator(minX, maxX, minY, maxY, shape);
        double exact = shape.exactArea();

        System.out.printf("%10s%15s%15s%20s%20s%n", "N", "Est. Area", "True Area", "Duration (ms)", "Rel. Error (%)");

        int[] trials = {1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000};
        for (int n : trials) {
            long t0 = System.currentTimeMillis();
            double estimate = sim.estimateArea(n);
            long dt = System.currentTimeMillis() - t0;
            double errorPercent = 100.0 * Math.abs(estimate - exact) / exact;
            System.out.printf("%10d%15.5f%15.5f%20d%20.3f%n", n, estimate, exact, dt, errorPercent);
            logger.info("N={} → estimate={}, exact={}, error={}%, time={}ms", n, estimate, exact, errorPercent, dt);
        }
    }
}
