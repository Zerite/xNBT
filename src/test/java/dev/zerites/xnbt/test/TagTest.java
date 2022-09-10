package dev.zerites.xnbt.test;

import dev.zerite.xnbt.XNBT;
import dev.zerite.xnbt.tag.NBTTag;
import dev.zerite.xnbt.tag.impl.TagByte;
import dev.zerite.xnbt.tag.impl.TagByteArray;
import dev.zerite.xnbt.tag.impl.TagCompound;
import dev.zerite.xnbt.tag.impl.TagDouble;
import dev.zerite.xnbt.tag.impl.TagFloat;
import dev.zerite.xnbt.tag.impl.TagInt;
import dev.zerite.xnbt.tag.impl.TagIntArray;
import dev.zerite.xnbt.tag.impl.TagList;
import dev.zerite.xnbt.tag.impl.TagLong;
import dev.zerite.xnbt.tag.impl.TagLongArray;
import dev.zerite.xnbt.tag.impl.TagShort;
import dev.zerite.xnbt.tag.impl.TagString;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagTest {

    private static final List<Supplier<NBTTag>> tagCreators = List.of(
        () -> new TagByte((byte) 42),
        () -> new TagShort((short) 0xDEAD),
        () -> new TagInt(0xCAFEBABE),
        () -> new TagLong(0x627269746D6F6A69L),
        () -> new TagFloat(0.69f),
        () -> new TagDouble(0.7209768349),
        () -> new TagByteArray(HexFormat.of().parseHex("8b8d9e918c")),
        () -> new TagString("wow iphone"),
        () -> new TagList(List.of()),
        () -> new TagList(List.of(new TagString("wonderhoy!"))),
        () -> new TagCompound(Map.of()),
        () -> new TagCompound(Map.of("deez", new TagString("nuts"), "999e98", new TagInt(0x999e98))),
        () -> new TagIntArray(new int[]{1989, 6, 4}),
        () -> new TagLongArray(new long[]{0x4BE77787B400001L, 0x7D5C089BE82001FL})
    );

    @Test
    public void equalsTest() {
        for (Supplier<NBTTag> creator : tagCreators) {
            assertEquals(creator.get(), creator.get());
        }
    }

    @Test
    public void writeThenReadTest() throws IOException {
        for (Supplier<NBTTag> creator : tagCreators) {
            NBTTag original = creator.get();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XNBT.writeNamedTag(new DataOutputStream(baos), "out", original);
            NBTTag read = XNBT.readUncompressed(new ByteArrayInputStream(baos.toByteArray()));
            assertEquals(original, read);
        }
    }

}
