package io.chazza.advancementapi.conditionUtils;

import com.google.gson.JsonObject;

/**
 * @author SirYwell
 */
public class Position extends Jsonable {

    private Number x;
    private Number y;
    private Number z;

    public Position(Number x, Number y, Number z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        if(x instanceof MaxMinValue) {
            object.add("x", ((MaxMinValue) x).toJson());
        }
        else {
            object.addProperty("x", x);
        }
        if(y instanceof MaxMinValue) {
            object.add("y", ((MaxMinValue) y).toJson());
        }
        else {
            object.addProperty("y", y);
        }
        if(z instanceof MaxMinValue) {
            object.add("z", ((MaxMinValue) z).toJson());
        }
        else {
            object.addProperty("z", z);
        }
        return object;
    }

}
