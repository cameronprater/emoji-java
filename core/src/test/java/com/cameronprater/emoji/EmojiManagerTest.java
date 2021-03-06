package com.cameronprater.emoji;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(JUnit4.class)
public class EmojiManagerTest {

    @Test
    public void getForAlias_with_unknown_alias_returns_null() {
        // GIVEN

        // WHEN
        Emoji emoji = EmojiManager.getForAlias("jkahsgdfjksghfjkshf");
        // THEN
        assertNull(emoji);
    }

    @Test
    public void getForAlias_returns_the_emoji_for_the_alias() {
        // GIVEN

        // WHEN
        Emoji emoji = EmojiManager.getForAlias("smile");
        // THEN
        assertEquals("\uD83D\uDE04", emoji.getUnicode());
    }

    @Test
    public void getForAlias_with_colons_returns_the_emoji_for_the_alias() {
        // GIVEN

        // WHEN
        Emoji emoji = EmojiManager.getForAlias(":smile:");
        // THEN
        assertEquals("\uD83D\uDE04", emoji.getUnicode());
    }

    @Test
    public void isEmoji_for_an_emoji_returns_true() {
        // GIVEN
        String emoji = "😀";
        // WHEN
        boolean isEmoji = EmojiManager.isEmoji(emoji);
        // THEN
        assertTrue(isEmoji);
    }

    @Test
    public void isEmoji_with_fitzpatric_modifier_returns_true() {
        // GIVEN
        String emoji = "\uD83E\uDD30\uD83C\uDFFB";
        // WHEN
        boolean isEmoji = EmojiManager.isEmoji(emoji);
        // THEN
        assertTrue(isEmoji);
    }

    @Test
    public void isEmoji_for_a_non_emoji_returns_false() {
        // GIVEN
        String str = "test";
        // WHEN
        boolean isEmoji = EmojiManager.isEmoji(str);
        // THEN
        assertFalse(isEmoji);
    }

    @Test
    public void isEmoji_for_an_emoji_and_other_chars_returns_false() {
        // GIVEN
        String str = "😀 test";
        // WHEN
        boolean isEmoji = EmojiManager.isEmoji(str);
        // THEN
        assertFalse(isEmoji);
    }

    @Test
    public void containsEmoji_with_fitzpatric_modifier_returns_true() {
        // GIVEN
        String emoji = "\uD83E\uDD30\uD83C\uDFFB";
        // WHEN
        boolean containsEmoji = EmojiManager.containsEmoji(emoji);
        // THEN
        assertTrue(containsEmoji);
    }

    @Test
    public void containsEmoji_for_a_non_emoji_returns_false() {
        // GIVEN
        String str = "test";
        // WHEN
        boolean containsEmoji = EmojiManager.containsEmoji(str);
        // THEN
        assertFalse(containsEmoji);
    }

    @Test
    public void containsEmoji_for_an_emoji_and_other_chars_returns_true() {
        // GIVEN
        String str = "😀 test";
        // WHEN
        boolean containsEmoji = EmojiManager.containsEmoji(str);
        // THEN
        assertTrue(containsEmoji);
    }

    @Test
    public void isOnlyEmojis_for_an_emoji_returns_true() {
        // GIVEN
        String str = "😀";
        // WHEN
        boolean isEmoji = EmojiManager.isOnlyEmojis(str);
        // THEN
        assertTrue(isEmoji);
    }

    @Test
    public void isOnlyEmojis_for_emojis_returns_true() {
        // GIVEN
        String str = "😀😀😀";
        // WHEN
        boolean isEmoji = EmojiManager.isOnlyEmojis(str);
        // THEN
        assertTrue(isEmoji);
    }

    @Test
    public void isOnlyEmojis_for_random_string_returns_false() {
        // GIVEN
        String str = "😀a";
        // WHEN
        boolean isEmoji = EmojiManager.isOnlyEmojis(str);
        // THEN
        assertFalse(isEmoji);
    }

    @Test
    public void getAll_doesnt_return_duplicates() {
        // GIVEN

        // WHEN
        List<Emoji> emojis = EmojiManager.getAll();
        // THEN
        Set<String> unicodes = new HashSet<>();
        for (Emoji emoji : emojis) {
            assertFalse("Duplicate: " + emoji.getAliases().get(0), unicodes.contains(emoji.getUnicode()));
            unicodes.add(emoji.getUnicode());
        }
        assertEquals(unicodes.size(), emojis.size());
    }

    @Test
    public void no_duplicate_alias() {
        // GIVEN

        // WHEN
        List<Emoji> emojis = EmojiManager.getAll();
        // THEN
        Set<String> aliases = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (Emoji emoji : emojis) {
            for (String alias : emoji.getAliases()) {
                if (aliases.contains(alias)) {
                    duplicates.add(alias);
                }
                aliases.add(alias);
            }
        }
        assertEquals("Duplicates: " + duplicates, duplicates.size(), 0);
    }

    @Test
    public void getByUnicode_returns_correct_emoji() {
        String wavingHand = "\uD83D\uDC4B";
        Emoji e = EmojiManager.getByUnicode(wavingHand);
        assertEquals(wavingHand, e.getUnicode());
        assertEquals("wave", e.getAliases().get(0));
    }
}
