public class TestPlanet {
  public static void main(String[] args) {
    checkPlanet();
  }

  private static void checkPlanet() {
    Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
    Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");

    checkEquals(p1.calDistance(p2), 1.0);
  }

  private static void checkEquals(dobule actual, double expected) {
    System.out.println("Expected: " + expected + " Actual: " + actual);
  }
}
