package com.example.montecarlo.config;

import org.ini4j.Wini;
import com.example.montecarlo.geometry.Point;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {
    private final Wini ini;
    public ConfigReader(String path) throws Exception {
        this.ini = new Wini(new File(path));
    }
    public double getDouble(String section, String option) {
        return ini.get(section, option, double.class);
    }
    public List<Point> getPoints(String section, String option) {
        String s = ini.get(section, option);
        String[] pairs = s.split(";");
        List<Point> list = new ArrayList<>();
        for (String p : pairs) {
            String[] xy = p.trim().split(",");
            list.add(new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1])));
        }
        return list;
    }
}
