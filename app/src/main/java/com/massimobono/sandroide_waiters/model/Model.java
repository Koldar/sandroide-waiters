package com.massimobono.sandroide_waiters.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Representss the whole model of the application of us
 *
 * Created by massi on 3/8/2017.
 */
public class Model {

    private static Model singleton;

    private List<ITable> tables;

    static {
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

}
