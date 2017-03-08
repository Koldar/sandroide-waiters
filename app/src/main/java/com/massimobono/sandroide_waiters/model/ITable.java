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

    public void addTableListener(TableListener tl);

    public void removeTableListener(TableListener tl);
}
