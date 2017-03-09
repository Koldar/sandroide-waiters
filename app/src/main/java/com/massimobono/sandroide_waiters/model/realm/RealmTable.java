package com.massimobono.sandroide_waiters.model.realm;

import android.util.Log;

import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.TableListener;
import com.massimobono.sandroide_waiters.model.standard.Table;
import com.massimobono.sandroide_waiters.utils.EventManager;

import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import it.unibs.sandroide.lib.BLEContext;
import it.unibs.sandroide.lib.item.button.BLEButton;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIOEvent;
import it.unibs.sandroide.lib.item.generalIO.BLEOnGeneralIOEventListener;

/**
 * Represents a table synchronized with realm
 *
 * Created by massi on 3/9/2017.
 */
public class RealmTable extends RealmObject implements ITable {

    private static final String TAG = RealmTable.class.getSimpleName();

    @PrimaryKey
    private long id;
    @Required
    private String name;
    @Required
    private String pinIdentifier;

    @Ignore
    /*default*/ EventManager<TableListener> eventManager;
    @Ignore
    private BLEGeneralIO virtualButton;
    @Ignore
    private boolean buzzing;

    public RealmTable() {
        super();
        this.eventManager = new EventManager<>();
        this.pinIdentifier = "";
        this.buzzing = false;
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
        return this.buzzing;
    }

    public void setBuzzing(boolean buzzing) {
        this.buzzing = buzzing;
    }

    @Override
    public String getPinIdentifier() {
        return this.pinIdentifier;
    }

    @Override
    public void setPinIdentifier(String pinIdentifier) {
        this.pinIdentifier = pinIdentifier;
        //TODO create a callback for a new pinIdentifier???
//        this.virtualButton = (BLEGeneralIO) BLEContext.findViewById(this.pinIdentifier);
//        if (virtualButton == null) {
//            Log.e(TAG, String.format("cannot set virtual button %s", this.pinIdentifier));
//            return;
//        }
//        this.virtualButton.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
//            @Override
//            public void onBoardInitEnded() {
//                virtualButton.setStatus(BLEGeneralIO.GENERAL_IO_DI);
//            }
//
//            @Override
//            public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
//                Log.i(TAG, String.format("got an event from the button \"%s\": values: \"%s\"",
//                        bleGeneralIOEvent.bleGeneralIO.getActualDeviceName(),
//                        Arrays.toString(bleGeneralIOEvent.values)));
//
//                for (TableListener tl : RealmTable.this.eventManager) {
//                    if (bleGeneralIOEvent.values[1] == 1) {
//                        tl.onBuzzOn(RealmTable.this);
//                    } else {
//                        tl.onBuzzOff(RealmTable.this);
//                    }
//                }
//            }
//
//            @Override
//            public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
//
//            }
//
//            @Override
//            public void onDigitalOutputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
//
//            }
//
//            @Override
//            public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
//
//            }
//
//            @Override
//            public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
//
//            }
//
//            @Override
//            public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
//
//            }
//
//            @Override
//            public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {
//
//            }
//        });
    }

    @Override
    public void addTableListener(TableListener tl) {
        this.eventManager.addListener(tl);
    }

    @Override
    public void removeTableListener(TableListener tl) {
        this.eventManager.removeListener(tl);
    }
}
