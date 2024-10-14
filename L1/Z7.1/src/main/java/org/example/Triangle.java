package org.example;

public class Triangle {
  final private String color;

  public Triangle(final String color) {
    this.color = color;
  }

  public void draw() {
    System.out.println("Triangle is " + color);
  }
}
