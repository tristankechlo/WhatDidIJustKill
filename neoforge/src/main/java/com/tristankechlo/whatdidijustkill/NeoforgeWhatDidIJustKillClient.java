package com.tristankechlo.whatdidijustkill;

import com.mojang.blaze3d.platform.InputConstants;
import com.tristankechlo.whatdidijustkill.client.ToastHandler;
import com.tristankechlo.whatdidijustkill.config.ConfigManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = WhatDidIJustKill.MOD_ID, value = Dist.CLIENT)
public class NeoforgeWhatDidIJustKillClient {

    public static final Lazy<KeyMapping> KEYMAPPING = Lazy.of(() -> new KeyMapping(
            "key.whatdidijustkill.toggle_toasts",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            WhatDidIJustKill.KEY_CATEGORY
    ));

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        ConfigManager.loadAndVerifyConfig();
    }

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(KEYMAPPING.get());
    }

    @EventBusSubscriber(modid = WhatDidIJustKill.MOD_ID, value = Dist.CLIENT)
    private static class ClientTick {

        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            while (KEYMAPPING.get().consumeClick() && Minecraft.getInstance().hasControlDown()) {
                ToastHandler.toggleVisibility(Minecraft.getInstance());
            }
        }
    }

}
