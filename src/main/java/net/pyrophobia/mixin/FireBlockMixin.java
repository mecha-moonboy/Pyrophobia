package net.pyrophobia.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pyrophobia.Pyrophobia;
import net.pyrophobia.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin extends AbstractBlockMixin{

    @WrapOperation(method = "trySpreadingFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    boolean redirectSetIfDirt(World instance, BlockPos pos, BlockState state, int flags, Operation<Boolean> original){
        if(instance.getBlockState(pos).getBlock() instanceof GrassBlock){
            return instance.setBlockState(pos, ModBlocks.ASHEN_DIRT.getDefaultState(), flags);
        }else{
            return original.call(instance, pos, state, flags);
        }
    }

    @WrapOperation(method = "trySpreadingFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;removeBlock(Lnet/minecraft/util/math/BlockPos;Z)Z"))
    boolean redirectRemoveIfDirt(World instance, BlockPos pos, boolean move, Operation<Boolean> original){
        if(instance.getBlockState(pos).getBlock() instanceof GrassBlock){
            return instance.setBlockState(pos, ModBlocks.ASHEN_DIRT.getDefaultState(), Block.NOTIFY_ALL);
        }else{
            return original.call(instance, pos, move);
        }
    }


    @WrapOperation(method = "scheduledTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;createAndScheduleBlockTick(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;I)V"))
    void wrapScheduleBlockTick(ServerWorld world, BlockPos pos, Block block, int delay, Operation original){


        int maxDelay = world.getGameRules().getInt(Pyrophobia.FIRE_MAX_DELAY);
        int randomDelay = world.random.nextInt(maxDelay);
        original.call(world, pos, this, randomDelay);


    }

}
