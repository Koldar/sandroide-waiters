package com.massimobono.sandroide_waiters.model.standard;

import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.TableListener;
import com.massimobono.sandroide_waiters.utils.EventManager;

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
     * manage the listeners of this instance
     */
    private EventManager<TableListener> listenerManager;

    private static long nextId;

    static {
        nextId = 0;
    }

    public Table(long id, String name) {
        this.id = id;
        this.name = name;
        this.buzz = false;
        this.listenerManager = new EventManager<>();
    }

    /**
     * like {@link #Table(long, String)} but the ID is assigned automatically
     * @param name the name of the table
     */
    public Table(String name) {
        this(nextId, name);
        nextId++;
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
        this.listenerManager.addListener(tl);
    }

    @Override
    public void removeTableListener(TableListener tl) {
        this.listenerManager.removeListener(tl);
    }
}
