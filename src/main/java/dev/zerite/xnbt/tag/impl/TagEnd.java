package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataOutput;
import java.io.IOException;

public class TagEnd implements NBTTag {

    public static final TagEnd INSTANCE = new TagEnd();

    private TagEnd() {}

    @Override
    public void write(DataOutput output) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public int getType() {
        return TagType.TAG_End;
    }
}
