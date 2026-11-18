package com.rocket.delta.config;

import com.google.gson.*;
import com.rocket.delta.module.Module;
import com.rocket.delta.module.ModuleManager;
import com.rocket.delta.setting.Setting;
import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private final ModuleManager modules;
    private final Path file = FabricLoader.getInstance().getConfigDir().resolve("rocketdelta.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager(ModuleManager modules) { this.modules = modules; }

    public void save() {
        try {
            JsonObject root = new JsonObject();
            for (Module m : modules.getAll()) {
                JsonObject o = new JsonObject();
                o.addProperty("enabled", m.isEnabled());
                o.addProperty("key", m.getKey());
                JsonObject s = new JsonObject();
                for (Setting st : m.getSettings()) s.add(st.getName(), st.save());
                o.add("settings", s);
                root.add(m.getName(), o);
            }
            Files.writeString(file, gson.toJson(root));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void load() {
        if (!Files.exists(file)) return;
        try {
            JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            for (Module m : modules.getAll()) {
                if (!root.has(m.getName())) continue;
                JsonObject o = root.getAsJsonObject(m.getName());
                if (o.has("key")) m.setKey(o.get("key").getAsInt());
                if (o.has("settings")) {
                    JsonObject s = o.getAsJsonObject("settings");
                    for (Setting st : m.getSettings()) if (s.has(st.getName())) st.load(s.get(st.getName()));
                }
                if (o.has("enabled")) m.setEnabled(o.get("enabled").getAsBoolean());
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
