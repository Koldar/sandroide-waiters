package com.massimobono.sandroide_waiters.model;

/**
 * Represent the state of the table
 *
 * Created by massi on 3/29/2017.
 */
public enum TableState {
    ///the table does not require anything
    OFF,
    ///a waiter is needed to be on the table
    WAITER_NEEDED,
    ///The table keeps needing waiters attention. It elapsed 30s from #WAITER_NEEDED
    WAITER_STILL_NEEDED,
    ///The waiter is at the table, solvig the issue
    RESOLVING,
    ///The waiter completed the issue at the table
    RESOLVED;

    public String getString() {
        throw new RuntimeException();
    }
}
