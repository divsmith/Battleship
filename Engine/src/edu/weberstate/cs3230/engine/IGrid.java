package edu.weberstate.cs3230.engine;

public interface IGrid<T> {
    public Cell[][] getGrid();
    public Cell.HitResult hit(Coordinate coord);
    public int getSize();
    public boolean placeShip(T ship, Placement placement, Cell[][] grid, boolean addToLists);
    public boolean placeShip(T ship, Placement placement);
    public T getShip(Coordinate coord);
}
