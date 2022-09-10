package dev.zerite.xnbt.tag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public class TagShort implements NBTTag {

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
}
