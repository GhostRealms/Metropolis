package net.ghostrealms;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by River on 01-Dec-14.
 * Language and Phrase lib
 */
public class LangLib {

    private final Metro plugin;
    private File phraseFile;
    private FileConfiguration phraseConfig;

    public LangLib(Metro instance) {
        plugin = instance;

        phraseFile = new File(plugin.getDataFolder(), "phrase.yml");
        phraseConfig = YamlConfiguration.loadConfiguration(phraseFile);
    }

    public String getPhrase(String phrase) throws IllegalArgumentException {
        if(getPhraseConfig().isString(phrase)) {
            return getPhraseConfig().getString(phrase);
        } else {
            throw new IllegalArgumentException("That phrase does not exist");
        }
    }

    private void reloadPhraseConfig() {
        Reader configReader = new InputStreamReader(plugin.getResource("phrase.yml"));

        if(phraseFile == null) {
            phraseFile = new File(plugin.getDataFolder(), "phrase.yml");
        }

        if(phraseConfig == null) {
            phraseConfig = YamlConfiguration.loadConfiguration(phraseFile);
        }

        if(configReader != null) {
            YamlConfiguration defaults = YamlConfiguration.loadConfiguration(configReader);

            phraseConfig.setDefaults(defaults);
        }
    }

    private FileConfiguration getPhraseConfig() {
        if(phraseConfig != null) {
            return phraseConfig;
        } else {
            reloadPhraseConfig();
            return getPhraseConfig();
        }
    }

    private void savePhraseConfig() {
        if(phraseConfig == null) {
            return;
        }

        if(phraseFile == null) {
            return;
        }

        try {
            phraseConfig.save(phraseFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
