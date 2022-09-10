package dev.zerite.xnbt.tag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class TagIntArray implements NBTTag {

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
        return "TagByteArray(" + Arrays.toString(value) + ")";
    }

    public static TagIntArray read(DataInputStream is) throws IOException {
        int length = is.readInt();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = is.readInt();
        }
        return new TagIntArray(array);
    }
}
