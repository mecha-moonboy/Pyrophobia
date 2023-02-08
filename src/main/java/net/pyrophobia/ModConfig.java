package net.pyrophobia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create(); // got a bunch of cool config options


    public static ModConfig readConfig() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("pyrophobia_config.json");
        ModConfig config = null;
        if (Files.exists(path)) {
            System.out.println("The config file exists...");
            // The config file exists, so we can read it
            try {
                // Read the file into a byte array
                byte[] data = Files.readAllBytes(path);

                // Deserialize the byte array into an object
                Gson gson = new Gson();
                config = gson.fromJson(new String(data), ModConfig.class);
            } catch (IOException e) {
                // Handle the exception
            }
        } else {
            // The config file does not exist, so we need to create it
            System.out.println("The config file does NOT exist...");

            try {
                // Create the file
                Files.createFile(path);

                // Create a new config object with default values
                config = new ModConfig();

                // Serialize the config object to a JSON string
                String json = new Gson().toJson(config);

                // Write the JSON string to the file
                Files.writeString(path, json);
            } catch (IOException e) {
                // Handle the exception
            }
        }
        return config;
    }


    public void writeConfig() {
        Path configFile = FabricLoader.getInstance().getConfigDir().resolve("pyrophobia_config.json");
        try {
            Files.writeString(configFile, GSON.toJson(this));
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
