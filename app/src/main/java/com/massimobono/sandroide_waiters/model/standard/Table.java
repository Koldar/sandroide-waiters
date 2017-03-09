package com.massimobono.sandroide_waiters.model.standard;

import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.TableListener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    /**
     * true if the buzz is on, false off
     */
    private boolean buzz;
    /**
     * a list of listeners for a particular table.
     * It is a set to prvevent duplicates
     */
    private Set<TableListener> listeners;

    public Table(long id, String name) {
        this.id = id;
        this.name = name;
        this.buzz = false;
        this.listeners = new HashSet<>();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isBuzzing() {
        return this.buzz;
    }

    @Override
    public void addTableListener(TableListener tl) {
        this.listeners.add(tl);
    }

    @Override
    public void removeTableListener(TableListener tl) {
        this.listeners.remove(tl);
    }
}
