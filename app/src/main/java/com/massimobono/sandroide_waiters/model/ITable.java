package com.massimobono.sandroide_waiters.model;

/**
 * Represents the set of operation that can identify a table
 */
public interface ITable {

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
     * @return true if a customer on that table requested a waiter
     */
    public boolean isBuzzing();

    /**
     * Set the new value of the buzzer
     * @param buzzing true if the button is buzzing, false otherwise
     */
    public void setBuzzing(boolean buzzing);

    /**
     * for further details, see <a href="https://github.com/SAndroidEOfficial/framework/wiki/Using-RedBear-Nano-with-SAndroidE">here</a>
     *
     * @return the pin identifier of the button layed on the table
     */
    public String getPinIdentifier();

    /**
     * Set the new pid identifier of this table
     * @param pinIdentifier the new pin identifier
     */
    public void setPinIdentifier(String pinIdentifier);

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
