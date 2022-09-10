package dev.zerite.xnbt;

import dev.zerite.xnbt.tag.NBTTag;
import dev.zerite.xnbt.tag.NamedTag;
import dev.zerite.xnbt.tag.TagByte;
import dev.zerite.xnbt.tag.TagByteArray;
import dev.zerite.xnbt.tag.TagCompound;
import dev.zerite.xnbt.tag.TagDouble;
import dev.zerite.xnbt.tag.TagEnd;
import dev.zerite.xnbt.tag.TagFloat;
import dev.zerite.xnbt.tag.TagInt;
import dev.zerite.xnbt.tag.TagIntArray;
import dev.zerite.xnbt.tag.TagList;
import dev.zerite.xnbt.tag.TagLong;
import dev.zerite.xnbt.tag.TagLongArray;
import dev.zerite.xnbt.tag.TagShort;
import dev.zerite.xnbt.tag.TagString;
import dev.zerite.xnbt.tag.TagType;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * High performance NBT parsing library
 *
 * @author DJtheRedstoner
 */
public class XNBT {

    public static NBTTag readCompressed(InputStream is) throws IOException {
        return readUncompressed(new GZIPInputStream(is));
    }

    public static NBTTag readUncompressed(InputStream is) throws IOException {
        return readNamedTag(new DataInputStream(is)).tag();
    }

    public static NamedTag readNamedTag(DataInputStream is) throws IOException {
        byte type = is.readByte();

        if (type == TagType.TAG_End) {
            return new NamedTag("", TagEnd.INSTANCE);
        }

        String name = is.readUTF();

        return new NamedTag(name, readTag(is, type));
    }

    public static NBTTag readTag(DataInputStream is, byte type) throws IOException {
        switch (type) {
            case TagType.TAG_Byte:
                return TagByte.read(is);
            case TagType.TAG_Short:
                return TagShort.read(is);
            case TagType.TAG_Int:
                return TagInt.read(is);
            case TagType.TAG_Long:
                return TagLong.read(is);
            case TagType.TAG_Float:
                return TagFloat.read(is);
            case TagType.TAG_Double:
                return TagDouble.read(is);
            case TagType.TAG_Byte_Array:
                return TagByteArray.read(is);
            case TagType.TAG_String:
                return TagString.read(is);
            case TagType.TAG_List:
                return TagList.read(is);
            case TagType.TAG_Compound:
                return TagCompound.read(is);
            case TagType.TAG_Int_Array:
                return TagIntArray.read(is);
            case TagType.TAG_Long_Array:
                return TagLongArray.read(is);
            default:
                throw new IOException("Unknown tag type: " + type);
        }
    }

    public static void writeNamedTag(DataOutput output, String name, NBTTag tag) throws IOException {
        output.write(tag.getType());
        output.writeUTF(name);
        tag.write(output);
    }

}
