package dev.zerite.xnbt.tag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public class TagByte implements NBTTag {

    private final byte value;

    public TagByte(byte value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Byte;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.write(value);
    }

    @Override
    public String toString() {
        return "TagByte(" + value + ")";
    }

    public static TagByte read(DataInputStream is) throws IOException {
        return new TagByte(is.readByte());
    }
}
