package net.logaa.robotiks.block;

import it.unimi.dsi.fastutil.bytes.ByteLinkedOpenCustomHashSet;
import net.logaa.robotiks.Robotiks;
import net.logaa.robotiks.block.custom.SmallElectrodesBlock;
import net.logaa.robotiks.item.ModCreativeModeTab;
import net.logaa.robotiks.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BlOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Robotiks.MOD_ID);


    /* BLOCKS */
    public static final RegistryObject<Block> SILICON_BLOCK = registerBlock("silicon_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.ROBOTIKS_TAB);

    public static final RegistryObject<Block> SMOOTH_STONE_CONNECTOR = registerBlock("smooth_stone_connector",
            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.ROBOTIKS_TAB);

    public static final RegistryObject<Block> SMALL_ELECTRODES = registerBlock("small_electrodes",
            ()-> new SmallElectrodesBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.ROBOTIKS_TAB);

    /* BLOCKS */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BlOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block >RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                           CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus){
        BlOCKS.register(eventBus);
    }
}
