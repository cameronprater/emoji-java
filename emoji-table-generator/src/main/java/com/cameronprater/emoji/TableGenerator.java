package com.cameronprater.emoji;

/**
 * This app generate the emoji table in the README ;)
 * <p/>
 * Run with:
 * mvn exec:java -Dexec.mainClass="com.vdurmont.emoji.TableGenerator"
 */
public class TableGenerator {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        // Table header
        sb.append("| Emoji | Aliases | Emoji | Aliases |\n");
        sb.append("| :---: | ------- | :---: | ------- |\n");

        // Emojis
        int i = 0;
        for (Emoji emoji : EmojiManager.getAll()) {
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
            i++;
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
