package com.sliceclient.minecraft;

import lombok.Getter;

import java.io.File;

@Getter
public enum MinecraftDirectory {
    WINDOWS(System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Roaming" + File.separator + ".minecraft", "windows"),
    LINUX(System.getProperty("user.home") + File.separator + ".minecraft", "linux"),
    MAC(System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator + "minecraft", "mac");

    MinecraftDirectory(String directory, String osName) {
        this.directory = directory;
    }

    public static String getDirectory() {
        String osName = System.getProperty("os.name").toLowerCase();

        if(osName.contains("windows")) return WINDOWS.directory;
        else if(osName.contains("linux")) return LINUX.directory;
        else if(osName.contains("mac")) return MAC.directory;
        else return null;
    }

    public final String directory;
}
