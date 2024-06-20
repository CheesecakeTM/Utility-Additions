package net.cheesecake.utilityadditions;

import net.cheesecake.utilityadditions.block.ModBlocks;
import net.cheesecake.utilityadditions.item.ModItemGroups;
import net.cheesecake.utilityadditions.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityAdditions implements ModInitializer {
	public static final String MOD_ID = "utilityadditions";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}