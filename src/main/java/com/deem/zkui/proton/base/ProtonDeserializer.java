package com.deem.zkui.proton.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Created by hzzhangdongya on 16-11-4.
 */
public abstract class ProtonDeserializer<T> {
    private ObjectMapper mapper = new ObjectMapper();

    public ProtonDeserializer() {
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }

    public ObjectWriter getWriter() {
        return this.mapper.writerWithDefaultPrettyPrinter();
    }
    public abstract String toJson(byte[] data);
}
