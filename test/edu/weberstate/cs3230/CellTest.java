package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.weberstate.cs3230.Cell.*;

/**
 * Created by parker on 2/12/17.
 */
class CellTest {
    private Cell cell;
    private Ship ship;

    @BeforeEach
    void setUp() {
        cell = new Cell();
        ship = new Patrol();
    }

    @Test
    void cell_without_ship_returns_null()
    {
        Assertions.assertNull(cell.getShip());
    }

    @Test
    void empty_cell_hit_returns_miss()
    {
        HitResult result = cell.hit();

        Assertions.assertEquals(result, HitResult.miss);
    }

    @Test
    void already_hit_cell_returns_already_hit()
    {
        cell.hit();

        HitResult result = cell.hit();

        Assertions.assertEquals(result, HitResult.alreadyMarked);
    }

    @Test
    void empty_cell_does_not_have_ship()
    {
        Assertions.assertFalse(cell.hasShip());
    }

    @Test
    void cell_returns_correct_ship()
    {
        Assertions.assertNull(cell.getShip());
        cell.setShip(ship);

        Assertions.assertSame(ship, cell.getShip());
    }

    @Test
    void cell_returns_hit_when_ship_is_hit()
    {
        cell.setShip(ship);
        Assertions.assertEquals(HitResult.hit, cell.hit());
    }

    @Test
    void cell_returns_sunk_when_ship_is_sunk()
    {
        ship.hit();
        cell.setShip(ship);
        Assertions.assertEquals(HitResult.sunk, cell.hit());
    }

    @Test
    void cell_has_correct_status_when_new()
    {
        Assertions.assertEquals(CellStatus.unmarked, cell.getStatus());
    }

    @Test
    void cell_has_correct_status_after_miss()
    {
        cell.hit();
        Assertions.assertEquals(CellStatus.miss, cell.getStatus());
    }

    @Test
    void cell_has_correct_status_after_hit()
    {
        cell.setShip(ship);
        cell.hit();
        Assertions.assertEquals(CellStatus.hit, cell.getStatus());
    }

    @Test
    void cell_has_correct_status_after_sunk()
    {
        ship.hit();
        cell.setShip(ship);
        cell.hit();
        Assertions.assertEquals(CellStatus.hit, cell.getStatus());
    }

}