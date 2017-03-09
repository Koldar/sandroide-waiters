package com.massimobono.sandroide_waiters.model;

import com.massimobono.sandroide_waiters.model.standard.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//TODO maybe Model can implement a collection? just for the lols
/**
 * Represents the whole model of the application of us
 *
 * At the current stage, our model is just a collection of tables
 *
 * Created by massi on 3/8/2017.
 */
public class Model {

    private static Model singleton;

    private static long staticId;
    private List<ITable> tables;

    static {
        staticId = 0;
        singleton = null;
    }

    private Model() {
        this.tables = new ArrayList<ITable>();
    }

    public static Model get() {
        if (singleton == null) {
            singleton = new Model();
        }
        return singleton;
    }
    /**
     *
     * @param position the index of the {@link ITable} we want to fetch
     * @return the {@link ITable} requested
     */
    public ITable get(int position) {
        return this.tables.get(position);
    }

    /**
     * factory method for a {@link ITable}
     *
     * @param id the unique identifier of the table
     * @return a new {@link ITable} instance
     */
    public ITable createTable(long id) {
        return new Table(id, "a normal table");
    }

    /**
     * like {@link #createTable(long)}, but it set automatically the id
     * @return a new {@link ITable} instance
     */
    public ITable createTable() {
        return this.createTable(staticId++);
    }

}
