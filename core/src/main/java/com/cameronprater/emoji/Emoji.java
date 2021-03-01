package com.cameronprater.emoji;

import java.util.Collections;
import java.util.List;

/**
 * This class represents an emoji.
 * <p>
 * This object is immutable so it can be used safely in a multithreaded context.
 *
 * @author Vincent DURMONT [vdurmont@gmail.com]
 */
public class Emoji {
    private boolean supportsFitzpatrick;
    private List<String> aliases;
    private String unicode;

    public Emoji() {
    }

    /**
     * Constructor for the Emoji.
     *
     * @param supportsFitzpatrick Whether the emoji supports Fitzpatrick modifiers
     * @param aliases             the aliases for this emoji
     */
    Emoji(String unicode, boolean supportsFitzpatrick, List<String> aliases) {
        this.unicode = unicode;
        this.supportsFitzpatrick = supportsFitzpatrick;
        this.aliases = Collections.unmodifiableList(aliases);
        int stringLength = getUnicode().length();
        for (int offset = 0; offset < stringLength;) {
            int codePoint = getUnicode().codePointAt(offset);
            offset += Character.charCount(codePoint);
        }
    }

    /**
     * Returns whether the emoji supports the Fitzpatrick modifiers or not
     *
     * @return true if the emoji supports the Fitzpatrick modifiers
     */
    public boolean supportsFitzpatrick() {
        return supportsFitzpatrick;
    }

    /**
     * Returns the aliases of the emoji
     *
     * @return the aliases (unmodifiable)
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Returns the unicode representation of the emoji
     *
     * @return the unicode representation
     */
    public String getUnicode() {
        return unicode;
    }

    /**
     * Returns the unicode representation of the emoji associated with the provided Fitzpatrick modifier.
     * If the modifier is null, then the result is similar to {@link Emoji#getUnicode()}
     *
     * @param fitzpatrick the fitzpatrick modifier or null
     * @return the unicode representation
     * @throws UnsupportedOperationException if the emoji doesn't support the Fitzpatrick modifiers
     */
    public String getUnicode(Fitzpatrick fitzpatrick) {
        if (!supportsFitzpatrick()) {
            throw new UnsupportedOperationException(
                    "Cannot get the unicode with a fitzpatrick modifier, the emoji doesn't support fitzpatrick.");
        } else if (fitzpatrick == null) {
            return getUnicode();
        }
        return getUnicode() + fitzpatrick.unicode;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Emoji && ((Emoji) o).getUnicode().equals(getUnicode());
    }

    @Override
    public int hashCode() {
        return unicode.hashCode();
    }

    /**
     * Returns the String representation of the Emoji object.
     * <p>
     * Example:
     * <p>
     * <code>Emoji {
     *   supportsFitzpatrick=false,
     *   aliases=[smile],
     *   unicode='😄'
     * }</code>
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Emoji{" +
            "supportsFitzpatrick=" + supportsFitzpatrick +
            ", aliases=" + aliases +
            ", unicode='" + unicode + '\'' +
            '}';
    }
}
