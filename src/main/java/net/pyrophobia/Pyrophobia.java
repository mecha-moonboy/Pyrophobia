package net.pyrophobia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.GameRules;
import net.pyrophobia.block.ModBlocks;

public class Pyrophobia implements ModInitializer {
    public  static final String MOD_ID = "pyrophobia";
    public static final GameRules.Key<GameRules.IntRule> FIRE_MAX_DELAY =
            GameRuleRegistry.register("fireMaxDelay", GameRules.Category.UPDATES, GameRuleFactory.createIntRule(100, 1));
    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();

        // check if the file exists, if not, create one
        ModConfig config = ModConfig.readConfig();

        // write to config
        config.writeConfig();

        FlammableBlockRegistry.getDefaultInstance().add(BlockTags.LEAVES, 20, 10);
        FlammableBlockRegistry.getDefaultInstance().add(BlockTags.LOGS, 5, 300);
        FlammableBlockRegistry.getDefaultInstance().add(BlockTags.SAPLINGS, 20, 10);
        FlammableBlockRegistry.getDefaultInstance().add(Blocks.GRASS_BLOCK, 300, 30);
        FlammableBlockRegistry.getDefaultInstance().add(Blocks.PODZOL, 200, 20);
    }
}
