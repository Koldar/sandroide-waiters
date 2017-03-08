package com.massimobono.sandroide_waiters.model;

/**
 * Specify the set of callbacks an object can use to react to the {@link ITable} events
 *
 * Created by massi on 3/8/2017.
 */
public interface TableListener {

    /**
     * Called when someone presses the buzz
     *
     * @param table the table that generated this event
     */
    public void onBuzzOn(ITable table);

    /**
     * Called when someone turned off the buzz
     *
     * @param table the table that generated this event
     */
    public void onBuzzOff(ITable table);
}
