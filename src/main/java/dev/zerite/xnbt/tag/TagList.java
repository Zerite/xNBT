package dev.zerite.xnbt.tag;

import dev.zerite.xnbt.XNBT;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagList implements NBTTag {

    private final List<NBTTag> value;

    public TagList(List<NBTTag> value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return TagType.TAG_List;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        if (value.size() == 0) {
            output.write(0); // type
            output.writeInt(0); // length
            return;
        }

        output.write(value.get(0).getType()); // todo: check
        output.writeInt(value.size());
        for (NBTTag tag : value) {
            tag.write(output);
        }
    }

    @Override
    public String toString() {
        return "TagByteArray(" + value + ")";
    }

    public static TagList read(DataInputStream is) throws IOException {
        byte type = is.readByte();
        int length = is.readInt();

        List<NBTTag> tags = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            tags.add(XNBT.readTag(is, type));
        }

        return new TagList(tags);
    }
}
