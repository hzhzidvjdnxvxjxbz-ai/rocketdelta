package com.rocket.delta.module.impl.misc;

import com.rocket.delta.module.Category;
import com.rocket.delta.module.Module;

public class GpsModule extends Module {
    public GpsModule() { super("GPS", Category.MISC); }

    // TODO: стрелка-указатель до заданных координат
    @Override public void onTick() {}
}
