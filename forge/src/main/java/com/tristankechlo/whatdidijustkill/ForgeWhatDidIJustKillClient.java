package com.tristankechlo.whatdidijustkill;

import com.mojang.blaze3d.platform.InputConstants;
import com.tristankechlo.whatdidijustkill.client.ToastHandler;
import com.tristankechlo.whatdidijustkill.config.ConfigManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = WhatDidIJustKill.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeWhatDidIJustKillClient {

    public static final Lazy<KeyMapping> KEYMAPPING = Lazy.of(() -> new KeyMapping(
            "key.whatdidijustkill.toggle_toasts",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            WhatDidIJustKill.KEY_CATEGORY
    ));

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ConfigManager.loadAndVerifyConfig();
    }

    @Mod.EventBusSubscriber(modid = WhatDidIJustKill.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    private static class ClientTick {

        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.register(KEYMAPPING.get());
        }

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent.Post event) {
            while (KEYMAPPING.get().consumeClick() && Minecraft.getInstance().hasControlDown()) {
                ToastHandler.toggleVisibility(Minecraft.getInstance());
            }
        }
    }

}
