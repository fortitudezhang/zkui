package com.deem.zkui.proton.impl;

import com.deem.zkui.proton.base.ProtonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.protobuf.InvalidProtocolBufferException;
import com.netease.cns.proton.proto.Proton.AgentData;
import org.projectfloodlight.openflow.types.MacAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by hzzhangdongya on 16-11-4.
 */
public class AgentDataDeserializer extends ProtonDeserializer<AgentData> {
    private final static Logger logger = LoggerFactory.getLogger(AgentDataDeserializer.class);

    @Override
    public String toJson(byte[] data) {
        try {
            AgentData data_ = AgentData.parseFrom(data);
            // XXX: we can simply call data_.toString to dump String, however, some value such
            // as Mac Address will not be converted to human-readable format, that's why here
            // I prefer json as a format and do a manual conversion here.
            ObjectNode top = this.getMapper().createObjectNode();
            if (null != data_.getVmac() && 0 != data_.getVmac().size()) {
                top.put("vmac", MacAddress.of(data_.getVmac().toByteArray()).toString());
            }
            ObjectNode lvids = top.putObject("lvids");
            for (Map.Entry<Integer, String> entry: data_.getLvidsMap().entrySet()) {
                lvids.put(entry.getKey().toString(), entry.getValue());
            }
            return this.getWriter().writeValueAsString(top);
        } catch (InvalidProtocolBufferException e){
            logger.error("Failed to parse agent data to protobuf object, invalid data");
            return null;
        } catch (Exception e) {
            logger.error("Unknown exception when handling agent data protobuf object, error: " + e.getMessage());
            return null;
        }
    }
}
