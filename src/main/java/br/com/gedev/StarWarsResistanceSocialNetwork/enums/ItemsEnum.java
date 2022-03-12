package br.com.gedev.StarWarsResistanceSocialNetwork.enums;

import java.util.UUID;

public enum ItemsEnum {
    ARMA("ad2d1795-ff26-41b7-be9e-c34b80deec28"),
    MUNICAO("53c5d39a-c430-4d73-a097-768783c6482d"),
    AGUA("cc88f19d-0648-4518-b0ab-519c1c7f669b"),
    COMIDA("2be1df29-8a46-4221-91a0-9cb3c3f2e8ff");

    private final UUID uuid;

    ItemsEnum(String uuidString) {
        uuid = UUID.fromString(uuidString);
    }

    public UUID getUUID() {
        return uuid;
    }
}
