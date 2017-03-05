package edu.weberstate.cs3230;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by parker on 2/13/17.
 */
class CoordinateTest {

    @Test
    void row_is_converted_from_char_to_int()
    {
        Coordinate coord = new Coordinate('a', 10);
        Assertions.assertEquals(0, coord.getRow());
    }

    @Test
    void higher_character_numbers_are_converted_correctly()
    {
        Coordinate coord = new Coordinate('R', 10);
        Assertions.assertEquals(17, coord.getRow());
    }

    @Test
    void capital_row_is_lowercased_and_converted_to_int()
    {
        Coordinate coord = new Coordinate('A', 10);
        Assertions.assertEquals(0, coord.getRow());
    }

    @Test
    void column_is_accessible()
    {
        Coordinate coord = new Coordinate('a', 27);
        Assertions.assertEquals(27, coord.getCol());
    }

    @Test
    void constructor_with_both_ints_works()
    {
        Coordinate coord = new Coordinate(0, 0);

        Assertions.assertEquals(0, coord.getRow());
        Assertions.assertEquals(0, coord.getCol());
    }

    @Test
    void row_as_char_works_correctly()
    {
        Coordinate coord = new Coordinate('a', 0);
        Assertions.assertEquals('a', coord.getRowChar());
    }
}