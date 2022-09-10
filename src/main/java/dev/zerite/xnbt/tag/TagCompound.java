package dev.zerite.xnbt.tag;

import dev.zerite.xnbt.XNBT;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TagCompound implements NBTTag {

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
        return "TagCompound(" + tagMap.toString() + ")";
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
}
