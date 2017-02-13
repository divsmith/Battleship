package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.weberstate.cs3230.Cell.*;
import static org.junit.jupiter.api.Assertions.*;

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

}