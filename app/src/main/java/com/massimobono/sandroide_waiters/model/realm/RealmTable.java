package com.massimobono.sandroide_waiters.model.realm;

import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.TableListener;
import com.massimobono.sandroide_waiters.utils.EventManager;

import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Represents a table synchronized with realm
 *
 *
 *
 * Created by massi on 3/9/2017.
 */
public class RealmTable extends RealmObject implements ITable{

    @PrimaryKey
    private long id;
    @Required
    private String name;
    private boolean buzzing;

    @Ignore
    private EventManager<TableListener> eventManager;

    public RealmTable() {
        super();
        this.eventManager = new EventManager<>();
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isBuzzing() {
        return buzzing;
    }

    public void setBuzzing(boolean buzzing) {
        this.buzzing = buzzing;
    }

    @Override
    public void addTableListener(TableListener tl) {
        this.eventManager.addListener(tl);
    }

    @Override
    public void removeTableListener(TableListener tl) {
        this.eventManager.removeListener(tl);
    }

    /**
     *
     * @param t the {@link ITable} instance to clone
     * @return a enw {@link RealmTable} from another {@link ITable} instance
     */
    public static RealmTable from(ITable t) {
        RealmTable retVal = new RealmTable();

        retVal.id = t.getId();
        retVal.name = t.getName();
        retVal.buzzing = t.isBuzzing();

        return retVal;
    }
}
