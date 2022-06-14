package com.sliceclient.minecraft.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Session {
    private String name, uuid, accessToken, userType;
}