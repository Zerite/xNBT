package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public final class TagShort implements NBTTag {

    private final short value;

    public TagShort(short value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Short;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeShort(value);
    }

    @Override
    public String toString() {
        return "TagShort(" + value + ")";
    }

    public static TagShort read(DataInputStream is) throws IOException {
        return new TagShort(is.readShort());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagShort)) return false;
        TagShort tagShort = (TagShort) o;
        return value == tagShort.value;
    }

    @Override
    public int hashCode() {
        return Short.hashCode(value);
    }
}
