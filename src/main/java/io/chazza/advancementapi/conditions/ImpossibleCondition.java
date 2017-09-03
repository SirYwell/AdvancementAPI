package io.chazza.advancementapi.conditions;

import com.google.gson.JsonObject;

/**
 * @author SirYwell
 * Not really necessary
 */
public class ImpossibleCondition extends Condition<ImpossibleCondition> {

    @Override
    protected JsonObject convertToJson(Object object) {
        return new JsonObject();
    }
}
