package edu.weberstate.cs3230;

public interface IGrid {
    public Cell[][] getGrid();
    public Cell.HitResult hit(Coordinate coord);
    public int getSize();
    public boolean placeShip(Ship ship, Placement placement, Cell[][] grid, boolean addToLists);
    public boolean placeShip(Ship ship, Placement placement);
    public Ship getShip(Coordinate coord);
}
