package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public final class TagIntArray implements NBTTag {

    private final int[] value;

    public TagIntArray(int[] value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Int_Array;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value.length);
        for (int i : value) {
            output.writeInt(i);
        }
    }

    @Override
    public String toString() {
        return "TagIntArray(" + Arrays.toString(value) + ")";
    }

    public static TagIntArray read(DataInputStream is) throws IOException {
        int length = is.readInt();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = is.readInt();
        }
        return new TagIntArray(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagIntArray)) return false;
        TagIntArray tagIntArray = (TagIntArray) o;
        return Arrays.equals(value, tagIntArray.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
