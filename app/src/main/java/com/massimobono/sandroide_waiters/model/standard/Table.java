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
     * The string identifying the button
     */
    private String pinIdentifier;
    /**
     * manage the listeners of this instance
     */
    private EventManager<TableListener> listenerManager;

    private static long nextId;

    static {
        nextId = 0;
    }

    public Table(long id, String name, String pinIdentifier) {
        this.id = id;
        this.name = name;
        this.buzz = false;
        this.pinIdentifier = pinIdentifier;
        this.listenerManager = new EventManager<>();
    }

    /**
     * like {@link #Table(long, String, String)} but the ID is assigned automatically
     * @param name the name of the table
     */
    public Table(String name, String pinIdentifier) {
        this(nextId, name, pinIdentifier);
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
    public void setBuzzing(boolean buzzing) {
        for (TableListener tl : this.listenerManager) {
            if (this.buzz) {
                tl.onBuzzOn(this);
            } else {
                tl.onBuzzOff(this);
            }
        }
    }

    @Override
    public String getPinIdentifier() {
        return this.pinIdentifier;
    }

    @Override
    public void setPinIdentifier(String pinIdentifier) {
        this.pinIdentifier = pinIdentifier;
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
