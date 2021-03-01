package com.cameronprater.emoji;

import java.util.List;

public class EmojiTableGenerator {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("| Emoji | Aliases | Emoji | Aliases |\n");
        sb.append("| :---: | ------- | :---: | ------- |\n");

        List<Emoji> emojis = EmojiManager.getAll();
        for (int i = 0; i < emojis.size(); i++) {
            Emoji emoji = emojis.get(i);
            String aliases = getAliases(emoji);

            if (i % 2 == 0) {
                sb.append("| ")
                    .append(emoji.getUnicode())
                    .append(" | ")
                    .append(aliases)
                    .append(" |");
            } else {
                sb.append(" ")
                    .append(emoji.getUnicode())
                    .append(" | ")
                    .append(aliases)
                    .append(" |\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static String getAliases(Emoji emoji) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String alias : emoji.getAliases()) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(alias);
        }
        return sb.toString();
    }
}
