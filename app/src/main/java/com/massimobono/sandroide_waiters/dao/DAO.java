package com.massimobono.sandroide_waiters.dao;

import com.massimobono.sandroide_waiters.model.ITable;

import java.io.Closeable;
import java.util.Collection;

/**
 * Represents the interface a class has to have in order to be able to fetch/store from/in a permanent storage.
 *
 * Created by massi on 3/9/2017.
 */

public interface DAO extends Closeable{

    /**
     * List of instruction to call to setup the DAO.
     * You also need to write a complementary list of operation to close in it {@link #close()}
     */
    public void setup();

    /**
     *
     * @param i the position of the element we need to fetch
     * @return the i-th {@link ITable} inside the DAO
     */
    public ITable getTable(int i);

    /**
     *
     * @return the total number of {@link ITable} inside the dao
     */
    public int getTableNumber();

    /**
     * Adds a new table inside the DAO
     *
     * <b>Note: we provide only a {@link ITable} parameter bacause in this way we separate concern between table storage and table creation</b>
     *
     * @param table the table to add
     */
    public void addTable(ITable table);

    /**
     * <p>Warning: this operation may return lots of instances! Be careful</p>
     *
     * @return all the available {@link ITable} inside the DAO
     */
    public Collection<ITable> getAllTables();
}
