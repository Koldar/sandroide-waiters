package com.massimobono.sandroide_waiters.model.standard;

import android.support.design.widget.TabLayout;
import android.util.Log;

import com.massimobono.sandroide_waiters.model.FakeBLEGeneralIO;
import com.massimobono.sandroide_waiters.model.ITable;
import com.massimobono.sandroide_waiters.model.TableListener;
import com.massimobono.sandroide_waiters.model.TableState;
import com.massimobono.sandroide_waiters.utils.EventManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import io.realm.RealmObjectSchema;
import it.unibs.sandroide.lib.BLEContext;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO;
import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIOEvent;
import it.unibs.sandroide.lib.item.generalIO.BLEOnGeneralIOEventListener;

/**
 * A standard implementation of what a table actually is
 *
 * Created by massi on 3/8/2017.
 */
public class Table implements ITable {

    private static final String TAG = Table.class.getSimpleName();

    /**
     * The id of the table
     */
    private long id;
    /**
     * the name of the table
     */
    private String name;
    /**
     * the pin where the button is positioned
     */
    private int buttonPin;
    /**
     * The state of the table
     */
    private TableState state;
    /**
     * The physical button
     *
     * Created <b>on demand</b>
     */
    private FakeBLEGeneralIO physicalButton;
    /**
     * manage the listeners of this instance
     */
    private EventManager<TableListener> listenerManager;

    private static long nextId;

    static {
        nextId = 0;
    }

    public Table(long id, String name, int buttonPin) {
        this.id = id;
        this.name = name;
        this.state = TableState.OFF;
        this.buttonPin = buttonPin;
        this.listenerManager = new EventManager<>();
    }

    /**
     * like {@link #Table(long, String, int)} but the ID is assigned automatically
     * @param name the name of the table
     */
    public Table(String name, int buttonPin) {
        this(nextId, name, buttonPin);
        nextId++;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TableState getStatus() {
        return this.state;
    }

    @Override
    public void setStatus(TableState state) {
        TableState oldState = this.state;
        this.state = state;
        if (this.state != oldState) {
            for (TableListener tl : this.listenerManager) {
                switch (this.state) {
                    case OFF:
                        tl.onTableOff(oldState, this);
                        break;
                    case WAITER_NEEDED:
                        tl.onTableWaiterNeeded(oldState, this);
                        break;
                    case WAITER_STILL_NEEDED:
                        tl.onTableWaiterStillNeeded(oldState, this);
                        break;
                    case RESOLVING:
                        tl.onTableResolving(oldState, this);
                        break;
                    case RESOLVED:
                        tl.onTableResolved(oldState, this);
                        break;
                }
            }
        }
    }

    @Override
    public int getButtonPin() {
        return this.buttonPin;
    }

    @Override
    public void setButtonPin(int buttonPin) {
        this.buttonPin = buttonPin;
        this.physicalButton = null;
    }

    @Override
    public String getPinIdentifier() {
        return String.format(PIN_IDENTIFIER_TEMPLATE, this.buttonPin);
    }

    @Override
    public FakeBLEGeneralIO getPhysicalButton() {
        if (this.physicalButton == null) {
            //TODO readd this.physicalButton = (BLEGeneralIO) BLEContext.findViewById(this.getPinIdentifier());
            this.physicalButton = FakeBLEGeneralIO.get(this.getPinIdentifier());
            this.physicalButton.setOnGeneralIOEventListener(new BLEOnGeneralIOEventListener() {
                @Override
                public void onBoardInitEnded() {
                    Log.i(TAG, "BUTTON initialized! devItem=" + physicalButton.getDevItem() + " actualDeviceName=" + physicalButton.getActualDeviceName());
                    physicalButton.setStatus(BLEGeneralIO.GENERAL_IO_AI);
                }

                @Override
                public void onDigitalInputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.i(TAG, "onDigitalInputValueChanged: " + bleGeneralIOEvent.values[1]);
                }

                @Override
                public void onAnalogValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.i(TAG, "onAnalogValueChanged " + bleGeneralIOEvent.values[1]);
                    //values[1] 96 if button is not pressed; 0.02 if the button is pressed
                    boolean pressed = bleGeneralIOEvent.values[1] < 50;

                    for (TableListener tl : listenerManager) {
                        if (pressed) {
                            tl.onBuzzOn(Table.this);
                        } else {
                            tl.onBuzzOff(Table.this);
                        }
                    }
                    //the button has been pressed. This is the only action the table can perform
                    if (pressed && getStatus() == TableState.OFF) {
                        Table.this.setStatus(TableState.WAITER_NEEDED);
                    }

                }

                @Override
                public void onDigitalOutputValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.i(TAG, "onDigitalOutputValueChanged");
                }

                @Override
                public void onServoValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.i(TAG, "onServoValueChanged");
                }

                @Override
                public void onPWMValueChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.i(TAG, "onPWMValueChanged");
                }

                @Override
                public void onGeneralIOStatusChanged(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.i(TAG, "onGeneralIOStatusChanged");
                }

                @Override
                public void onSetGeneralIOParameter(BLEGeneralIOEvent bleGeneralIOEvent) {
                    Log.i(TAG, "onSetGeneralIOParameter");
                }
            });
        }
        return this.physicalButton;
    }

    @Override
    public void addTableListener(TableListener tl) {
        this.listenerManager.addListener(tl);
    }

    @Override
    public void removeTableListener(TableListener tl) {
        this.listenerManager.removeListener(tl);
    }
}
