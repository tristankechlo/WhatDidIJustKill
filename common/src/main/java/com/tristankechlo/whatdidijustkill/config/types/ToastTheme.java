package com.tristankechlo.whatdidijustkill.config.types;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;

public enum ToastTheme implements StringRepresentable {

    ADVANCEMENT("ADVANCEMENT", new ResourceLocation("toast/advancement"), ChatFormatting.GRAY, ChatFormatting.DARK_GRAY, ChatFormatting.WHITE),
    RECIPE("RECIPE", new ResourceLocation("toast/recipe"), ChatFormatting.BLACK, ChatFormatting.DARK_GRAY, ChatFormatting.DARK_GRAY),
    TUTORIAL("TUTORIAL", new ResourceLocation("toast/tutorial"), ChatFormatting.BLACK, ChatFormatting.DARK_GRAY, ChatFormatting.DARK_GRAY);

    public static final Codec<ToastTheme> CODEC = StringRepresentable.fromEnum(ToastTheme::values);
    private final String key;
    private final ResourceLocation background;
    private final ChatFormatting colorText; // color to use for most of the text
    private final ChatFormatting colorEntityType; // color to use when rendering the entity_type
    private final ChatFormatting colorHighlight; // color to use for highlighting the entity name

    ToastTheme(String key, ResourceLocation background, ChatFormatting colorText, ChatFormatting colorEntityType, ChatFormatting colorHighlight) {
        this.key = key;
        this.background = background;
        this.colorText = colorText;
        this.colorEntityType = colorEntityType;
        this.colorHighlight = colorHighlight;
    }

    public ResourceLocation getBackgroundTexture() {
        return background;
    }

    public ChatFormatting getColorText() {
        return colorText;
    }

    public ChatFormatting getColorEntityType() {
        return colorEntityType;
    }

    public ChatFormatting getColorHighlight() {
        return colorHighlight;
    }

    @Override
    public String getSerializedName() {
        return this.key;
    }

}
