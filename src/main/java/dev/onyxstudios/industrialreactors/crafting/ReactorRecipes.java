package dev.onyxstudios.industrialreactors.crafting;

import dev.onyxstudios.industrialreactors.IndustrialReactors;
import dev.onyxstudios.industrialreactors.init.ReactorItems;
import dev.onyxstudios.industrialreactors.tag.c.SharedItemTags;
import io.github.glasspane.mesh.api.annotation.CalledByReflection;
import io.github.glasspane.mesh.api.crafting.RecipeCreator;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.CookingRecipeJsonFactory;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

@CalledByReflection
public class ReactorRecipes implements RecipeCreator {

    @Override
    public void createRecipes(Consumer<RecipeJsonProvider> consumer) {
        createTempBlasting(consumer, Ingredient.fromTag(SharedItemTags.IRON_INGOTS), ReactorItems.STEEL_INGOT, 500);
        createTempBlasting(consumer, Ingredient.fromTag(SharedItemTags.URANIUM_INGOTS), ReactorItems.ENRICHED_URANIUM_INGOT);

        CookingRecipeJsonFactory
                .createBlasting(Ingredient.fromTag(SharedItemTags.COAL), ReactorItems.GRAPHITE, 0.1F, 100)
                .criterion("has_coal", conditionsFromTag(SharedItemTags.COAL))
                .offerTo(consumer);

        smeltingAndBlasting(consumer, Ingredient.fromTag(SharedItemTags.LEAD_ORES), ReactorItems.LEAD_INGOT, 0.5F, conditionsFromTag(SharedItemTags.LEAD_ORES));
        smeltingAndBlasting(consumer, Ingredient.fromTag(SharedItemTags.URANIUM_ORES), ReactorItems.URANIUM_INGOT, 0.5F, conditionsFromTag(SharedItemTags.URANIUM_ORES));

        ShapedRecipeJsonFactory
                .create(ReactorItems.URANIUM_FUEL_PELLET, 16)
                .input('#', SharedItemTags.IRON_NUGGETS)
                .input('U', SharedItemTags.ENRICHED_URANIUM_INGOTS)
                .pattern("###")
                .pattern("#U#")
                .pattern("###")
                .criterion("has_enriched_uranium", conditionsFromTag(SharedItemTags.ENRICHED_URANIUM_INGOTS))
                .offerTo(consumer);

        ShapedRecipeJsonFactory
                .create(ReactorItems.URANIUM_FUEL_ROD, 1)
                .input('#', SharedItemTags.IRON_NUGGETS)
                .input('U', ReactorItems.URANIUM_FUEL_PELLET)
                .pattern("#U#")
                .pattern("#U#")
                .pattern("#U#")
                .criterion("has_fuel_pellet", conditionsFromItem(ReactorItems.URANIUM_FUEL_PELLET))
                .offerTo(consumer);
    }

    private static void createTempBlasting(Consumer<RecipeJsonProvider> consumer, Ingredient input, ItemConvertible output) {
        createTempBlasting(consumer, input, output, 200);
    }

    private static void createTempBlasting(Consumer<RecipeJsonProvider> consumer, Ingredient input, ItemConvertible output, int time) {
        CookingRecipeJsonFactory.createBlasting(input, output, 0.1F, time).criterion("has_item", conditionsFromItemPredicates(ItemPredicate.ANY)).offerTo(consumer, IndustrialReactors.id("temp_blasting_" + Registry.ITEM.getId(output.asItem()).getPath()));
    }

    private static void smeltingAndBlasting(Consumer<RecipeJsonProvider> consumer, Ingredient input, ItemConvertible output, float experience, CriterionConditions conditions) {
        Identifier id = Registry.ITEM.getId(output.asItem());
        CookingRecipeJsonFactory.createSmelting(input, output, experience, 200).criterion("has_" + id.getPath(), conditions).offerTo(consumer, IndustrialReactors.id(id.getPath() + "_from_smelting"));
        CookingRecipeJsonFactory.createBlasting(input, output, experience, 100).criterion("has_" + id.getPath(), conditions).offerTo(consumer, IndustrialReactors.id(id.getPath() + "_from_blasting"));
    }

    private static EnterBlockCriterion.Conditions requireEnteringFluid(Block block) {
        return new EnterBlockCriterion.Conditions(EntityPredicate.Extended.EMPTY, block, StatePredicate.ANY);
    }

    private static InventoryChangedCriterion.Conditions conditionsFromItem(ItemConvertible itemConvertible) {
        return conditionsFromItemPredicates(ItemPredicate.Builder.create().item(itemConvertible).build());
    }

    private static InventoryChangedCriterion.Conditions conditionsFromTag(Tag<Item> tag) {
        return conditionsFromItemPredicates(ItemPredicate.Builder.create().tag(tag).build());
    }

    private static InventoryChangedCriterion.Conditions conditionsFromItemPredicates(ItemPredicate... itemPredicates) {
        return new InventoryChangedCriterion.Conditions(EntityPredicate.Extended.EMPTY, NumberRange.IntRange.ANY, NumberRange.IntRange.ANY, NumberRange.IntRange.ANY, itemPredicates);
    }
}
