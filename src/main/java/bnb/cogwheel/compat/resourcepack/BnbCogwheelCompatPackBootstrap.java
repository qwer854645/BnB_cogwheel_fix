package bnb.cogwheel.compat.resourcepack;

import bnb.cogwheel.compat.BnbCogwheelCompatMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;

/**
 * Bits n Bobs 2.2+ rewrites {@code create:block/cogwheel} texture slots.
 * This mod auto-enables a TOP built-in pack that retargets Create / Northstar /
 * Fantasizing cogwheels onto {@code bits_n_bobs:block/default_cogwheel*}.
 */
public final class BnbCogwheelCompatPackBootstrap {
    public static final String BNB_MOD_ID = "bits_n_bobs";

    private static final ResourceLocation PACK_LOCATION = ResourceLocation.fromNamespaceAndPath(
            BnbCogwheelCompatMod.MOD_ID,
            "resourcepacks/bnb_cogwheel_compat"
    );

    private BnbCogwheelCompatPackBootstrap() {
    }

    public static void register(IEventBus modEventBus) {
        if (FMLEnvironment.dist != Dist.CLIENT) {
            return;
        }
        modEventBus.addListener(BnbCogwheelCompatPackBootstrap::onAddPackFinders);
        modEventBus.addListener(BnbCogwheelCompatPackBootstrap::onRegisterReloadListeners);
    }

    private static void onAddPackFinders(AddPackFindersEvent event) {
        if (!ModList.get().isLoaded(BNB_MOD_ID)) {
            return;
        }
        if (event.getPackType() != PackType.CLIENT_RESOURCES) {
            return;
        }

        event.addPackFinders(
                PACK_LOCATION,
                PackType.CLIENT_RESOURCES,
                Component.translatable("resourcepack.bnb_cogwheel_compat.pack"),
                PackSource.BUILT_IN,
                true,
                Pack.Position.TOP
        );
        BnbCogwheelCompatMod.LOGGER.info(
                "Bits n Bobs detected — enabling cogwheel model compat "
                        + "(Create texture packs + Northstar + Fantasizing)"
        );
    }

    private static void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
        if (!ModList.get().isLoaded(BNB_MOD_ID)) {
            return;
        }
        event.registerReloadListener(new BnbCogwheelTextureProbe());
    }
}
