package io.chazza.advancementapi.conditions;

import com.google.gson.JsonObject;
import io.chazza.advancementapi.conditionUtils.wrappers.EntityWrapper;

/**
 * @author SirYwell
 */
public class BredAnimalsCondition extends Condition<BredAnimalsCondition> {


    public BredAnimalsCondition(String name) {
        name(name);
    }

    public BredAnimalsCondition() {

    }

    public BredAnimalsCondition entity(EntityWrapper entity) {
        set(entity);
        return this;
    }

    @Override
    protected JsonObject convertToJson(Object object) {
        return ((EntityWrapper) object).toJson();
    }
}
