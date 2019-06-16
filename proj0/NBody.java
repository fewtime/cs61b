public class NBody {
  // read radius from file
  public static double readRadius(String filename) {
    In in = new In(filename);

    int N = in.readInt();
    double R = in.readDouble();

    return R;
  }

  // read plants
  public static Planet[] readPlanets(String filename) {
    In in = new In(filename);

    int N = in.readInt();
    double R = in.readDouble();

    Planet[] planets = new Planet[N];

    for (int i = 0; i < N; ++i) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();

      Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
      planets[i] = p;
    }

    return planets;
  }

  public static void main(String[] args) {
    // collecting all needed input
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Planet[] planets = readPlanets(filename);

    // drawing the background
    double radius = readRadius(filename);
    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0, 0, "images/starfield.jpg");

    // drawing all planets
    for (Planet p : planets) {
      p.draw();
    }

    // creating an animation
    StdDraw.enableDoubleBuffering();
    double time = 0;
    while (time < T) {
      double[] xForce = new double[planets.length];
      double[] yForce = new double[planets.length];

      for (int i = 0; i < planets.length; ++i) {
        xForce[i] = planets[i].calcNetForceExertedByX(planets);
        yForce[i] = planets[i].calcNetForceExertedByY(planets);
      }

      // update
      for (int i = 0; i < planets.length; ++i) {
        planets[i].update(dt, xForce[i], yForce[i]);
      }

      // draw background
      StdDraw.picture(0, 0, "images/starfield.jpg");

      // draw planets
      for (Planet p : planets) {
        p.draw();
      }

      StdDraw.show();
      StdDraw.pause(10);

      time += dt;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
