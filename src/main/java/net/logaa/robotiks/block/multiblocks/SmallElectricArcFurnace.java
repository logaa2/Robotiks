package net.logaa.robotiks.block.multiblocks;

import net.logaa.robotiks.api.MultiblockConfirmedFacing;
import net.logaa.robotiks.block.ModBlocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class SmallElectricArcFurnace {
    public static void validateCompleteStructure(Block blockClicked, BlockPos blockClickedPosition, UseOnContext pContext, Player player, String hammerFacing){

        int width = 3;
        int height = 5;
        int depth = 3;




        BlockPos offsetToOrigin = new BlockPos(0, 0, 0);/*sets origin*/
        if(hammerFacing == "north"){
            offsetToOrigin = new BlockPos(1,1,0);
        }
        else if(hammerFacing == "west"){
            offsetToOrigin = new BlockPos(2,1,-1);
        }
        else if(hammerFacing == "south"){
            offsetToOrigin = new BlockPos(1,1,-2);
        }
        else if(hammerFacing == "east"){
            offsetToOrigin = new BlockPos(0,1,-1);
        }
        BlockPos origin;
        origin = blockClickedPosition.subtract(offsetToOrigin);/*sets origin*/
        //player.sendMessage(new TextComponent("Origin: " + origin.getX()+ ", " + origin.getY() + ", " + origin.getZ()), player.getUUID());

        BlockPos pointer;
        pointer = origin;
        int u =0;

        int pointerArea[][] = new int[width*width][height];
        int structure[][] = {{0,2,2,2,0},{2,1,0,2,2},{0,2,2,2,0},{2,1,0,2,2},{3,0,0,5,4},{2,1,0,2,2},{0,2,2,2,0},{2,1,0,2,2},{0,2,2,2,0}};
        //player.sendMessage(new TextComponent("Scan started."), player.getUUID());

        for(int z = 0 ; z < depth ; z++){
            for(int x = 0 ; x < width ; x++){
                //player.sendMessage(new TextComponent("Pointer: " + pointer.getX() + ", " + pointer.getZ()), player.getUUID());
                //player.sendMessage(new TextComponent("u: " + u), player.getUUID());
                for(int y = 0 ; y < height ; y++){


                    int blockType = checkBlockType(pointer, pContext);

                    pointerArea[u][y] = blockType;
                    if (pointerArea[u][y] != structure[u][y]) {
                        //player.sendMessage(new TextComponent("Should be: " + structure[u][y]), player.getUUID());
                    }


                    //player.sendMessage(new TextComponent("is: " + blockType), player.getUUID());
                    BlockPos operator = new BlockPos(0,-1,0);
                    pointer = pointer.subtract(operator);

                }
                BlockPos operator = new BlockPos(-1,height,0);
                pointer = pointer.subtract(operator);
                u++;
            }
            BlockPos operatorZ = new BlockPos(width,0,1);
            pointer = pointer.subtract(operatorZ);
        }

        //player.sendMessage(new TextComponent("Scan finished."), player.getUUID());

        //COMPARE BOTH ARRAYS
        int blockMatchesStructure = 0;
        for(int x = 0; x < (width*width) ; x++ ){
            for(int y = 0; y < height ; y++){
                if(pointerArea[x][y] == structure[x][y]){
                    blockMatchesStructure++;
                    //player.sendMessage(new TextComponent("Matches: " + blockMatchesStructure), player.getUUID());
                }
            }
        }

        //IF SCAN = STRUCTURE MODEL
        if(blockMatchesStructure == 45){
            player.sendMessage(new TextComponent("Structure is complete."), player.getUUID());
        }
    }

    public static MultiblockConfirmedFacing confirm(Block blockClicked, BlockPos blockClickedPosition, UseOnContext pContext, Player player){
        //player.sendMessage(new TextComponent("Entered confirm"), player.getUUID());
        for(int f = 0; f < 4 ;f++){
            List<BlockPos> faces = structureFaces(f);
            //player.sendMessage(new TextComponent("f: " + f), player.getUUID());
            int foundSides = 0;

            for(int i = 0; i < 4 ;i++){

                //player.sendMessage(new TextComponent("i: " + i), player.getUUID());



                BlockPos result = blockClickedPosition.subtract(faces.get(i));
                //player.sendMessage(new TextComponent("Side " + i + " (" + result.getX() + ", " + result.getY() + ", " + result.getZ() + ")"), player.getUUID());

                if(isBlastFurnace(result, pContext)) {
                    foundSides += 1;
                }
                //player.sendMessage(new TextComponent("Found " + foundSides + " sides"), player.getUUID());
                if(foundSides == 4){
                    if(f == 0){
                        MultiblockConfirmedFacing furnaceConfirmedFacing = new MultiblockConfirmedFacing(true , "north");
                        return furnaceConfirmedFacing;
                    }
                    else if(f == 1){
                        MultiblockConfirmedFacing furnaceConfirmedFacing = new MultiblockConfirmedFacing(true , "east");
                        return furnaceConfirmedFacing;
                    }
                    else if(f == 2){
                        MultiblockConfirmedFacing furnaceConfirmedFacing = new MultiblockConfirmedFacing(true , "south");
                        return furnaceConfirmedFacing;
                    }
                    else if(f == 3){
                        MultiblockConfirmedFacing furnaceConfirmedFacing = new MultiblockConfirmedFacing(true , "west");
                        return furnaceConfirmedFacing;
                    }
                }
            }
        }
        MultiblockConfirmedFacing furnaceNotConfirmed = new MultiblockConfirmedFacing(false , "north");
        return furnaceNotConfirmed;
    }
    public static List<BlockPos> structureFaces(int hammerFacing){
        if(hammerFacing == 0) {
            List<BlockPos> faces = new ArrayList<>();
            BlockPos West = new BlockPos(1, 0, 1);
            BlockPos North = new BlockPos(0, 0, 2);
            BlockPos East = new BlockPos(-1, 0, 1);
            BlockPos South = new BlockPos(0, 0, 0);

            faces.add(West);
            faces.add(North);
            faces.add(East);
            faces.add(South);
            return faces;
        }else if(hammerFacing == 1) {
            List<BlockPos> faces = new ArrayList<>();
            BlockPos West = new BlockPos(0, 0, 0);
            BlockPos North = new BlockPos(-1, 0, 1);
            BlockPos East = new BlockPos(-2, 0, 0);
            BlockPos South = new BlockPos(-1, 0, -1);

            faces.add(West);
            faces.add(North);
            faces.add(East);
            faces.add(South);
            return faces;
        }else if(hammerFacing == 2) {
            List<BlockPos> faces = new ArrayList<>();
            BlockPos West = new BlockPos(1, 0, -1);
            BlockPos North = new BlockPos(0, 0, 0);
            BlockPos East = new BlockPos(-1, 0, -1);
            BlockPos South = new BlockPos(0, 0, -2);

            faces.add(West);
            faces.add(North);
            faces.add(East);
            faces.add(South);
            return faces;
        }else if(hammerFacing == 3) {
            List<BlockPos> faces = new ArrayList<>();
            BlockPos West = new BlockPos(2, 0, 0);
            BlockPos North = new BlockPos(1, 0, 1);
            BlockPos East = new BlockPos(0, 0, 0);
            BlockPos South = new BlockPos(1, 0, -1);

            faces.add(West);
            faces.add(North);
            faces.add(East);
            faces.add(South);
            return faces;
        }else{
            return null;
        }
    }
    private static boolean isBlastFurnace(BlockPos blockPositionClicked, UseOnContext pContext) {
        Block block = pContext.getLevel().getBlockState(blockPositionClicked).getBlock();
        return block == Blocks.BLAST_FURNACE;
    }
    private static int checkBlockType(BlockPos blockPositionClicked, UseOnContext pContext) {
        Block block = pContext.getLevel().getBlockState(blockPositionClicked).getBlock();


        if(block == Blocks.BLAST_FURNACE){
            return 1;
        }else if(block == Blocks.SMOOTH_STONE){
            return 2;
        }else if(block == Blocks.HOPPER){
            return 3;
        }else if(block.equals(ModBlocks.SMOOTH_STONE_CONNECTOR.get())){
            return 4;
        }else if(block.equals(ModBlocks.SMALL_ELECTRODES.get())){
            return 5;
        }
        return 0;
    }
}
