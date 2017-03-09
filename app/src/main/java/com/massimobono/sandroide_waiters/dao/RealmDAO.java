package com.massimobono.sandroide_waiters.dao;

import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.realm.RealmTable;

import java.io.IOException;
import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmResults;

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

    public RealmDAO() {
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void setup() {
    }

    @Override
    public ITable getTable(int i) {
        return this.realm
                .where(RealmTable.class)
                .equalTo("id", i)
                .findFirst();
    }

    @Override
    public long getTableNumber() {
        return this.realm
                .where(RealmTable.class)
                .count();
    }

    @Override
    public ITable addTable(ITable table) {
        RealmTable retVal = RealmTable.from(table);
        this.realm.beginTransaction();
        this.realm.insert(retVal);
        this.realm.commitTransaction();

        return retVal;
    }

    @Override
    public Collection<? extends ITable> getAllTables() {
        return this.realm
                .where(RealmTable.class)
                .findAll();
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void removeTable(ITable table) {
        this.realm.beginTransaction();
        this.realm
                .where(RealmTable.class)
                .equalTo("id", table.getId())
                .findAll()
                .deleteAllFromRealm();
        this.realm.commitTransaction();
    }

    @Override
    public void resetDatabase() {
        this.realm.beginTransaction();
        this.realm
                .where(RealmTable.class)
                .findAll()
                .deleteAllFromRealm();
        this.realm.commitTransaction();
    }
}
