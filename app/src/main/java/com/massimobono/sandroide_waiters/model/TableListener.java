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

    /**
     * Callback executed when a table arrived to the state {@link TableState#OFF}
     * @param oldState the state the table was before a transaction happened
     * @param table the table involved
     */
    public void onTableOff(TableState oldState, ITable table);

    /**
     * Callback executed when a table arrived to the state {@link TableState#WAITER_NEEDED}
     * @param oldState the state the table was before a transaction happened
     * @param table the table involved
     */
    public void onTableWaiterNeeded(TableState oldState, ITable table);

    /**
     * Callback executed when a table arrived to the state {@link TableState#WAITER_STILL_NEEDED}
     * @param oldState the state the table was before a transaction happened
     * @param table the table involved
     */
    public void onTableWaiterStillNeeded(TableState oldState, ITable table);

    /**
     * Callback executed when a table arrived to the state {@link TableState#RESOLVING}
     * @param oldState the state the table was before a transaction happened
     * @param table the table involved
     */
    public void onTableResolving(TableState oldState, ITable table);

    /**
     * Callback executed when a table arrived to the state {@link TableState#RESOLVED}
     * @param oldState the state the table was before a transaction happened
     * @param table the table involved
     */
    public void onTableResolved(TableState oldState, ITable table);


}
