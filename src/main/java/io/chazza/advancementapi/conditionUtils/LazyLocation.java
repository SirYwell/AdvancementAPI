package io.chazza.advancementapi.conditionUtils;

import com.google.gson.JsonObject;
import org.bukkit.block.Biome;

/**
 * @author SirYwell
 */
public class LazyLocation extends Jsonable {

    private Position position;
    private Biome biome;
    private Dimension dimension;
    private Feature feature;

    public LazyLocation() {}

    public LazyLocation(Position position, Biome biome, Dimension dimension, Feature feature) {
        this.position = position;
        this.biome = biome;
        this.dimension = dimension;
        this.feature = feature;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        if(biome != null) {
            object.addProperty("biome", biome.name().toLowerCase());
        }
        if(dimension != null) {
            object.addProperty("dimension", dimension.name().toLowerCase());
        }
        if(feature != null) {
            object.addProperty("feature", feature.getName());
        }
        object = addIfDeclared("position", position, object);
        return object;
    }
}
