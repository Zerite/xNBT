package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public final class TagByteArray implements NBTTag {

    private final byte[] value;

    public TagByteArray(byte[] value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Byte_Array;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value.length);
        output.write(value);
    }

    @Override
    public String toString() {
        return "TagByteArray(" + Arrays.toString(value) + ")";
    }

    public static TagByteArray read(DataInputStream is) throws IOException {
        int length = is.readInt();
        byte[] value = new byte[length];
        is.readFully(value);
        return new TagByteArray(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagByteArray)) return false;
        TagByteArray tagByteArray = (TagByteArray) o;
        return Arrays.equals(value, tagByteArray.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
