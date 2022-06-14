package com.sliceclient.util.account;

import com.sliceclient.minecraft.session.Session;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MicrosoftAccount {
    private MinecraftProfile profile;
    private Session session;
    private String refreshToken;
}
