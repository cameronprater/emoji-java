package com.cameronprater.emoji;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Loads the emojis from a JSON database.
 *
 * @author Vincent DURMONT [vdurmont@gmail.com]
 */
public class EmojiLoader {

    /**
     * No need for a constructor, all the methods are static.
     */
    private EmojiLoader() {
    }

    /**
     * Loads a JSONArray of emojis from an InputStream, parses it and returns the associated list of {@link Emoji}s
     *
     * @param stream the stream of the JSONArray
     * @return the list of {@link Emoji}s
     * @throws IOException if an error occurs while reading the stream or parsing the JSONArray
     */
    public static List<Emoji> loadEmojis(InputStream stream) throws IOException {
        return new ObjectMapper().registerModule(new SimpleModule().addDeserializer(Emoji.class, new EmojiDeserializer()))
            .readValue(stream, new TypeReference<List<Emoji>>() {});
    }
}
