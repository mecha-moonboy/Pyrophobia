package net.pyrophobia.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args= {
                                    "stringValue=fire"
                            }
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/AbstractBlock$Settings;breakInstantly()Lnet/minecraft/block/AbstractBlock$Settings;"
            ),
            method = "<clinit>"
    )
    private static AbstractBlock.Settings fireNoBreakInstantly(AbstractBlock.Settings settings) {
        settings.strength(1.5f);
        return settings;
    }
}
