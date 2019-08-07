package com.curiouslyodd.intricacies.client.render;

import javax.annotation.Nonnull;

import com.curiouslyodd.intricacies.Main;
import com.curiouslyodd.intricacies.entities.IntricateVillager;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderIntricateVillager extends RenderLiving<IntricateVillager> {

	public static final Factory FACTORY = new Factory();
	public static ResourceLocation mobTexture = new ResourceLocation(Main.MODID + ":textures/entities/illusionist.png");
	
	public RenderIntricateVillager(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelVillager(1), 0.5F);
    }
	
	@Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull IntricateVillager entity) {
        return mobTexture;
    }
	
	public static class Factory implements IRenderFactory<IntricateVillager> {

        @Override
        public Render<? super IntricateVillager> createRenderFor(RenderManager manager) {
            return new RenderIntricateVillager(manager);
        }

    }
}
