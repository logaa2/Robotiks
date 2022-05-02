package net.logaa.robotiks.item.custom;

import net.logaa.robotiks.api.MultiblockHandler;
import net.logaa.robotiks.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class blockChecker extends Item {
    public blockChecker(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            Block blockClicked = pContext.getLevel().getBlockState(positionClicked).getBlock();
            String blockString = blockClicked.toString();

            player.sendMessage(new TextComponent("block: " + blockClicked), player.getUUID());
            player.sendMessage(new TextComponent("Electrode: " + blockClicked.toString()), player.getUUID());


            if(blockString == ModBlocks.SMOOTH_STONE_CONNECTOR.toString()){
                player.sendMessage(new TextComponent("CONNECTOR" ), player.getUUID());

            }else if(blockString == ModBlocks.SMALL_ELECTRODES.toString()){
                player.sendMessage(new TextComponent("ELECTRODE" ), player.getUUID());
            }
            player.sendMessage(new TextComponent("no match" ), player.getUUID());
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }
}