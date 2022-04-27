package net.logaa.robotiks.item;

import net.logaa.robotiks.Robotiks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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

    /* ITEMS */
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}