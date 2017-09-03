import io.chazza.advancementapi.AdvancementAPI;
import io.chazza.advancementapi.FrameType;
import io.chazza.advancementapi.Trigger;
import io.chazza.advancementapi.conditionUtils.LazyLocation;
import io.chazza.advancementapi.conditionUtils.MaxMinValue;
import io.chazza.advancementapi.conditionUtils.Position;
import io.chazza.advancementapi.conditionUtils.wrappers.EntityWrapper;
import io.chazza.advancementapi.conditions.BredAnimalsCondition;
import io.chazza.advancementapi.conditions.ItemCondition;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.logging.Logger;

import static org.mockito.Mockito.mock;

/**
 * Created by ysl3000
 */
public class AdvancementAPITest {


    private final String worldName = "world";
    private final File worldFile = new File(worldName);

    @Before
    public void setUp() {
        World world = mock(World.class);

        Mockito.when(world.getWorldFolder()).thenReturn(worldFile);

        Server server = mock(Server.class);
        Mockito.when(server.getLogger()).thenReturn(Logger.getAnonymousLogger());
        Mockito.when(server.getWorld("world")).thenReturn(world);
        Mockito.when(server.getVersion()).thenReturn("AdvancementTestMocking");
        Mockito.when(server.getVersion()).thenReturn("AdvancementTestMocking");
        Bukkit.setServer(server);
    }


    @Test
    public void createAndSave() {
        LazyLocation location = new LazyLocation();
        MaxMinValue xAndZ = new MaxMinValue(-20, 20);
        MaxMinValue y = new MaxMinValue(0, 265);
        location.setPosition(new Position(xAndZ, y, xAndZ));
        BredAnimalsCondition condition = new BredAnimalsCondition("child");
        condition.entity(new EntityWrapper(EntityType.COW, location));
        AdvancementAPI parent = AdvancementAPI.builder(new NamespacedKey("plugin", "my/firststeps"))
                .title("First Steps")
                .description("Starting")
                .icon("minecraft:wooden_sword")
                .trigger(
                        Trigger.builder(
                                Trigger.TriggerType.BRED_ANIMALS, "test")
                                .condition(condition))
                .hidden(false)
                .toast(false)
                .background("minecraft:textures/gui/advancements/backgrounds/stone.png")
                .frame(FrameType.TASK)
                .build();

        parent.save(worldName);

        // you're able to use TextComponents @see https://www.spigotmc.org/wiki/the-chat-component-api/#colors-and-formatting
        TextComponent textComponent = new TextComponent("Addiction!");
        textComponent.setBold(true);
        textComponent.setColor(ChatColor.GOLD);
        ItemCondition condition1 = new ItemCondition();
        condition1.stack(new ItemStack(Material.APPLE));
        condition1.name("item");
        AdvancementAPI advancementAPI = AdvancementAPI.builder(new NamespacedKey("plugin", "my/addiction"))
                .title(textComponent) // the TextComponent define above
                .description("Eat an Apple") // you can also use a normal String instead of the TextComponent
                .icon("minecraft:golden_apple")
                .trigger(
                        Trigger.builder(
                                Trigger.TriggerType.CONSUME_ITEM, "test") // triggers when consuming an item
                                .condition(condition1)) //1 x apple
                .hidden(true) // Advancement is hidden before completed
                .toast(true) // should send a Toast Message -> popup right upper corner
                .background("minecraft:textures/gui/advancements/backgrounds/stone.png")
                .frame(FrameType.GOAL)
                .parent(parent.getId().toString()) // define a parent! example above
                .build();

        advancementAPI.save(worldName);

    }

}
