package edu.weberstate.cs3230.engine;

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
    private static Placement a0v = new Placement('a', 0, 'v');
    private static Placement a0h = new Placement('a', 0, 'h');

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
        grid.placeShip(ship, a0h);

        Assertions.assertEquals(Cell.HitResult.hit, grid.hit(a0));
    }

    @Test
    void grid_returns_alreadymarked_when_alreadyhit()
    {
        Ship ship = new Patrol();
        grid.placeShip(ship, a0h);
        grid.hit(a0);

        Assertions.assertEquals(Cell.HitResult.alreadyMarked, grid.hit(a0));
    }

    @Test
    void grid_returns_sunk_when_sunk()
    {
        Ship ship = new Patrol();
        grid.placeShip(ship, a0h);
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
        Assertions.assertTrue(grid.placeShip(ship, a0h));
        Assertions.assertSame(ship, grid.getShip(a0));
    }

    @Test
    void ship_can_be_placed_on_grid_vertically()
    {
        Ship ship = new Patrol();
        Assertions.assertTrue(grid.placeShip(ship, a0v));
        Assertions.assertSame(ship, grid.getShip(a0));
    }

    @Test
    void ship_spans_horizontal_length()
    {
        Ship ship = new Carrier();
        grid.placeShip(ship, a0h);

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
        grid.placeShip(ship, a0v);

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
        grid.placeShip(carrier, a0h);

        Assertions.assertFalse(grid.placeShip(sub, a0h));
        Assertions.assertFalse(grid.placeShip(sub, new Placement('a', 4, 'h')));
    }

    @Test
    void ship_cannot_interfere_with_existing_ship_horizontally()
    {
        Ship patrol = new Patrol();
        Ship carrier = new Carrier();

        grid.placeShip(patrol, new Placement('a', 2, 'h'));

        // Ensure ship was not placed
        Assertions.assertFalse(grid.placeShip(carrier, a0h));

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

        grid.placeShip(patrol, new Placement('c', 0, 'v'));

        // Ensure ship was not placed
        Assertions.assertFalse(grid.placeShip(carrier, a0v));

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

        Assertions.assertFalse(grid.placeShip(carrier, new Placement('@', 0, 'h')));
        Assertions.assertFalse(grid.placeShip(carrier, new Placement('L', 0, 'h')));
        Assertions.assertFalse(grid.placeShip(carrier, new Placement('a', -1, 'v')));
        Assertions.assertFalse(grid.placeShip(carrier, new Placement('a', 11, 'v')));
    }

    @Test
    void ship_cannot_be_placed_one_over_end_of_grid()
    {
        Ship carrier = new Carrier();

        Assertions.assertFalse(grid.placeShip(carrier, new Placement('a', 10, 'h')));
        Assertions.assertFalse(grid.placeShip(carrier, new Placement('k', 0, 'v')));
    }

    @Test
    void ship_end_cannot_extend_past_grid()
    {
        Ship carrier = new Carrier();

        Assertions.assertFalse(grid.placeShip(carrier, new Placement('a', 7, 'h')));
        Assertions.assertFalse(grid.placeShip(carrier, new Placement('h', 0, 'v')));
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

    @Test
    void copy_of_internal_grid_can_be_retrieved()
    {
        Ship patrol = new Patrol();
        grid.placeShip(patrol, a0h);

        Assertions.assertTrue(grid.getGrid() instanceof Cell[][]);
    }

    @Test
    void copy_of_internal_grid_is_correct()
    {
        Ship patrol = new Patrol();
        Ship carrier = new Carrier();

        patrol.hit();

        grid.placeShip(patrol, new Placement('a', 1, 'h'));
        grid.placeShip(carrier, a0v);

        Cell[][] cells = grid.getGrid();

        Assertions.assertTrue(cells[0][1].getShip() instanceof Patrol);
        Assertions.assertTrue(cells[0][2].getShip() instanceof Patrol);
        Assertions.assertNull(cells[0][3].getShip());
        Assertions.assertEquals(cells[0][1].getShip().hit(), Ship.ShipState.sunk);

        Assertions.assertTrue(cells[0][0].getShip() instanceof Carrier);
        Assertions.assertTrue(cells[1][0].getShip() instanceof Carrier);
        Assertions.assertEquals(cells[0][0].getShip().hit(), Ship.ShipState.floating);
    }

    @Test
    void copy_of_grid_is_deep_copy_of_internal_grid()
    {
        Ship patrol = new Patrol();
        grid.placeShip(patrol, a0h);

        // Hit patrol from copy of grid.
        Cell[][] cells = grid.getGrid();
        cells[0][0].hit();

        // Verify that internal patrol has not been hit
        Assertions.assertEquals(0, patrol.getHits());

        // Hit a cell in cells
        cells[1][1].hit();

        // Verify that cell hasn't already been hit in grid
        Assertions.assertNotEquals(Cell.HitResult.alreadyMarked, grid.hit(new Coordinate('b', 1)));
    }
}