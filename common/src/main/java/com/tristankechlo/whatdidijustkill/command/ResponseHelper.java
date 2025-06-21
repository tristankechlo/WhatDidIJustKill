package com.tristankechlo.whatdidijustkill.command;


import com.tristankechlo.whatdidijustkill.WhatDidIJustKill;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.net.URI;
import java.net.URISyntaxException;

public final class ResponseHelper {

    public static void sendMessageConfigReload(CommandSourceStack source, boolean success) {
        String text = success ? "Config was successfully reloaded." : "Error while reloading config. Check the logs for further details.";
        MutableComponent message = Component.literal(text).withStyle(ChatFormatting.WHITE);
        sendMessage(source, message, true);
    }

    public static void sendMessageConfigReset(CommandSourceStack source, boolean success) {
        String text = success ? "Config was successfully reset." : "Error while saving the default config.";
        MutableComponent message = Component.literal(text).withStyle(ChatFormatting.WHITE);
        sendMessage(source, message, true);
    }

    public static MutableComponent start() {
        return Component.literal("[" + WhatDidIJustKill.MOD_NAME + "] ").withStyle(ChatFormatting.GOLD);
    }

    public static void sendMessage(CommandSourceStack source, Component message, boolean broadcastToOps) {
        MutableComponent start = start().append(message);
        source.sendSuccess(() -> start, broadcastToOps);
    }

    public static MutableComponent clickableLink(String url, String displayText) throws URISyntaxException {
        MutableComponent mutableComponent = Component.literal(displayText);
        mutableComponent.withStyle(ChatFormatting.GREEN, ChatFormatting.UNDERLINE);
        URI parsedUrl = new URI(url);
        mutableComponent.withStyle(style -> style.withClickEvent(new ClickEvent.OpenUrl(parsedUrl)));
        return mutableComponent;
    }

    public static MutableComponent clickableLink(String url) {
        try {
            return clickableLink(url, url);
        } catch (URISyntaxException e) {
            WhatDidIJustKill.LOGGER.error("Failed to create clickable link for URL: {}", url, e);
            throw new RuntimeException(e);
        }
    }

}
