package com.cameronprater.emoji;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Loads the emojis from a JSON database.
 *
 * @author Vincent DURMONT [vdurmont@gmail.com]
 */
public class EmojiLoader {

    private EmojiLoader() {
    }

    /**
     * Loads an InputStream of emojis in JSON, parses it and returns the associated list of {@link Emoji}s
     *
     * @param stream the stream of the emojis in JSON
     * @return the list of {@link Emoji}s
     */
    public static List<Emoji> loadEmojis(InputStream stream) {
        try {
            return new ObjectMapper()
                    .registerModule(new SimpleModule().addDeserializer(Emoji.class, new EmojiDeserializer()))
                    .readValue(stream, new TypeReference<List<Emoji>>() {});
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
