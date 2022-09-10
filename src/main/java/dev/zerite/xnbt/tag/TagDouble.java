package dev.zerite.xnbt.tag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public class TagDouble implements NBTTag {

    private final double value;

    public TagDouble(double value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Double;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeDouble(value);
    }

    @Override
    public String toString() {
        return "TagDouble(" + value + ")";
    }

    public static TagDouble read(DataInputStream is) throws IOException {
        return new TagDouble(is.readDouble());
    }
}
