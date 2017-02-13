package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/13/17.
 */
class GridTest {
    private Grid grid;
    private static int DEFAULT_SIZE = 10;
    private static Coordinate a0 = new Coordinate('a', 0);

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
    void grid_returns_miss_when_missed()
    {
        Cell.HitResult result = grid.hit(a0);

        Assertions.assertEquals(Cell.HitResult.miss, result);
    }

    @Test
    void grid_returns_hit_when_hit()
    {
        Ship ship = new Patrol();
        grid.placeShip(ship, a0, 'h');

        Assertions.assertEquals(Cell.HitResult.hit, grid.hit(a0));
    }

    @Test
    void grid_returns_alreadymarked_when_alreadyhit()
    {
        Ship ship = new Patrol();
        grid.placeShip(ship, a0, 'h');
        grid.hit(a0);

        Assertions.assertEquals(Cell.HitResult.alreadyMarked, grid.hit(a0));
    }

    @Test
    void grid_returns_sunk_when_sunk()
    {
        Ship ship = new Patrol();
        grid.placeShip(ship, a0, 'h');
        grid.hit(a0);

        Assertions.assertEquals(Cell.HitResult.sunk, grid.hit(new Coordinate('a', 1)));
    }

    @Test
    void grid_cannot_be_hit_off_the_grid()
    {
        Assertions.assertNull(grid.hit(new Coordinate('@', 0)));
        Assertions.assertNull(grid.hit(new Coordinate('L', 0)));
        Assertions.assertNull(grid.hit(new Coordinate('a', -1)));
        Assertions.assertNull(grid.hit(new Coordinate('a', 11)));
    }

    @Test
    void grid_cannot_be_hit_one_off_the_grid()
    {
        Assertions.assertNull(grid.hit(new Coordinate('a', 10)));
        Assertions.assertNull(grid.hit(new Coordinate('k', 0)));
    }

    @Test
    void ship_can_be_placed_on_grid_horizontally()
    {
        Ship ship = new Patrol();
        Assertions.assertTrue(grid.placeShip(ship, a0, 'h'));
        Assertions.assertSame(ship, grid.getShip(a0));
    }

    @Test
    void ship_can_be_placed_on_grid_vertically()
    {
        Ship ship = new Patrol();
        Assertions.assertTrue(grid.placeShip(ship, a0, 'v'));
        Assertions.assertSame(ship, grid.getShip(a0));
    }

    @Test
    void ship_spans_horizontal_length()
    {
        Ship ship = new Carrier();
        grid.placeShip(ship, a0, 'h');

        Assertions.assertSame(ship, grid.getShip(a0));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 1)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 2)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 3)));
        Assertions.assertSame(ship, grid.getShip(new Coordinate('a', 4)));
    }

    @Test
    void ship_spans_vertical_length()
    {
        Ship ship = new Carrier();
        grid.placeShip(ship, a0, 'v');

        Assertions.assertSame(ship, grid.getShip(a0));
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
        grid.placeShip(carrier, a0, 'h');

        Assertions.assertFalse(grid.placeShip(sub, a0, 'h'));
        Assertions.assertFalse(grid.placeShip(sub, new Coordinate('a', 4), 'h'));
    }

    @Test
    void ship_cannot_interfere_with_existing_ship_horizontally()
    {
        Ship patrol = new Patrol();
        Ship carrier = new Carrier();

        grid.placeShip(patrol, new Coordinate('a', 2), 'h');

        // Ensure ship was not placed
        Assertions.assertFalse(grid.placeShip(carrier, a0, 'h'));

        // Ensure original ship is still in place
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('a', 2)));
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('a', 3)));

        // Ensure no portion of second ship was placed
        Assertions.assertNull(grid.getShip(a0));
        Assertions.assertNull(grid.getShip(new Coordinate('a', 1)));
    }

    @Test
    void ship_cannot_interfere_with_existing_ship_vertically()
    {
        Ship patrol = new Patrol();
        Ship carrier = new Carrier();

        grid.placeShip(patrol, new Coordinate('c', 0), 'v');

        // Ensure ship was not placed
        Assertions.assertFalse(grid.placeShip(carrier, a0, 'v'));

        // Ensure original ship is still in place
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('c', 0)));
        Assertions.assertSame(patrol, grid.getShip(new Coordinate('d', 0)));

        // Ensure no portion of second ship was placed
        Assertions.assertNull(grid.getShip(a0));
        Assertions.assertNull(grid.getShip(new Coordinate('b', 0)));
    }

    @Test
    void ship_starting_point_cannot_be_placed_off_grid()
    {
        Ship carrier = new Carrier();

        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('@', 0), 'h'));
        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('L', 0), 'h'));
        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('a', -1), 'v'));
        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('a', 11), 'v'));
    }

    @Test
    void ship_cannot_be_placed_one_over_end_of_grid()
    {
        Ship carrier = new Carrier();

        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('a', 10), 'h'));
        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('k', 0), 'v'));
    }

    @Test
    void ship_end_cannot_extend_past_grid()
    {
        Ship carrier = new Carrier();

        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('a', 7), 'h'));
        Assertions.assertFalse(grid.placeShip(carrier, new Coordinate('h', 0), 'v'));
    }

    @Test
    void ship_cannot_be_retrieved_from_off_grid()
    {
        Assertions.assertNull(grid.getShip(new Coordinate('@', 0)));
        Assertions.assertNull(grid.getShip(new Coordinate('L', 0)));
        Assertions.assertNull(grid.getShip(new Coordinate('a', -1)));
        Assertions.assertNull(grid.getShip(new Coordinate('a', 11)));
    }

    @Test
    void ship_cannot_be_retrieved_from_one_off_grid()
    {
        Assertions.assertNull(grid.getShip(new Coordinate('a', 10)));
        Assertions.assertNull(grid.getShip(new Coordinate('k', 0)));
    }

}