package io.chazza.advancementapi.conditionUtils;

import com.google.gson.JsonObject;

/**
 * @author SirYwell
 */
public class MaxMinValue extends Number {

    private int min;
    private int max;

    public MaxMinValue(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("min", min);
        object.addProperty("max", max);
        return object;
    }

    @Override
    public int intValue() {
        return hashCode();
    }

    @Override
    public long longValue() {
        return hashCode();
    }

    @Override
    public float floatValue() {
        return hashCode();
    }

    @Override
    public double doubleValue() {
        return hashCode();
    }
}
