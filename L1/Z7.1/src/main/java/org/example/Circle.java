package org.example;

public class Circle {
  final private String color;

  public Circle(final String color) {
    this.color = color;
  }

  public void draw() {
    System.out.println("Circle is " + color);
  }
}
