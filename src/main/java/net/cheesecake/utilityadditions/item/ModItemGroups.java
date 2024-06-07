package net.cheesecake.utilityadditions.item;

import net.cheesecake.utilityadditions.UtilityAdditions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ETERNALITE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(UtilityAdditions.MOD_ID, "eternalite"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.eternalite"))
                    .icon(() -> new ItemStack(ModItems.ETERNALITE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.DAWNGEM);
                        entries.add(ModItems.ETERNALITE); //TODO: make and add the textures!!



                    }).build());

    public static void registerItemGroups() {
        UtilityAdditions.LOGGER.info("Registering Item Groups for " + UtilityAdditions.MOD_ID);
    }
}
