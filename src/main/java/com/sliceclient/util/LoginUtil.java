package com.sliceclient.util;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.sliceclient.minecraft.session.Session;
import com.sliceclient.util.account.MicrosoftAccount;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import lombok.experimental.UtilityClass;

import java.net.Proxy;

@UtilityClass
public class LoginUtil {

    /**
     * Logins in using email and password.
     * */
    public static Session loginMojang(String email, String password) {
        final YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service.createUserAuthentication(Agent.MINECRAFT);

        auth.setUsername(email);
        auth.setPassword(password);

        try {
            auth.logIn();
            return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Logins user in using Microsoft Authenticator.
     */
    public static MicrosoftAccount loginMicrosoft(String email, String password) {
        try {
            System.out.println( email + ":" + password);
            MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
            MicrosoftAuthResult result = authenticator.loginWithCredentials(email, password);
            MinecraftProfile profile = result.getProfile();

            Session session = new Session(profile.getName(), profile.getId(), result.getAccessToken(), "mojang", result.getRefreshToken());
            return new MicrosoftAccount(profile, session, result.getRefreshToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Logins user in using Microsoft Authenticator.
     * with refresh token.
     */
    public static MicrosoftAccount loginMicrosoft(String refreshToken) {
        try {
            MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
            MicrosoftAuthResult result = authenticator.loginWithRefreshToken(refreshToken);
            MinecraftProfile profile = result.getProfile();

            Session session = new Session(profile.getName(), profile.getId(), result.getAccessToken(), "mojang", result.getRefreshToken());
            return new MicrosoftAccount(profile, session, result.getRefreshToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
