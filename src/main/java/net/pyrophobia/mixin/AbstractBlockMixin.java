package net.pyrophobia.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {


    //@Shadow protected abstract Block asBlock();

    @Shadow @Deprecated public abstract void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @Inject(method = "onBlockAdded", at = @At(value = "TAIL"))
    public void onBlockAddedCheck(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At(value = "TAIL"))
    public void getStateForNeighborUpdateOverride(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
    }

    @Inject(method = "scheduledTick", at = @At(value = "TAIL"))
    public void scheduledTickOverride(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci){
    }

    @Inject(method = "randomTick", at = @At(value = "TAIL"))
    public void randomTickOverride(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci){

    }

}
