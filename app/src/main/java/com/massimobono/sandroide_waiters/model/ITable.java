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
}
