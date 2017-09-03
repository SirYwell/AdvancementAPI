package io.chazza.advancementapi.conditionUtils;

import com.google.gson.JsonObject;

/**
 * @author SirYwell
 */
public abstract class Jsonable {

    public abstract JsonObject toJson();

    protected JsonObject addIfDeclared(String property, Object object, JsonObject base) {
        if(object != null) {
            if(object instanceof String) {
                base.addProperty(property, (String) object);
                return base;
            }
            if(object instanceof Number) {
                base.addProperty(property, (Number) object);
                return base;
            }
            if(object instanceof Character) {
                base.addProperty(property, (Character) object);
                return base;
            }
            if(object instanceof Boolean) {
                base.addProperty(property, (Boolean) object);
                return base;
            }
            base.add(property, ((Jsonable) object).toJson());
        }
        return base;
    }
}
