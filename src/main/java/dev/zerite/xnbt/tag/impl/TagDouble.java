package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDouble tagDouble = (TagDouble) o;
        return Double.compare(tagDouble.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
