public class Planet {
  public double xxPos; // Current x position
  public double yyPos; // Current y position
  public double xxVel; // Current velocity in the x direction
  public double yyVel; // Current velocity in the y direction
  public double mass; // Its mass
  public String imgFileName; // The name of the file that corresponds to the image that depicts the planet

  /** constructor */
  public Planet(double xP, double yP, double xV,
                double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double dx = xxPos - p.xxPos;
    double dy = yyPos - p.yyPos;
    double r = Math.sqrt(dx * dx + dy * dy);
    return r;
  }

  public double calcForceExertedBy(Planet p) {
    double g = 6.67e-11;
    double r = calcDistance(p);
    double f = g * mass * p.mass / (r * r);
    return f;
  }

  public double calcForceExertedByX(Planet p) {
    double f = calcForceExertedBy(p);
    double r = calcDistance(p);
    double fx = f * (p.xxPos - xxPos) / r;
    return fx;
  }

  public double calcForceExertedByY(Planet p) {
    double f = calcForceExertedBy(p);
    double r = calcDistance(p);
    double fy = f * (p.yyPos - yyPos) / r;
    return fy;
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    double net = 0;
    for (Planet p : planets) {
      if (!p.equals(this)) {
        net += calcForceExertedByX(p);
      }
    }
    return net;
  }

  public double calcNetForceExertedByY(Planet[] planets) {
    double net = 0;
    for (Planet p : planets) {
      if (!p.equals(this)) {
        net += calcForceExertedByY(p);
      }
    }
    return net;
  }

  public void update(double dt, double xForce, double yForce) {
    double ax = xForce / mass;
    double ay = yForce / mass;

    xxVel += dt * ax;
    yyVel += dt * ay;

    xxPos += dt * xxVel;
    yyPos += dt * yyVel;
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
