package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}