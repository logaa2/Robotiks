package net.logaa.robotiks.block;

import java.util.function.ToIntFunction;
import it.unimi.dsi.fastutil.bytes.ByteLinkedOpenCustomHashSet;
import net.logaa.robotiks.Robotiks;
import net.logaa.robotiks.block.custom.SmallElectrodesBlock;
import net.logaa.robotiks.item.ModCreativeModeTab;
import net.logaa.robotiks.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.lighting.BlockLightEngine;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.grower.AcaciaTreeGrower;
import net.minecraft.world.level.block.grower.BirchTreeGrower;
import net.minecraft.world.level.block.grower.DarkOakTreeGrower;
import net.minecraft.world.level.block.grower.JungleTreeGrower;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import java.util.function.Supplier;
import java.lang.Object;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.block.state.BlockState;

public class ModBlocks {
    public static ToIntFunction<BlockState> lightLevel = BlockState -> 15;
    public static final DeferredRegister<Block> BlOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Robotiks.MOD_ID);


    /* BLOCKS */
    public static final RegistryObject<Block> SILICON_BLOCK = registerBlock("silicon_block", ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.ROBOTIKS_TAB);
    public static final RegistryObject<Block> SMOOTH_STONE_CONNECTOR = registerBlock("smooth_stone_connector", ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.ROBOTIKS_TAB);
    public static final RegistryObject<Block> SMALL_ELECTRODES = registerBlock("small_electrodes", ()-> new SmallElectrodesBlock(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.ROBOTIKS_TAB);
    public static final RegistryObject<Block> SMALL_ELECTRODES_MOLD = registerBlock("small_electrodes_mold", ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.ROBOTIKS_TAB);

    public static final RegistryObject<Block> IRON_TORCH = registerBlock("iron_torch",()-> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel(lightLevel).sound(SoundType.WOOD), ParticleTypes.FLAME), ModCreativeModeTab.ROBOTIKS_TAB);
    public static final RegistryObject<Block> WALL_IRON_TORCH = registerBlock("wall_iron_torch",()-> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel(lightLevel).sound(SoundType.WOOD).dropsLike(ModBlocks.IRON_TORCH.get()), ParticleTypes.FLAME), ModCreativeModeTab.ROBOTIKS_TAB);

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
    int anonymousFunction(BlockState lightLevelToReturn) {
        return 10;
    }
}
