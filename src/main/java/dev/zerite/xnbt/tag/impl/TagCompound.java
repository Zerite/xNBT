package dev.zerite.xnbt.tag.impl;

import dev.zerite.xnbt.XNBT;
import dev.zerite.xnbt.tag.NBTTag;
import dev.zerite.xnbt.tag.NamedTag;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class TagCompound implements NBTTag {

    private final Map<String, NBTTag> tagMap;

    public TagCompound(Map<String, NBTTag> tagMap) {
        this.tagMap = tagMap;
    }

    @Override
    public int getType() {
        return TagType.TAG_Compound;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        for (Map.Entry<String, NBTTag> entry : tagMap.entrySet()) {
            XNBT.writeNamedTag(output, entry.getKey(), entry.getValue());
        }
        output.write(TagType.TAG_End);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TagCompound(").append(tagMap.size()).append(" entries): {\n");
        for (Map.Entry<String, NBTTag> entry : tagMap.entrySet()) {
            builder.append("  '").append(entry.getKey()).append("': ");

            if (entry.getValue() instanceof TagCompound || entry.getValue() instanceof TagList) {
                String[] split = entry.getValue().toString().split("\n");
                boolean first = true;
                for (String s : split) {
                    if (!first) {
                        builder.append("  ");
                    }

                    first = false;
                    builder.append(s).append("\n");
                }
            } else {
                builder.append(entry.getValue()).append("\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    public static TagCompound read(DataInputStream is) throws IOException {
        Map<String, NBTTag> tags = new LinkedHashMap<>();
        while (true) {
            NamedTag namedTag = XNBT.readNamedTag(is);

            if (namedTag.tag() == TagEnd.INSTANCE) break;

            tags.put(namedTag.name(), namedTag.tag());
        }
        return new TagCompound(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagCompound)) return false;
        TagCompound tagCompound = (TagCompound) o;
        return tagMap.equals(tagCompound.tagMap);
    }

    @Override
    public int hashCode() {
        return tagMap.hashCode();
    }
}
