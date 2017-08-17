package io.chazza.advancementapi.conditions;

import com.google.gson.JsonObject;
import org.bukkit.inventory.ItemStack;

/**
 * @author Hannes
 */
public class ConsumeItemCondition extends Condition<ConsumeItemCondition> {

    private ItemStack stack;

    public ConsumeItemCondition() {
        setT(this);
    }

    public ConsumeItemCondition stack(ItemStack stack) {
        this.stack = stack;
        return set(stack);
    }

    @Override
    protected JsonObject convertToJson(Object object) {
        ItemStack stack = (ItemStack) object;
        JsonObject itemJSON = new JsonObject();
        itemJSON.addProperty("item", "minecraft:" + stack.getType().name().toLowerCase());
        itemJSON.addProperty("amount", stack.getAmount());
        itemJSON.addProperty("data", stack.getData().getData());
        return itemJSON;
    }
}
