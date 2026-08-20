package bnb.cogwheel.compat.resourcepack;

import bnb.cogwheel.compat.BnbCogwheelCompatMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/** Logs which packs supply Create cogwheel textures after resource reload. */
public final class BnbCogwheelTextureProbe implements ResourceManagerReloadListener {
    private static final ResourceLocation SMALL =
            ResourceLocation.fromNamespaceAndPath("create", "textures/block/cogwheel.png");
    private static final ResourceLocation LARGE =
            ResourceLocation.fromNamespaceAndPath("create", "textures/block/large_cogwheel.png");

    private static final Set<String> IGNORE_SUBSTRINGS = Set.of(
            "minecraft",
            "mod:create",
            "mod/create",
            "bits_n_bobs",
            "bnb_cogwheel_compat",
            "azimuth"
    );

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        LinkedHashSet<String> all = new LinkedHashSet<>();
        all.addAll(packIds(resourceManager, SMALL));
        all.addAll(packIds(resourceManager, LARGE));

        List<String> custom = new ArrayList<>();
        for (String id : all) {
            if (!isIgnored(id)) {
                custom.add(id);
            }
        }

        if (custom.isEmpty()) {
            BnbCogwheelCompatMod.LOGGER.info(
                    "BnB cogwheel probe: no external cogwheel texture pack detected. "
                            + "Model retarget is still active."
            );
        } else {
            BnbCogwheelCompatMod.LOGGER.info(
                    "BnB cogwheel probe: custom cogwheel texture(s) from {} pack(s): {}. "
                            + "Compat pack retargets models so these PNGs apply.",
                    custom.size(),
                    custom
            );
        }
    }

    private static List<String> packIds(ResourceManager rm, ResourceLocation id) {
        List<String> out = new ArrayList<>();
        for (Resource resource : rm.getResourceStack(id)) {
            out.add(resource.sourcePackId());
        }
        return out;
    }

    private static boolean isIgnored(String packId) {
        String id = packId.toLowerCase(Locale.ROOT);
        for (String part : IGNORE_SUBSTRINGS) {
            if (id.contains(part)) {
                return true;
            }
        }
        return false;
    }
}
