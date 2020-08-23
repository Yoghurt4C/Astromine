package com.github.chainmailstudios.astromine.technologies.common.generator.recipe;

import com.github.chainmailstudios.astromine.common.generator.material.MaterialItemType;
import com.github.chainmailstudios.astromine.common.generator.material.MaterialSet;
import com.github.chainmailstudios.astromine.common.generator.recipe.base.EnergyProcessingRecipeGenerator;
import com.github.chainmailstudios.astromine.common.recipe.TrituratingRecipe;
import com.github.chainmailstudios.astromine.common.utilities.GeneratorUtilities;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.shedaniel.cloth.api.datagen.v1.RecipeData;

public class TrituratingRecipeGenerator extends EnergyProcessingRecipeGenerator {
	public TrituratingRecipeGenerator(MaterialItemType input, int inputCount, MaterialItemType output, int outputCount, int time, int energyConsumed) {
		super(input, inputCount, output, outputCount, time, energyConsumed);
	}

	public TrituratingRecipeGenerator(MaterialItemType input, MaterialItemType output, int outputCount, int time, int energyConsumed) {
		this(input, 1, output, outputCount, time, energyConsumed);
	}

	public TrituratingRecipeGenerator(MaterialItemType input, MaterialItemType output, int time, int energyConsumed) {
		this(input, 1, output, 1, time, energyConsumed);
	}

	@Override
	public String getRecipeName(MaterialSet set) {
		return set.getItemIdPath(output) + "_from_triturating_" + input.getName();
	}

	@Override
	public void generate(RecipeData recipes, MaterialSet set) {
		recipes.accept(GeneratorUtilities.Providers.createProvider(TrituratingRecipe.Serializer.INSTANCE, getRecipeId(set), json -> {
			JsonElement inputJson = set.getIngredient(input).toJson();
			if (inputJson.isJsonObject()) {
				inputJson.getAsJsonObject().addProperty("count", inputCount);
			}

			json.add("input", inputJson);

			JsonObject outputJson = new JsonObject();
			outputJson.addProperty("item", set.getItemId(output).toString());
			outputJson.addProperty("count", outputCount);

			json.add("output", outputJson);
			json.addProperty("time", time);
			json.addProperty("energy_consumed", energyConsumed);
		}));
	}

	@Override
	public String getGeneratorName() {
		return "triturating";
	}
}