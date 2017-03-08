package com.massimobono.sandroide_waiters.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representss the whole model of the application of us
 *
 * Created by massi on 3/8/2017.
 */
public class Model {

    private static Model singleton;

    private Collection<ITable> tables;

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
     * @return all the tables available in the model
     */
    public Collection<ITable> getTables() {
        return this.tables;
    }

}
