package com.massimobono.sandroide_waiters.model;

/**
 * Represents the set of operation that can identify a table
 */
public interface ITable {

    /**
     *
     * @return an unique identifier of a table
     */
    public long getId();

    /**
     *
     * @return a description of a table
     */
    public String getName();

    /**
     *
     * @return true if a customer on that table requested a waiter
     */
    public boolean isBuzzing();

    /**
     * Adds a new listener of the table
     * Adding the same listener twice have no effects
     * @param tl the listener to add
     */
    public void addTableListener(TableListener tl);

    /**
     * Removes a listener of this table
     * Removing a listener that is currently not listening the {@link ITable} instance have no effects
     * @param tl the listener to remove
     */
    public void removeTableListener(TableListener tl);
}
