package com.massimobono.sandroide_waiters.model.realm;

import android.content.Context;

import com.massimobono.sandroide_waiters.model.DAO;
import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.TableListener;
import com.massimobono.sandroide_waiters.model.realm.RealmTable;

import java.io.IOException;
import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

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
    private static boolean realmInitialized;

    static {
        realmInitialized = false;
    }

    public RealmDAO(Context context) {
        if (!realmInitialized) {
            Realm.init(context);
            realmInitialized = true;
        }
        //TODO don't always recreate the database from scratch!
        //see https://github.com/realm/realm-java/issues/3472
        RealmConfiguration config2 = new RealmConfiguration.Builder()
                .name("default2")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build();

        this.realm = Realm.getInstance(config2);
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

        this.realm.beginTransaction();

        final RealmTable retVal = this.realm.createObject(RealmTable.class, table.getId());
        retVal.setName(table.getName());
        retVal.setBuzzing(table.isBuzzing());

        this.realm.insert(retVal);
        this.realm.commitTransaction();

        retVal.addChangeListener(new RealmChangeListener<RealmTable>() {
            @Override
            public void onChange(RealmTable element) {
                for (TableListener tl : element.eventManager) {
                    if (retVal.isBuzzing()) {
                        tl.onBuzzOn(retVal);
                    } else {
                        tl.onBuzzOff(retVal);
                    }
                }
            }
        });

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
        this.realm.close();
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
