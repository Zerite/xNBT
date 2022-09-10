package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public final class TagInt implements NBTTag {

    private final int value;

    public TagInt(int value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Int;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value);
    }

    @Override
    public String toString() {
        return "TagInt(" + value + ")";
    }

    public static TagInt read(DataInputStream is) throws IOException {
        return new TagInt(is.readInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagInt)) return false;
        TagInt tagInt = (TagInt) o;
        return value == tagInt.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
