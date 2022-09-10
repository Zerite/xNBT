package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.XNBT;
import dev.zerite.xnbt.tag.NBTTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class TagList implements NBTTag {

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
        StringBuilder builder = new StringBuilder();
        builder.append("TagList(").append(value.size()).append(" entries): [\n");

        for (int i = 0; i < value.size(); i++) {
            NBTTag tag = value.get(i);

            if (tag instanceof TagCompound || tag instanceof TagList) {
                String[] split = tag.toString().split("\n");

                for (int j = 0; j < split.length; j++) {
                    String s = split[j];
                    if (j != split.length - 1) {
                        builder.append("  ").append(s).append("\n");
                    } else {
                        builder.append("  ").append(s);
                    }
                }
            } else {
                builder.append("  ").append(tag);
            }

            if (i != value.size() - 1) {
                builder.append(",\n");
            } else {
                builder.append("\n");
            }
        }

        builder.append("]");
        return builder.toString();
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
