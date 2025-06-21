package com.tristankechlo.whatdidijustkill;

import com.tristankechlo.whatdidijustkill.command.WhatDidIJustKillCommand;
import com.tristankechlo.whatdidijustkill.network.ForgePacketHandler;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WhatDidIJustKill.MOD_ID)
public class ForgeWhatDidIJustKill {

    public ForgeWhatDidIJustKill(FMLJavaModLoadingContext context) {
        FMLCommonSetupEvent.getBus(context.getModBusGroup()).addListener(this::commonSetup);
        RegisterClientCommandsEvent.BUS.addListener(this::registerCommands);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ForgePacketHandler.registerPackets();
    }

    private void registerCommands(RegisterClientCommandsEvent event) {
        WhatDidIJustKillCommand.register(event.getDispatcher());
    }

}
