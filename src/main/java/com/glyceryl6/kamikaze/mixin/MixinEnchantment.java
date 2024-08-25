package com.glyceryl6.kamikaze.mixin;

import com.glyceryl6.kamikaze.registry.KEEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class MixinEnchantment {

    @Shadow @Final private Component description;

    @Inject(method = "isSupportedItem", at = @At(value = "HEAD"), cancellable = true)
    public void isSupportedItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (this.description.getContents() instanceof TranslatableContents contents) {
            if (contents.getKey().equals("enchantment.kamikaze_elytra.kamikaze_elytra")) {
                cir.setReturnValue(stack.getItem() instanceof ElytraItem);
            }
        }
    }

    @Inject(method = "getFullname", at = @At(value = "HEAD"), cancellable = true)
    private static void getFullname(Holder<Enchantment> enchantment, int level, CallbackInfoReturnable<Component> cir) {
        MutableComponent component = enchantment.value().description().copy();
        if (enchantment.is(KEEnchantments.KAMIKAZE_ELYTRA)) {
            cir.setReturnValue(component.withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));
        }
    }

}