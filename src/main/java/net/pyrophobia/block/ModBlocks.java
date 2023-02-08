package net.pyrophobia.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pyrophobia.Pyrophobia;


public class ModBlocks {
    public static Block ASHEN_DIRT;
    public static void registerModBlocks(){
        ASHEN_DIRT = registerBlock(
                "ashen_dirt",
                new Block(
                        FabricBlockSettings
                                .of(Material.SOIL)
                                .requiresTool()
                                .sounds(BlockSoundGroup.GRAVEL)
                                .strength(0.5f)
                ),
                ItemGroup.DECORATIONS
        );
    }


    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Pyrophobia.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup group){
        Registry.register(
                Registry.ITEM,
                new Identifier(Pyrophobia.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group))
        );
    }
}
