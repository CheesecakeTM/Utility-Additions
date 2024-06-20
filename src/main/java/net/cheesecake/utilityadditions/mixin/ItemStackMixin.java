package net.cheesecake.utilityadditions.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damage(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        NbtCompound nbt = stack.getNbt();

        if (nbt != null && nbt.getBoolean("Indestructible")) {
            // Prevent item from losing durability
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "isItemBarVisible", at = @At("HEAD"), cancellable = true)
    private void isItemBarVisible(CallbackInfoReturnable<Boolean> cir){
        ItemStack stack = (ItemStack) (Object) this;
        NbtCompound nbt = stack.getNbt();

        if (nbt != null && nbt.getBoolean("Indestructible")) {
            // Prevent the item bar from being visible
            cir.setReturnValue(false);
        }
    }


    @Inject(method = "isDamageable", at = @At("HEAD"), cancellable = true)
    private void isDamageable(CallbackInfoReturnable<Boolean> cir){
        ItemStack stack = (ItemStack) (Object) this;
        NbtCompound nbt = stack.getNbt();

        if ( nbt != null && nbt.getBoolean("Indestructible")){
            cir.setReturnValue(false);
        }
    }
}
