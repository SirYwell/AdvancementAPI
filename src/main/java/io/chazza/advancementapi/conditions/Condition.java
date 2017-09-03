package io.chazza.advancementapi.conditions;

import com.google.gson.JsonObject;

/**
 * @author SirYwell (rewritten)
 */
public abstract class Condition<T extends Condition<T>> {

    private String name;
    private JsonObject set;
    private T t;

    void setT(T t) {
        this.t = t;
    }

    public T name(String name) {
        this.name = name;
        return t;
    }

    public T set(Object object) {
        this.set = convertToJson(object);
        return t;
    }

    protected abstract JsonObject convertToJson(Object object);

    public String getName() {
        return name;
    }

    public JsonObject getSet() {
        return set;
    }

    @Override
    public String toString() {
        return t.getClass().getCanonicalName() + "(name: " + name + ", set: " + set + ")";
    }
}
