package com.massimobono.sandroide_waiters.model;

import com.massimobono.sandroide_waiters.dao.DAO;
import com.massimobono.sandroide_waiters.dao.RealmDAO;
import com.massimobono.sandroide_waiters.model.standard.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Represents the whole model of the application of us
 *
 * At the current stage, our model contains only the DAO
 *
 * Created by massi on 3/8/2017.
 */
public class Model {

    /**
     * the object used to fetch all data from application permament storage
     */
    private DAO dao;

    public Model(DAO dao) {
        this.dao = dao;

        //just for the funz we add some tables
        this.dao.resetDatabase();
        for (int i=0; i<10; i++ ) {
            this.dao.addTable(new Table(String.format("tavolo %02d", i)));
        }
    }

    public DAO getDao() {
        return this.dao;
    }

}
