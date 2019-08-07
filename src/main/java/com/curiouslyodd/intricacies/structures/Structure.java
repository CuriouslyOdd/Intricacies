package com.curiouslyodd.intricacies.structures;

import com.curiouslyodd.intricacies.Main;

import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class Structure {

		public static String structureName;
		public static PlacementSettings settings = new PlacementSettings().setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE);
		
		public static void generate(String structureName, World worldIn, BlockPos pos, IBlockState state, EnumFacing facing) {
			MinecraftServer mcServer = worldIn.getMinecraftServer();
			TemplateManager manager = mcServer.getWorld(worldIn.provider.getDimension()).getStructureTemplateManager();
			ResourceLocation location = new ResourceLocation(Main.MODID, structureName);
			Template template = manager.get(mcServer, location);
			
			settings.setRotation(calculateRotation(facing));
			
			if(template != null) {
				worldIn.notifyBlockUpdate(pos, state, state, 3);
				template.addBlocksToWorldChunk(worldIn, pos, settings);
			}
		}
		
		public static Rotation calculateRotation(EnumFacing facing) {
			Rotation returnRotation = Rotation.NONE;
			
			switch(facing.getName()) {
				case "east":
					returnRotation = Rotation.CLOCKWISE_90;
				break;
				case "south":
					returnRotation = Rotation.CLOCKWISE_180;
				break;
				case "west":
					returnRotation = Rotation.COUNTERCLOCKWISE_90;
				break;
			}
			
			return returnRotation;
		}
	
}
