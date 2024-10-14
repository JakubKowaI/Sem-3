package org.example;

public class Main {
  public static void main(final String[] args) {
    try {
      final String type = args[0];
      if ("triangle".equals(type)) {
        final Triangle triangle = new Triangle(args[1]);
        triangle.draw();
      } else if ("square".equals(type)) {
        final Square square = new Square(args[1]);
        square.draw();
      } else if ("circle".equals(type)) {
        final Circle circle = new Circle(args[1]);
        circle.draw();
      } else {
        System.out.println("Unknown shape");
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Unknown shape");
    }
  }
}