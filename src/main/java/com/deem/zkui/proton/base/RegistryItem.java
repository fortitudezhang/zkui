package com.deem.zkui.proton.base;

/**
 * Created by hzzhangdongya on 16-11-4.
 */
public class RegistryItem {
    public String fullpathRegex = null;
    public ProtonSerializer serializer = null;
    public ProtonDeserializer deserializer = null;

    public RegistryItem(String fullPathRegex, ProtonSerializer serializer, ProtonDeserializer deserializer) {
        this.fullpathRegex = fullPathRegex;
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public String getFullPathRegex() {
        return this.fullpathRegex;
    }

    public ProtonSerializer getSerializer() {
        return this.serializer;
    }

    public ProtonDeserializer getDeserializer() {
        return this.deserializer;
    }
}
