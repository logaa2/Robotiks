package net.logaa.robotiks.item.custom;

import net.logaa.robotiks.api.MultiblockHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BasicHammerItem extends Item {
    public BasicHammerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            Block blockClicked = pContext.getLevel().getBlockState(positionClicked).getBlock();
            if(isValuableBlock(blockClicked)){
                outputValuableCoordinates(positionClicked, player, blockClicked);
                foundBlock = true;
                MultiblockHandler.MultiblockStructureIdentifier(blockClicked, positionClicked, pContext);
            }else if
            (!foundBlock) {
                player.sendMessage(new TranslatableComponent("item.tutorialmod.dowsing_rod.no_valuables"),
                        player.getUUID());
            }


        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }


    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockClicked) {
        player.sendMessage(new TextComponent("Found " + blockClicked.asItem().getRegistryName().toString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(Block block) {
        return block == Blocks.BLAST_FURNACE;
    }
}