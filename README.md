# BnB Cogwheel Compat

NeoForge **1.21.1** client mod that fixes cogwheel textures broken by **Create: Bits 'n' Bobs 2.2+**.

## Problem

BnB 2.2+ overwrites Create's `create:block/cogwheel` models and switches gear texture slots to spruce wood. That breaks:

- Resource packs that only replace `create:block/cogwheel.png` / `large_cogwheel.png`
- Addon cogs that still parent those Create models (**Northstar** iron, **Create Fantasizing** phantom)

## Fix

When `bits_n_bobs` is loaded, this mod auto-enables a built-in **TOP** resource pack that retargets models onto `bits_n_bobs:block/default_cogwheel*` with the correct slots (`#1_2` / `#4`).

After resource reload, the log line `BnB cogwheel probe` reports external cogwheel texture pack sources.

## Covered models

| Namespace | Models |
|-----------|--------|
| `create` | `cogwheel`, `cogwheel_shaftless`, `large_cogwheel`, `large_cogwheel_shaftless` |
| `northstar` | `iron_cogwheel`, `iron_cogwheel_shaftless`, `iron_large_cogwheel` |
| `create_fantasizing` | `phantom_cogwheel`, `phantom_large_cogwheel`, `phantom_large_cogwheel_shaftless` |

## Requirements

- Minecraft 1.21.1 / NeoForge 21.1+
- Optional: Bits 'n' Bobs (mod does nothing if absent)
- Northstar / Fantasizing only needed if you use those cogs

## Build

```bat
gradlew.bat build
```

Output: `build/libs/bnb_cogwheel_compat-<version>.jar`

## Download

- Release: https://github.com/qwer854645/BnB_cogwheel_fix/releases/tag/v1.0.0
- Also in repo: [`dist/bnb_cogwheel_compat-1.0.0.jar`](https://github.com/qwer854645/BnB_cogwheel_fix/tree/main/dist)

## In-game

Keep the built-in pack enabled:

- EN: **BnB Cogwheel Compat (Create / Northstar / Fantasizing)**
- ZH: **BnB 齿轮材质兼容（Create / Northstar / Fantasizing）**
