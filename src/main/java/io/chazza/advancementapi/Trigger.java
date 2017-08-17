package io.chazza.advancementapi;

import com.google.gson.JsonObject;
import io.chazza.advancementapi.conditions.Condition;

import java.util.*;

/**
 * @author Hannes
 */
public class Trigger {

    private TriggerType type;
    private String name;
    private Set<Condition> conditions = new LinkedHashSet<>();

    private Trigger(TriggerType type, String name, Set<Condition> conditions) {
        this.type = type;
        this.name = name;
        this.conditions = conditions;
    }

    public static TriggerBuilder builder(TriggerType type, String name) {
        return new TriggerBuilder().name(name).type(type);
    }

    public String getName() {
        return name;
    }

    public JsonObject toJsonObject() {

        JsonObject triggerObj = new JsonObject();

        final JsonObject advConditions = new JsonObject();
        triggerObj.addProperty("trigger", "minecraft:" + this.type.toString().toLowerCase());
        this.conditions.forEach(condition -> advConditions.add(condition.getName(), condition.getSet()));
        if (!this.conditions.isEmpty())
            triggerObj.add("conditions", advConditions);


        return triggerObj;

    }

    public static class TriggerBuilder {

        private String name;
        private ArrayList<Condition> conditions = new ArrayList<>();
        private TriggerType type;

        public TriggerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TriggerBuilder type(TriggerType type) {
            this.type = type;
            return this;
        }

        public TriggerBuilder condition(Condition condition) {
            conditions.add(condition);
            return this;
        }

        public TriggerBuilder conditions(Collection<? extends Condition> conditions) {
            this.conditions.addAll(conditions);
            return this;
        }

        public Trigger build() {
            Set<Condition> conditions;
            switch (this.conditions == null ? 0 : this.conditions.size()) {
                case 0:
                    conditions = Collections.emptySet();
                    break;
                case 1:
                    conditions = Collections.singleton(this.conditions.get(0));
                    break;
                default:
                    conditions = new LinkedHashSet<>(this.conditions.size() < 1073741824 ? 1 + this.conditions.size() + (this.conditions.size() - 3) / 3 : Integer.MAX_VALUE);
                    conditions.addAll(this.conditions);
                    conditions = java.util.Collections.unmodifiableSet(conditions);
            }

            return new Trigger(type, name, conditions);
        }
    }

    public enum TriggerType {
        ARBITRARY_PLAYER_TICK,
        BRED_ANIMALS,
        BREWED_POTION,
        CHANGED_DIMENSION,
        CONSTRUCT_BEACON,
        CONSUME_ITEM,
        CURED_ZOMBIE_VILLAGER,
        ENCHANTED_ITEM,
        ENTER_BLOCK,
        ENTITY_HURT_PLAYER,
        ENTITY_KILLED_PLAYER,
        IMPOSSIBLE,
        INVENTORY_CHANGED,
        ITEM_DURABILITY_CHANGED,
        LEVITATION,
        LOCATION,
        PLACED_BLOCK,
        PLAYER_HURT_ENTITY,
        PLAYER_KILLED_ENTITY,
        RECIPE_UNLOCKED,
        SLEPT_IN_BED,
        SUMMONED_ENTITY,
        TAME_ANIMAL,
        TICK,
        USED_ENDER_EYE,
        VILLAGER_TRADE
    }
}
