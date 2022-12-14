package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public final class TagLongArray implements NBTTag {

    private final long[] value;

    public TagLongArray(long[] value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Long_Array;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value.length);
        for (long l : value) {
            output.writeLong(l);
        }
    }

    @Override
    public String toString() {
        return "TagLongArray(" + Arrays.toString(value) + ")";
    }

    public static TagLongArray read(DataInputStream is) throws IOException {
        int length = is.readInt();
        long[] array = new long[length];
        for (int i = 0; i < length; i++) {
            array[i] = is.readLong();
        }
        return new TagLongArray(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagLongArray)) return false;
        TagLongArray tagLongArray = (TagLongArray) o;
        return Arrays.equals(value, tagLongArray.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
