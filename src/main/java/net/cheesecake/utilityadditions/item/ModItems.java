package net.cheesecake.utilityadditions.item;

import net.cheesecake.utilityadditions.UtilityAdditions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ETERNALITE = registerItem("eternalite", new Item(new FabricItemSettings()));
    public static final Item DAWNGEM = registerItem("dawngem", new Item(new FabricItemSettings()));
    public static final Item ENRICHED_IRON_INGOT = registerItem("enriched_iron_ingot", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(ETERNALITE);
        entries.add(DAWNGEM);
        entries.add(ENRICHED_IRON_INGOT);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(UtilityAdditions.MOD_ID, name), item);
    }

    public static void registerModItems(){
        UtilityAdditions.LOGGER.info("Registering Mod Items for " + UtilityAdditions.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}
