package io.chazza.advancementapi.conditions;

import com.google.gson.JsonObject;
import org.bukkit.inventory.ItemStack;

/**
 * @author SirYwell
 */
public class ItemCondition extends Condition<ItemCondition> {

    private ItemStack stack;

    public ItemCondition() {
        setT(this);
        name("item");
    }

    public ItemCondition stack(ItemStack stack) {
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
