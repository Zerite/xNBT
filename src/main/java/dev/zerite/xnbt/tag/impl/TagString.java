package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

public class TagString implements NBTTag {

    @NotNull
    private final String value;

    public TagString(@NotNull String value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagString tagString = (TagString) o;
        return value.equals(tagString.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
