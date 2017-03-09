package com.massimobono.sandroide_waiters.utils;

import com.massimobono.sandroide_waiters.model.TableListener;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * A simple class that encapsule a set of listener
 *
 * You can use this class to manage listeners of a particular class
 *
 * Created by massi on 3/9/2017.
 */
public class EventManager<LISTENER> implements Iterable<LISTENER>{
    private Set<LISTENER> listeners;

    public EventManager() {
        super();
        this.listeners = new HashSet<>();
    }

    public void addListener(LISTENER l) {
        this.listeners.add(l);
    }

    public void removeListener(LISTENER l) {
        this.listeners.remove(l);
    }

    @Override
    public Iterator<LISTENER> iterator() {
        return this.listeners.iterator();
    }
}
