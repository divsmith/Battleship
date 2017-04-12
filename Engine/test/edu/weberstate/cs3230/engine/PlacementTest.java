package edu.weberstate.cs3230.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by parker on 2/28/17.
 */
class PlacementTest {
    @Test
    void orientation_is_set_correctly()
    {
        Placement placement = new Placement('a', 0, 'v');
        Assertions.assertEquals('v', placement.getOrientation());
    }

    @Test
    void constructor_from_coordinate_works()
    {
        Coordinate coord = new Coordinate('a', 0);
        Placement placement = new Placement(coord, 'h');

        Assertions.assertEquals(0, placement.getRow());
        Assertions.assertEquals(0, placement.getCol());
        Assertions.assertEquals('h', placement.getOrientation());
    }
}