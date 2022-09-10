package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public final class TagLong implements NBTTag {

    private final long value;

    public TagLong(long value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Long;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeLong(value);
    }

    @Override
    public String toString() {
        return "TagLong(" + value + ")";
    }

    public static TagLong read(DataInputStream is) throws IOException {
        return new TagLong(is.readLong());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagLong)) return false;
        TagLong tagLong = (TagLong) o;
        return value == tagLong.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }
}
