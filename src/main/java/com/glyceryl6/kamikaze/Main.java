package com.glyceryl6.kamikaze;

import com.glyceryl6.kamikaze.registry.KEAttachmentTypes;
import com.glyceryl6.kamikaze.registry.KEEnchantmentEffectTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import java.util.Locale;

@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "kamikaze_elytra";

    public Main(IEventBus modEventBus) {
        KEAttachmentTypes.ATTACHMENT_TYPES.register(modEventBus);
        KEEnchantmentEffectTypes.ENCHANTMENT_ENTITY_EFFECT_TYPES.register(modEventBus);
    }

    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name.toLowerCase(Locale.ROOT));
    }

}