package net.logaa.robotiks.item;

import net.logaa.robotiks.Robotiks;
import net.logaa.robotiks.block.ModBlocks;
import net.logaa.robotiks.item.custom.BasicHammerItem;
import net.logaa.robotiks.item.custom.blockChecker;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Robotiks.MOD_ID);

    /* ITEMS */
    public static final RegistryObject<Item> SILICON = ITEMS.register("silicon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ROBOTIKS_TAB)));

    public static final RegistryObject<Item> IRON_STICK = ITEMS.register("iron_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ROBOTIKS_TAB)));

    public static final RegistryObject<Item> SILICON_BOARD = ITEMS.register("silicon_board",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ROBOTIKS_TAB)));

    public static final RegistryObject<Item> IRON_TORCH = ITEMS.register("iron_torch", () -> new StandingAndWallBlockItem(ModBlocks.IRON_TORCH.get(), ModBlocks.WALL_IRON_TORCH.get(), (new Item.Properties()).tab(ModCreativeModeTab.ROBOTIKS_TAB)));


    //TOOLS
    public static final RegistryObject<Item> BASIC_HAMMER = ITEMS.register("basic_hammer",
            () -> new BasicHammerItem(new Item.Properties().tab(ModCreativeModeTab.ROBOTIKS_TAB).durability(128)));




    /* ITEMS */
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
