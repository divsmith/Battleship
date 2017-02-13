package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by parker on 2/13/17.
 */
class GridTest {
    private Grid grid;
    private static int DEFAULT_SIZE = 10;

    @BeforeEach
    void setUp() {
        grid = new Grid();
    }

    @Test
    void default_size_is_correct()
    {
        Assertions.assertEquals(DEFAULT_SIZE, grid.getSize());
    }

    @Test
    void size_can_be_set_via_constructor()
    {
        grid = new Grid(20);
        Assertions.assertEquals(20, grid.getSize());
    }

    @Test
    void ship_can_be_placed_on_grid_horizontally()
    {
        Coordinate coord = new Coordinate('a', 0);
        Ship ship = new Patrol();
        Assertions.assertTrue(grid.placeShip(ship, coord, 'h'));
        Assertions.assertSame(ship, grid.getShip(coord));
    }

    @Test
    void ship_can_be_placed_on_grid_vertically()
    {
        Coordinate coord = new Coordinate('a', 0);
        Ship ship = new Patrol();
        Assertions.assertTrue(grid.placeShip(ship, coord, 'v'));
        Assertions.assertSame(ship, grid.getShip(coord));
    }

    @Test
    void ship_spans_horizontal_length()
    {
        Ship ship = new Carrier();
        grid.placeShip(ship, new Coordinate('a', 0), 'h');

        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 0)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 1)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 2)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 3)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 4)));
    }

    @Test
    void ship_spans_vertical_length()
    {
        Ship ship = new Carrier();
        grid.placeShip(ship, new Coordinate('a', 0), 'v');

        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 0)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('b', 0)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('c', 0)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('d', 0)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('e', 0)));
    }

    @Test
    void ship_cannot_be_placed_on_same_cell_as_existing_ship()
    {
        Ship carrier = new Carrier();
        Ship sub = new Submarine();
        grid.placeShip(carrier, new Coordinate('a', 0), 'h');

        Assertions.assertFalse(grid.placeShip(sub, new Coordinate('a', 0), 'h'));
        Assertions.assertFalse(grid.placeShip(sub, new Coordinate('a', 4), 'h'));
    }

    @Test
    void ship_cannot_interfere_with_existing_ship_horizontally()
    {
        Ship patrol = new Patrol();
        Ship carrier = new Carrier();

        grid.placeShip(patrol, new Coordinate('a', 2), 'h');

        // Ensure ship was not placed
        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('a', 0), 'h'));

        // Ensure original ship is still in place
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('a', 2)));
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('a', 3)));

        // Ensure no portion of second ship was placed
        Assertions.assertNull(grid.getShip(new Coordinate('a', 0)));
        Assertions.assertNull(grid.getShip(new Coordinate('a', 1)));
    }

    @Test
    void ship_cannot_interfere_with_existing_ship_vertically()
    {
        Ship patrol = new Patrol();
        Ship carrier = new Carrier();

        grid.placeShip(patrol, new Coordinate('c', 0), 'v');

        // Ensure ship was not placed
        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('a', 0), 'v'));

        // Ensure original ship is still in place
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('c', 0)));
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('d', 0)));

        // Ensure no portion of second ship was placed
        Assertions.assertNull(grid.getShip(new Coordinate('a', 0)));
        Assertions.assertNull(grid.getShip(new Coordinate('b', 0)));
    }

}