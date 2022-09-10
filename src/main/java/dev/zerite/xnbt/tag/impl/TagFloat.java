package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagFloat tagFloat = (TagFloat) o;
        return Float.compare(tagFloat.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(value);
    }
}
