package com.tristankechlo.whatdidijustkill;

import java.nio.file.Path;

public interface IPlatformHelper {

    IPlatformHelper INSTANCE = WhatDidIJustKill.load(IPlatformHelper.class);

    Path getConfigDirectory();

    boolean isModLoaded(String modid);

}
