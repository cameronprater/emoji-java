package com.cameronprater.emoji;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Arrays;

public class EmojiDeserializer extends StdDeserializer<Emoji> {

    private EmojiDeserializer(Class<?> vc) {
        super(vc);
    }

    public EmojiDeserializer() {
        this(Emoji.class);
    }

    @Override
    public Emoji deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode treeNode = jsonParser.readValueAsTree();
        String unicode;
        String description;
        boolean supportsFitzpatrick = false;
        String[] aliases;
        TreeNode temp;
        if ((temp = treeNode.get("unicode")) != null) {
            unicode = temp.toString();
        } else {
            throw new RuntimeException();
        }
        if ((temp = treeNode.get("description")) != null) {
            description = temp.toString();
        } else {
            throw new RuntimeException();
        }
        if ((temp = treeNode.get("supportsFitzpatrick")) != null) {
            supportsFitzpatrick = Boolean.parseBoolean(temp.toString());
        }
        if ((temp = treeNode.get("aliases")) != null) {
            String[] tokens = temp.toString().replace("[", "").replace("]", "").replace("\"", "").split(",");
            aliases = tokens.length > 1 ? tokens : new String[] { tokens[0] };
        } else {
            throw new RuntimeException();
        }
        return new Emoji(unicode.replace("\"", ""), description.replace("\"", ""), supportsFitzpatrick, Arrays.asList(aliases));
    }
}
