package com.example.montecarlo.geometry;

public interface Shape {
    /** Точное (аналитическое) площадь фигуры (при необходимости). */
    double exactArea();
    /** Проверка попадания точки внутрь фигуры. */
    boolean contains(Point p);
}
