package io.chazza.advancementapi.conditionUtils.wrappers;

import com.google.gson.JsonObject;
import io.chazza.advancementapi.conditionUtils.Jsonable;
import io.chazza.advancementapi.conditionUtils.LazyLocation;
import org.bukkit.entity.EntityType;

/**
 * @author SirYwell
 */
public class EntityWrapper extends Jsonable {


    // TODO add effects and nbt

    private String entityId;
    private LazyLocation location;

    public EntityWrapper(String entityId, LazyLocation location) {
        this.entityId = entityId;
        this.location = location;
    }

    public EntityWrapper(EntityType type, LazyLocation location) {
        this.entityId = type.getName();
        this.location = location;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object = addIfDeclared("type", "minecraft:" + entityId, object);
        object = addIfDeclared("location", location, object);
        return object;
    }
}
