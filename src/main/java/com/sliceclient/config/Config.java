package com.sliceclient.config;

import com.sliceclient.Slice;
import com.sliceclient.util.LoginUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Objects;

@Getter @Setter
public class Config {

    private File configFile;

    public Config() {
        configFile = new File(System.getProperty("user.home") + File.separator + "Slice" + File.separator + "config.json");
        Runtime.getRuntime().addShutdownHook(new Thread(this::save));
        if(configFile.exists()) load();
    }

    private void load() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(":");

                if(split[1].equalsIgnoreCase("0") || split[0].equalsIgnoreCase("Player"))
                    return;

                Slice.INSTANCE.setSession(Objects.requireNonNull(LoginUtil.loginMicrosoft(split[1])).getSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(configFile);
            fw.write(Slice.INSTANCE.getSession().getName() + ":" + Slice.INSTANCE.getSession().getRefreshToken());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fw != null) fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
