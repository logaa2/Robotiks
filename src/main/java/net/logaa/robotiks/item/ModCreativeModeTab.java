package net.logaa.robotiks.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ROBOTIKS_TAB = new CreativeModeTab("robotikstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILICON.get());
        }
    };
}
