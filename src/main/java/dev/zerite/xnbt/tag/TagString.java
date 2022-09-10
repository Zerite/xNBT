package dev.zerite.xnbt.tag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public class TagString implements NBTTag {

    private final String value;

    public TagString(String value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_String;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeUTF(value);
    }

    @Override
    public String toString() {
        return "TagString('" + value + "')";
    }

    public static TagString read(DataInputStream is) throws IOException {
        return new TagString(is.readUTF());
    }
}
