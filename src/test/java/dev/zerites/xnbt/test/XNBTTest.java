package dev.zerites.xnbt.test;

import dev.zerite.xnbt.XNBT;
import dev.zerite.xnbt.tag.NBTTag;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class XNBTTest {

    @Test
    public void readTest() throws IOException {
        try (InputStream is = Files.newInputStream(Path.of("test/hello_world.nbt"))) {
            System.out.println(XNBT.readUncompressed(is));
        }
        try (InputStream is = Files.newInputStream(Path.of("test/bigtest.nbt"))) {
            System.out.println(XNBT.readCompressed(is));
        }
    }

    @Test
    public void writeTest() throws IOException {
        byte[] data = Files.readAllBytes(Path.of("test/hello_world.nbt"));
        NBTTag read = XNBT.readUncompressed(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XNBT.writeNamedTag(new DataOutputStream(out), "hello world", read);
        assertArrayEquals(data, out.toByteArray());
    }

}
