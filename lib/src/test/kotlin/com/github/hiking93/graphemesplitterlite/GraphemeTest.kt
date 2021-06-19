package com.github.hiking93.graphemesplitterlite

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GraphemeTest {

    @Test
    fun `simple latin`() {
        Assertions.assertEquals(
            listOf("g", "r", "a", "p", "h", "e", "m", "e"),
            GraphemeSplitter().split("grapheme"),
        )
    }

    @Test
    fun `latin with Combining Diacritical Marks`() {
        Assertions.assertEquals(
            listOf(
                "\u0061\u0308\u0303",
                "\u0061\u0323\u032D",
            ),
            GraphemeSplitter().split("\u0061\u0308\u0303\u0061\u0323\u032D"),
        )
    }

    @Test
    fun `surrogate pairs`() {
        Assertions.assertEquals(
            listOf(
                "\uD83D\uDCA9",
                "\uD83D\uDEBD",
            ),
            GraphemeSplitter().split("\uD83D\uDCA9\uD83D\uDEBD")
        )
    }

    @Test
    fun `CR X LF`() {
        Assertions.assertEquals(
            listOf("\u000D\u000A"),
            GraphemeSplitter().split("\u000D\u000A")
        )
    }

    @Test
    fun `Hangul syllable sequence`() {
        Assertions.assertEquals(
            listOf(
                "\u1112\u1161\u11AB",
                "\u1100\u1173\u11AF",
            ),
            GraphemeSplitter().split("\u1112\u1161\u11AB\u1100\u1173\u11AF")
        )
    }

    @Test
    fun `Devanagari sequence`() {
        Assertions.assertEquals(
            listOf(
                "\u0926\u0947",
                "\u0935",
                "\u0928\u093E",
                "\u0917",
                "\u0930\u0940",
            ),
            GraphemeSplitter().split(
                "\u0926\u0947\u0935\u0928\u093E\u0917\u0930\u0940"
            )
        )
    }

    @Test
    fun `emoji modifier`() {
        Assertions.assertEquals(
            listOf("\uD83E\uDD26\uD83C\uDFFB"),
            GraphemeSplitter().split("\uD83E\uDD26\uD83C\uDFFB")
        )
    }

    @Test
    fun `zero width joiner`() {
        Assertions.assertEquals(
            listOf("\uD83D\uDC68\u200D\uD83D\uDC67\u200D\uD83D\uDC66"),
            GraphemeSplitter().split("\uD83D\uDC68\u200D\uD83D\uDC67\u200D\uD83D\uDC66")
        )
    }

    @Test
    fun `regional indicator flag sequence`() {
        Assertions.assertEquals(
            listOf("\uD83C\uDDEF\uD83C\uDDF5"),
            GraphemeSplitter().split("\uD83C\uDDEF\uD83C\uDDF5")
        )
        Assertions.assertEquals(
            listOf("\uD83C\uDDEF\uD83C\uDDF5", "\uD83C\uDDEF"),
            GraphemeSplitter().split("\uD83C\uDDEF\uD83C\uDDF5\uD83C\uDDEF")
        )
    }

    @Test
    fun `tag sequence`() {
        Assertions.assertEquals(
            listOf("\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F"),
            GraphemeSplitter().split(
                "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F"
            )
        )
    }
}