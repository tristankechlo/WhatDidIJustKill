package com.tristankechlo.whatdidijustkill.fabric_command;

import com.tristankechlo.whatdidijustkill.WhatDidIJustKill;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public final class FabricResponseHelper {

    public static void sendMessageConfigReload(FabricClientCommandSource source, boolean success) {
        String text = success ? "Config was successfully reloaded." : "Error while reloading config. Check the logs for further details.";
        MutableComponent message = Component.literal(text).withStyle(ChatFormatting.WHITE);
        sendMessage(source, message);
    }

    public static void sendMessageConfigReset(FabricClientCommandSource source, boolean success) {
        String text = success ? "Config was successfully reset." : "Error while saving the default config.";
        MutableComponent message = Component.literal(text).withStyle(ChatFormatting.WHITE);
        sendMessage(source, message);
    }

    public static MutableComponent start() {
        return Component.literal("[" + WhatDidIJustKill.MOD_NAME + "] ").withStyle(ChatFormatting.GOLD);
    }

    public static void sendMessage(FabricClientCommandSource source, Component message) {
        MutableComponent start = start().append(message);
        source.sendFeedback(start);
    }

    public static MutableComponent clickableLink(String url, String displayText) {
        MutableComponent mutableComponent = Component.literal(displayText);
        mutableComponent.withStyle(ChatFormatting.GREEN, ChatFormatting.UNDERLINE);
        mutableComponent.withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)));
        return mutableComponent;
    }

    public static MutableComponent clickableLink(String url) {
        return clickableLink(url, url);
    }

}
