package net.cheesecake.utilityadditions.mixin;

import net.cheesecake.utilityadditions.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
	@Shadow private String newItemName;
	@Shadow private final Property levelCost;

    protected AnvilScreenHandlerMixin(Property levelCost) {
        this.levelCost = levelCost;
    }

    @Shadow protected abstract void updateResult();

	@Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
	private void updateResultMixin(CallbackInfo info) {
		DefaultedList<Slot> player = ((AnvilScreenHandler) (Object) this).slots;
		ItemStack leftStack = ((AnvilScreenHandler) (Object) this).slots.get(0).getStack();
		ItemStack rightStack = ((AnvilScreenHandler) (Object) this).slots.get(1).getStack();

		if (!leftStack.isEmpty() && !rightStack.isEmpty() && rightStack.getItem() == ModItems.ETERNALITE) {
			ItemStack resultStack = leftStack.copy();
            resultStack.getOrCreateNbt().putBoolean("Indestructible", true);
			((AnvilScreenHandler) (Object) this).slots.get(2).setStack(resultStack);

			this.levelCost.set(1);

			info.cancel();
		}
	}

	@Inject(method = "onTakeOutput", at = @At("HEAD"))
	private void onTakeOutputMixin(PlayerEntity player, ItemStack stack, CallbackInfo info) {
		if (stack.getOrCreateNbt().getBoolean("Indestructible")) {
			((AnvilScreenHandler) (Object) this).slots.get(0).takeStack(1);
			((AnvilScreenHandler) (Object) this).slots.get(1).takeStack(1);
		}
	}
}