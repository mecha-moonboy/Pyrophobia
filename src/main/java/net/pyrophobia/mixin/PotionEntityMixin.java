package net.pyrophobia.mixin;

import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionEntity.class)
public abstract class PotionEntityMixin {

    int extinguishDistance = 5;

    @Shadow protected abstract void extinguishFire(BlockPos pos);

    @Inject(method = "onBlockHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/thrown/PotionEntity;extinguishFire(Lnet/minecraft/util/math/BlockPos;)V"))
    void extinguishArea(BlockHitResult blockHitResult, CallbackInfo ci){
        BlockPos blockPos = blockHitResult.getBlockPos();

        for(int x = -extinguishDistance; x <= extinguishDistance; x++){
            for(int y = -extinguishDistance; y <= extinguishDistance; y++){
                for(int z = -extinguishDistance; z <= extinguishDistance; z++){
                    this.extinguishFire(blockPos.add(x, y, z));
                }
            }
        }
    }
}
