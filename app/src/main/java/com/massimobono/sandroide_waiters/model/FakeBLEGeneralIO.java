package com.massimobono.sandroide_waiters.model;

import it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO;
import it.unibs.sandroide.lib.item.generalIO.BLEOnGeneralIOEventListener;

/**
 *
 * A fake {@link it.unibs.sandroide.lib.item.generalIO.BLEGeneralIO} used for testing purposes
 * Created by massi on 3/29/2017.
 */
public class FakeBLEGeneralIO {

    private BLEOnGeneralIOEventListener listener;
    private int status;
    private String identifier;

    public static FakeBLEGeneralIO get(String s) {
        FakeBLEGeneralIO retVal = new FakeBLEGeneralIO();
        retVal.identifier = s;

        return retVal;
    }

    public void setOnGeneralIOEventListener(BLEOnGeneralIOEventListener listener) {
        this.listener = listener;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDevItem() {
        return this.identifier;
    }

    public String getActualDeviceName() {
        return "nano";
    }
}
