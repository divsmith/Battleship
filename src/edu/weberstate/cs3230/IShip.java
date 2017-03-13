package edu.weberstate.cs3230;

/**
 * Created by parker on 3/12/17.
 */
public interface IShip {
    public Ship copy();
    public String getName();
    public int getLength();
    public int getHits();
    public Ship.ShipState getShipState();
    public Ship.ShipState hit();
}
