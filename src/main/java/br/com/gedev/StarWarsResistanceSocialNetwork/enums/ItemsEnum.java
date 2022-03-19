package br.com.gedev.StarWarsResistanceSocialNetwork.enums;

import java.util.UUID;

public enum ItemsEnum {
    ARMA("ad2d1795-ff26-41b7-be9e-c34b80deec28", "Arma"),
    MUNICAO("53c5d39a-c430-4d73-a097-768783c6482d", "Munição"),
    AGUA("cc88f19d-0648-4518-b0ab-519c1c7f669b", "Água"),
    COMIDA("2be1df29-8a46-4221-91a0-9cb3c3f2e8ff", "Comida");

    private final UUID uuid;
    private final String itemName;

    ItemsEnum(String uuidString, String name) {
        uuid = UUID.fromString(uuidString);
        itemName = name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getItemName() {
        return itemName;
    }
}
