package com.rocket.delta.module;

import com.rocket.delta.event.EventManager;
import com.rocket.delta.event.impl.PacketEvent;
import com.rocket.delta.event.impl.Render2DEvent;
import com.rocket.delta.event.impl.TickEvent;
import com.rocket.delta.module.impl.combat.*;
import com.rocket.delta.module.impl.movement.*;
import com.rocket.delta.module.impl.render.*;
import com.rocket.delta.module.impl.player.*;
import com.rocket.delta.module.impl.misc.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager(EventManager events) {
        modules.add(new KillAuraModule());
        modules.add(new VelocityModule());
        modules.add(new AutoTotemModule());
        modules.add(new FlyModule());
        modules.add(new SpeedModule());
        modules.add(new NoFallModule());
        modules.add(new GuiMoveModule());
        modules.add(new FullBrightModule());
        modules.add(new NoRenderModule());
        modules.add(new EspModule());
        modules.add(new XrayModule());
        modules.add(new HudModule());
        modules.add(new FastBreakModule());
        modules.add(new AutoToolModule());
        modules.add(new AutoFarmModule());
        modules.add(new AutoBrewModule());
        modules.add(new AntiAfkModule());
        modules.add(new CaptchaSolverModule());
        modules.add(new GpsModule());
        modules.add(new DeathCoordsModule());

        events.register(TickEvent.class, e -> { for (Module m : modules) if (m.isEnabled()) m.onTick(); });
        events.register(Render2DEvent.class, e -> { for (Module m : modules) if (m.isEnabled()) m.onRender2D(e.context); });
        events.register(PacketEvent.class, e -> { for (Module m : modules) if (m.isEnabled()) m.onPacket(e); });
    }

    public List<Module> getAll() { return modules; }

    public Module getByName(String name) {
        for (Module m : modules) if (m.getName().equalsIgnoreCase(name)) return m;
        return null;
    }

    public List<Module> getByCategory(Category c) {
        return modules.stream().filter(m -> m.getCategory() == c).toList();
    }
}
