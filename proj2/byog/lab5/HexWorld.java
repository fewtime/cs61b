package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        int x;
        int y;

        Position(int pX, int pY) {
            x = pX;
            y = pY;
        }
    }

    /**
     * s   = 3 3 3 3 3 3
     * i   = 0 1 2 3 4 5
     * res = 3 5 7 7 5 3
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return
     */
    public static int hexRowWidth(int s, int i) {
        return s + 2 * -hexRowOffset(s, i);
    }

    /**
     * Computesrelative x coordinate of the leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero. For example, if s = 3, and i = 2, this function
     * returns -2, because the row 2 up from the bottom starts 2 to the left
     * of the start position, e.g.
     *   xxxx
     *  xxxxxx
     * xxxxxxxx
     * xxxxxxxx <-- i = 2, starts 2 spots to the left of the bottom of the hex
     *  xxxxxx
     *   xxxx
     *
     * i   = 0  1  2  3  4 5
     * res = 0 -1 -2 -2 -1 0
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        i = (i >= s ? (2 * s - 1 - i) : i);
        return -i;
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; ++xi) {
            world[p.x + xi][p.y] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; ++yi) {
            int thisRowY = p.y + yi;

            int rowStartX = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(rowStartX, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        initWorld(ter, world);

        tessellate(world, 3);

        ter.renderFrame(world);
    }

    private static void initWorld(TERenderer ter, TETile[][] world) {
        ter.initialize(WIDTH, HEIGHT);

        for (int x = 0; x < WIDTH; ++x) {
            for (int y = 0; y < HEIGHT; ++y) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private static void tessellate(TETile[][] world, int size) {
        // Start at the mid
        Position startPos = new Position(WIDTH / 2 - 1, HEIGHT / 2 - 1);

        drawColsOfHexes(world, size, startPos);
    }

    private static void drawColsOfHexes(TETile[][] world, int s, Position p) {
        Position nextP = p;
        drawRandomVerticalHexes(world, nextP, 3, 3);

        nextP = new Position(nextP.x + 2 * s - 1, nextP.y - s);
        drawRandomVerticalHexes(world, nextP, 4, 3);

        nextP = new Position(nextP.x + 2 * s - 1, nextP.y - s);
        drawRandomVerticalHexes(world, nextP, 5, 3);

        nextP = new Position(nextP.x + 2 * s - 1, nextP.y + s);
        drawRandomVerticalHexes(world, nextP, 4, 3);

        nextP = new Position(nextP.x + 2 * s - 1, nextP.y + s);
        drawRandomVerticalHexes(world, nextP, 3, 3);
    }

    /**
     * @param world the world to draw on
     * @param p the bottom left cooridinate of the first hexagon
     * @param n the number of hexes in this column
     * @param s the size of these hexes
     */
    private static void drawRandomVerticalHexes(TETile[][] world, Position p, int n, int s) {
        Position nextP = p;
        for (int i = 0; i < n; ++i) {
            addHexagon(world, nextP, s, randomTile());
            nextP = new Position(nextP.x, nextP.y + 2 * s);
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            default: return Tileset.NOTHING;
        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }
}
