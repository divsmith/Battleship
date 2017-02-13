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

    @BeforeEach
    void setUp() {
        cell = new Cell();
    }

    @Test
    void empty_cell_hit_returns_miss()
    {
        CellResult result = cell.hit();

        Assertions.assertEquals(result, CellResult.miss);
    }

    @Test
    void already_hit_cell_returns_already_hit()
    {
        cell.hit();

        CellResult result = cell.hit();

        Assertions.assertEquals(result, CellResult.alreadyMarked);
    }

}