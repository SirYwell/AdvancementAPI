import io.chazza.advancementapi.AdvancementAPI;
import io.chazza.advancementapi.FrameType;
import io.chazza.advancementapi.Trigger;
import io.chazza.advancementapi.conditions.ConsumeItemCondition;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
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

        ConsumeItemCondition condition = new ConsumeItemCondition();
        condition.stack(new ItemStack(Material.BREAD));
        condition.name("item");
        AdvancementAPI parent = AdvancementAPI.builder(new NamespacedKey("plugin", "my/firststeps"))
                .title("First Steps")
                .description("Starting")
                .icon("minecraft:wood_sword")
                .trigger(
                        Trigger.builder(
                                Trigger.TriggerType.CONSUME_ITEM, "test")
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
        condition.stack(new ItemStack(Material.APPLE));
        AdvancementAPI advancementAPI = AdvancementAPI.builder(new NamespacedKey("test", "my/addiction"))
                .title(textComponent) // the TextComponent define above
                .description("Eat an Apple") // you can also use a normal String instead of the TextComponent
                .icon("minecraft:golden_apple")
                .trigger(
                        Trigger.builder(
                                Trigger.TriggerType.CONSUME_ITEM, "test") // triggers when consuming an item
                                .condition(condition)) //1 x apple
                .hidden(true) // Advancement is hidden before completed
                .toast(true) // should send a Toast Message -> popup right upper corner
                .background("minecraft:textures/gui/advancements/backgrounds/stone.png")
                .frame(FrameType.GOAL)
                .parent(parent.getId().toString()) // define a parent! example above
                .build();

        advancementAPI.save(worldName);

    }

}
