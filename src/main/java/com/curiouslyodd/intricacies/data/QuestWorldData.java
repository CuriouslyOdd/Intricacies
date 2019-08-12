package com.curiouslyodd.intricacies.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.curiouslyodd.intricacies.Main;
import com.google.gson.Gson;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class QuestWorldData extends WorldSavedData {
	
	public static final String identifier = Main.MODID + "_quest_data";
	public int currentQuestId = 1;
	public String currentQuestName;

	public QuestWorldData() {
		super(identifier);
	}
	
	public QuestWorldData(String s) {
		super(s);
		markDirty();
	}
	
	public static QuestWorldData get(World world) {
		MapStorage storage = world.getMapStorage();
		QuestWorldData instance = (QuestWorldData) storage.getOrLoadData(QuestWorldData.class, identifier);
		
		if(instance == null) {
			instance = new QuestWorldData();
			storage.setData(identifier, instance);
		}
		
		return instance;
	}
	
	public void getQuest(int questKey) {
		try {
			InputStream file = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(Main.MODID + ":json/quests/" + questKey + ".json")).getInputStream();
			Reader reader = new InputStreamReader(file);
			JsonResult result = new Gson().fromJson(reader, JsonResult.class);
			
			currentQuestName = result.name;
		} catch (IOException e) {
			System.out.println("Cant find quests.json");
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		currentQuestId = nbt.getInteger("current_quest");
		getQuest(currentQuestId);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("current_quest", currentQuestId);
		return compound;
	}
	

	@SuppressWarnings("unused")
	public class JsonResult {
		private String name;
		private String description;
	}
}
