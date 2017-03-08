package com.massimobono.sandroide_waiters.model.standard;

import com.massimobono.sandroide_waiters.model.ITable;

/**
 * A standard implementation of what a table actually is
 *
 * Created by massi on 3/8/2017.
 */
public class Table implements ITable {

    /**
     * The id of the table
     */
    private long id;
    /**
     * the name of the table
     */
    private String name;

    public Table(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
