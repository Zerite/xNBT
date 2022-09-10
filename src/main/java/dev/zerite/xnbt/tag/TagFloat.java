package dev.zerite.xnbt.tag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public class TagFloat implements NBTTag {

    private final float value;

    public TagFloat(float value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_Float;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeFloat(value);
    }

    @Override
    public String toString() {
        return "TagFloat(" + value + ")";
    }

    public static TagFloat read(DataInputStream is) throws IOException {
        return new TagFloat(is.readFloat());
    }
}
