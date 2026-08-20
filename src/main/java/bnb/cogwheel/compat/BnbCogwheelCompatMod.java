package bnb.cogwheel.compat;

import bnb.cogwheel.compat.resourcepack.BnbCogwheelCompatPackBootstrap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(BnbCogwheelCompatMod.MOD_ID)
public class BnbCogwheelCompatMod {
    public static final String MOD_ID = "bnb_cogwheel_compat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public BnbCogwheelCompatMod(IEventBus modEventBus) {
        BnbCogwheelCompatPackBootstrap.register(modEventBus);
    }
}
