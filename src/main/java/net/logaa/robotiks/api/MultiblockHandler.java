package net.logaa.robotiks.api;
import net.logaa.robotiks.block.multiblocks.SmallElectricArcFurnace;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
public class MultiblockHandler {

    public static void MultiblockStructureIdentifier(Block blockClicked, BlockPos blockClickedPosition, UseOnContext pContext){
        Player player = pContext.getPlayer();
        player.sendMessage(new TextComponent("Identifier started"), player.getUUID());


        if(isBlastFurnace(blockClickedPosition, pContext)){
            // IF BLAST FURNACE

            MultiblockConfirmedFacing isSmallArcFurnace = SmallElectricArcFurnace.confirm(blockClicked, blockClickedPosition, pContext, player);
            if(isSmallArcFurnace.confirmation == true)
            {
                player.sendMessage(new TextComponent("Identified structure as small electric arc furnace"), player.getUUID());
                player.sendMessage(new TextComponent("Facing: " + isSmallArcFurnace.originFacing), player.getUUID());
                SmallElectricArcFurnace.validateCompleteStructure(blockClicked, blockClickedPosition, pContext, player, isSmallArcFurnace.originFacing);
            }
            else if(isSmallArcFurnace.confirmation == false)
            {
                player.sendMessage(new TextComponent("No connections found"), player.getUUID());
            }
        }
        else
        {
            player.sendMessage(new TextComponent("No connections found"), player.getUUID());
        }
    }

    private static boolean isBlastFurnace(BlockPos blockPositionClicked, UseOnContext pContext) {
        Block block = pContext.getLevel().getBlockState(blockPositionClicked).getBlock();
        return block == Blocks.BLAST_FURNACE;
    }

}
