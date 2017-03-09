package com.massimobono.sandroide_waiters.model;

import com.massimobono.sandroide_waiters.dao.DAO;
import com.massimobono.sandroide_waiters.dao.RealmDAO;
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
    /**
     * the object used to fetch all data from application permament storage
     */
    private DAO dao;

    static {
        singleton = null;
    }

    private Model() {
        this.dao = new RealmDAO();
    }

    public static Model get() {
        if (singleton == null) {
            singleton = new Model();
        }
        return singleton;
    }

    public DAO getDao() {
        return this.dao;
    }

}
