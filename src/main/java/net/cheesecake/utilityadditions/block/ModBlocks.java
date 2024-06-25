package net.cheesecake.utilityadditions.block;

import net.cheesecake.utilityadditions.UtilityAdditions;
import net.cheesecake.utilityadditions.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block DEEPSLATE_DAWN_ORE = registerBlock("deepslate_dawn_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE)));//TODO add a texture

    private static void addOreBlocksToNaturalTabItemGroup (FabricItemGroupEntries entries) {
        entries.add(ModBlocks.DEEPSLATE_DAWN_ORE);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(UtilityAdditions.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(UtilityAdditions.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        UtilityAdditions.LOGGER.info("Registering ModBlocks for "+UtilityAdditions.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModBlocks::addOreBlocksToNaturalTabItemGroup);
    }
}
