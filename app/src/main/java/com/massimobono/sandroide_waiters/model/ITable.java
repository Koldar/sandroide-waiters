package com.massimobono.sandroide_waiters.model;

import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO;

/**
 * Represents the set of operation that can identify a table
 */
public interface ITable {

    public static final String PIN_IDENTIFIER_TEMPLATE = "nano_rbs_general_io_%d";

    /**
     *
     * @return an unique identifier of a table
     */
    public long getId();

    /**
     *
     * @return a description of a table
     */
    public String getName();

    /**
     *
     * @return the state of the table
     */
    public TableState getStatus();

    /**
     *
     * @param state the new state of the table
     */
    public void setStatus(TableState state);

    /**
     * The id of the pin the button is located on the BLE Nano
     * For example, if it is "5", the string used to initialize the pin will be "rbs_general_io_5"
     *
     * @return the button pin
     */
    public int getButtonPin();

    public void setButtonPin(int buttonPin);

    /**
     * for further details, see <a href="https://github.com/SAndroidEOfficial/framework/wiki/Using-RedBear-Nano-with-SAndroidE">here</a>
     *
     * For example, "rbs_general_io_5" is a ping identifier. You can fetch this value from {@link BLEGeneralIO#getDevItem()}
     * @return the pin identifier of the button layed on the table
     */
    public String getPinIdentifier();

    /**
     *
     * @return an instance allowing you to communicate with the physical button
     */
    public FakeBLEGeneralIO getPhysicalButton();

    /**
     * Adds a new listener of the table
     * Adding the same listener twice have no effects
     * @param tl the listener to add
     */
    public void addTableListener(TableListener tl);

    /**
     * Removes a listener of this table
     * Removing a listener that is currently not listening the {@link ITable} instance have no effects
     * @param tl the listener to remove
     */
    public void removeTableListener(TableListener tl);
}
