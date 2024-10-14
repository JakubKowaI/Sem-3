package org.example;

public class Square {
  final private String color;

  public Square(final String color) {
    this.color = color;
  }

  public void draw() {
    System.out.println("Square is " + color);
  }
}
