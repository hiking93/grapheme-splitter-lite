# Grapheme Splitter Lite (Java)

A light-weight Java library that breaks strings into user-perceived characters a.k.a. Grapheme Clusters for common cases.

This library is a light-weight implementation with limited language support, based on `java.text.BreakIterator` with some patches added, by a subset of rules in [UAX #29: Unicode Text Segmentation](https://unicode.org/reports/tr29/). Checkout [ICU4J](https://unicode-org.github.io/icu/userguide/icu4j/) if you need all rules implemented.

## Applied Patches

* [ZWJ (Zero-Width Joiner)](https://en.wikipedia.org/wiki/Zero-width_joiner)
* [Emoji modifiers (skin colors)](https://en.wikipedia.org/wiki/Miscellaneous_Symbols_and_Pictographs#Emoji_modifiers)
* [Regional indicator symbols (flags)](https://en.wikipedia.org/wiki/Regional_indicator_symbol)
* [Tags](https://en.wikipedia.org/wiki/Tags_(Unicode_block))

## Known Issues

* Some [rules of Hangul](https://unicode.org/reports/tr29/#GB6) are not implemented, e.g. L + LVT.
* Some [Spacing Mark Characters](https://unicode.org/reports/tr29/#SpacingMark) (e.g. Tamil, Myanmar) are not recognized.


## Example Usage

### Split Clusters

```
GraphemeSplitter().split(
    "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F\uD83E\uDD26\uD83C\uDFFB\u200D\u2642\uFE0F\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66"
)
```
Result:
```
[🏴󠁧󠁢󠁥󠁮󠁧󠁿, 🤦🏻‍♂️, 👨‍👩‍👧‍👦]
```

### Find Breaks
```
GraphemeSplitter().findBreaks(
    "\uD83C\uDDF0\uD83C\uDDF7\u1112\u1161\u11AB"
)
```
Result:

```
[4, 7]
```