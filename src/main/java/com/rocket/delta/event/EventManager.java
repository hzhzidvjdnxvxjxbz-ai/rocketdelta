package com.rocket.delta.event;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventManager {
    private final Map<Class<?>, List<Consumer<Object>>> listeners = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends Event> void register(Class<T> type, Consumer<T> listener) {
        listeners.computeIfAbsent(type, k -> new CopyOnWriteArrayList<>()).add((Consumer<Object>) (Consumer<?>) listener);
    }

    public <T extends Event> T post(T event) {
        List<Consumer<Object>> list = listeners.get(event.getClass());
        if (list != null) for (Consumer<Object> l : list) l.accept(event);
        return event;
    }
}
