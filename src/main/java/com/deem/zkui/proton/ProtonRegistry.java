package com.deem.zkui.proton;

import com.deem.zkui.proton.base.RegistryItem;
import com.deem.zkui.proton.impl.AgentDataDeserializer;
import com.deem.zkui.proton.impl.AgentDataSerializer;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by hzzhangdongya on 16-11-4.
 */
public class ProtonRegistry {
    // TODO: organize as a tree each node contains fixed string or regex pattern string.
    private ArrayList<RegistryItem> registryItems = new ArrayList<>();
    private static ProtonRegistry instance = null;

    private ProtonRegistry initRegistry() {
        RegistryItem item = null;

        // Match xxx-xxx-xxx-xxx style of host identifier simply.
        item = new RegistryItem(
                "/cns/agents/access/[0-9|-]+/agent_data",
                new AgentDataSerializer(),
                new AgentDataDeserializer());
        registryItems.add(item);
        return this;
    }

    public static ProtonRegistry getRegistry() {
        if (instance == null) {
            return new ProtonRegistry().initRegistry();
        } else {
            return instance;
        }
    }

    public RegistryItem getRegistryItem(String fullPath) {
        RegistryItem item = null;

        for (RegistryItem i : registryItems) {
            if (Pattern.matches(i.getFullPathRegex(), fullPath))
                item = i;
                break;
        }

        return item;
    }
}
