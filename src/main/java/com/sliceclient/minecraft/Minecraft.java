package com.sliceclient.minecraft;

import com.sliceclient.Slice;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Minecraft {

    @Getter
    private static final String jarPath = System.getProperty("user.home") + File.separator + "Slice" + File.separator + "versions" + File.separator + "Slice" + File.separator + "Slice.jar",
        libDir = System.getProperty("user.home") + File.separator + "Slice" + File.separator + "lib";


    public static void start() {
        new Thread(() -> {
            try {
                String command = "java -"
                        + "Xms1024M "
                        + "-Xmx4096M "
                        + "-Djava.library.path=\"" + libDir + File.separator + "natives" + "\" "
                        + "-cp \"" + libDir + File.separator + "*" + ";" + jarPath + "\" "
                        + "net.minecraft.client.main.Main "
                        + "--username " + Slice.INSTANCE.getSession().getName() + " "
                        + "--version " + "vanilla "
                        + "--gameDir \"" + System.getProperty("user.home") + File.separator + "Slice" + "\" "
                        + "--assetsDir \"" + MinecraftDirectory.getDirectory() + File.separator + "assets" + "\" "
                        + "--uuid " + Slice.INSTANCE.getSession().getUuid() + " "
                        + "--accessToken " + Slice.INSTANCE.getSession().getAccessToken() + " "
                        + "--assetIndex " + "1.8 "
                        + "--userType " + Slice.INSTANCE.getSession().getUserType() + " "
                        + "--userProperties " + "{} ";

                System.out.println(command);
                Process process = Runtime.getRuntime().exec(command);

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();

                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while ((line = errorReader.readLine()) != null) {
                    System.err.println(line);
                }
                errorReader.close();
            } catch (Exception ignored) {
            }
        }).start();
    }
}
