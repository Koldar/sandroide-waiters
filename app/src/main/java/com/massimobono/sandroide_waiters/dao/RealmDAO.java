package com.massimobono.sandroide_waiters.dao;

import com.massimobono.sandroide_waiters.model.ITable;

import java.io.IOException;
import java.util.Collection;

import io.realm.Realm;

/**
 * Represents a DAo connecting the application with a realm DB
 *
 * Information about the installation of Realm can be found <a href="https://realm.io/docs/java/latest/#prerequisites">here</a>;
 * furthermore, information about the use of Realm can be found here
 *
 * Created by massi on 3/9/2017.
 */
public class RealmDAO implements DAO {

    private Realm realm;

    @Override
    public void setup() {

    }

    @Override
    public ITable getTable(int i) {
        return null;
    }

    @Override
    public int getTableNumber() {
        return 0;
    }

    @Override
    public void addTable(ITable table) {

    }

    @Override
    public Collection<ITable> getAllTables() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
