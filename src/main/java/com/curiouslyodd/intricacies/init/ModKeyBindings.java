package com.curiouslyodd.intricacies.init;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModKeyBindings {
	
	public static KeyBinding[] keyBindings;

    public static void registerKeyBindings()
    {
        // declare an array of key bindings
        keyBindings = new KeyBinding[1];

        // instantiate the key bindings
        keyBindings[0] = new KeyBinding("Open Player Stats", Keyboard.KEY_I, "Intricacies");

        // register all the key bindings
        for (int i = 0; i < keyBindings.length; ++i)
        {
            ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
    }

	
}
