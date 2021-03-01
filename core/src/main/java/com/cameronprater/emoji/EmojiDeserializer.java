package com.cameronprater.emoji;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class EmojiDeserializer extends StdDeserializer<Emoji> {

    private EmojiDeserializer(Class<?> vc) {
        super(vc);
    }

    public EmojiDeserializer() {
        this(Emoji.class);
    }

    @Override
    public Emoji deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        String unicode = Objects.requireNonNull(node.get("unicode")).asText();
        boolean supportsFitzpatrick = Objects.requireNonNull(node.get("supportsFitzpatrick")).asBoolean();
        Spliterator<JsonNode> spliterator = Objects.requireNonNull(node.get("aliases").spliterator());
        List<String> aliases = StreamSupport.stream(spliterator, false).map(JsonNode::asText).collect(Collectors.toList());

        return new Emoji(unicode, supportsFitzpatrick, aliases);
    }
}
