package com.github.chainmailstudios.astromine.common.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DimensionLayerRegistry {
	public enum Type {
		TOP,
		BOTTOM
	}

	private final Map<RegistryKey<DimensionType>, Pair<Integer, RegistryKey<DimensionType>>> TOP_ENTRIES = new HashMap<>();
	private final Map<RegistryKey<DimensionType>, Pair<Integer, RegistryKey<DimensionType>>> BOTTOM_ENTRIES = new HashMap<>();

	private DimensionLayerRegistry() {
		// Unused.
	}

	public static final DimensionLayerRegistry INSTANCE = new DimensionLayerRegistry();

	public void register(Type type, RegistryKey<DimensionType> dimension, Integer levelY, RegistryKey<DimensionType> newDimension) {
		final Map<RegistryKey<DimensionType>, Pair<Integer, RegistryKey<DimensionType>>> ENTRIES = type == Type.TOP ? TOP_ENTRIES : BOTTOM_ENTRIES;

		ENTRIES.put(dimension, new Pair<>(levelY, newDimension));
	}

	public int getLevel(Type type, RegistryKey<DimensionType> dimension) {
		final Map<RegistryKey<DimensionType>, Pair<Integer, RegistryKey<DimensionType>>> ENTRIES = type == Type.TOP ? TOP_ENTRIES : BOTTOM_ENTRIES;

		final Pair<Integer, RegistryKey<DimensionType>> pair = ENTRIES.get(dimension);

		return pair == null ? Integer.MIN_VALUE : pair.getLeft();
	}

	public RegistryKey<DimensionType> getDimension(Type type, RegistryKey<DimensionType> dimension) {
		final Map<RegistryKey<DimensionType>, Pair<Integer, RegistryKey<DimensionType>>> ENTRIES = type == Type.TOP ? TOP_ENTRIES : BOTTOM_ENTRIES;

		final Pair<Integer, RegistryKey<DimensionType>> pair = ENTRIES.get(dimension);

		return pair == null ? null : pair.getRight();
	}
}
