package com.massimobono.sandroide_waiters.dao;

import com.massimobono.sandroide_waiters.model.ITable;

import java.io.Closeable;
import java.util.Collection;

/**
 * Created by massi on 3/9/2017.
 */

public interface DAO extends Closeable{

    public void setup();

    public ITable getTable(int i);

    public int getTableNumber();

    public void addTable(int id, String name);

    public Collection<ITable> getAllTables();
}
