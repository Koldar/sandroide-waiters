package com.massimobono.sandroide_waiters.model.standard;

import com.massimobono.sandroide_waiters.model.DAO;
import com.massimobono.sandroide_waiters.model.ITable;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A DAO masking a map of {@link ITable}
 *
 * Useful for simplyfying logic during development
 *
 * Created by massi on 3/29/2017.
 */
public class StandardDAO implements DAO {

    private Map<Long, ITable> tables;

    public StandardDAO() {
        this.tables = new HashMap<>();
    }

    @Override
    public void setup() {

    }

    @Override
    public ITable getTable(int i) {
        return this.tables.get((long)i);
    }

    @Override
    public long getTableNumber() {
        return this.tables.size();
    }

    @Override
    public ITable addTable(ITable table) {
        this.tables.put(table.getId(), table);
        return table;
    }

    @Override
    public Collection<? extends ITable> getAllTables() {
        return this.tables.values();
    }

    @Override
    public void removeTable(ITable table) {
        this.tables.remove(table.getId());
    }

    @Override
    public void resetDatabase() {
        this.tables.clear();
    }

    @Override
    public void close() throws IOException {
    }
}
