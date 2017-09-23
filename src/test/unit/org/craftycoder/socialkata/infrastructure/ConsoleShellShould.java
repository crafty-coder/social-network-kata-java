package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.delivery.Console;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleShellShould {

    @Mock
    private PrintStream outMock;

    @Test
    public void read_on_provided_stream() throws UnsupportedEncodingException {

        String text = "Alice -> I love the weather today";
        InputStream stream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8.name()));

        Console console = new ConsoleShell(stream, outMock);

        Assert.assertEquals(console.read(), text);

    }

    @Test
    public void write_on_out_stream() throws UnsupportedEncodingException {

        InputStream emptyInputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return -1;
            }
        };

        String textToPrint = "I love the weather today (5 seconds ago)";

        Console console = new ConsoleShell(emptyInputStream, outMock);

        console.println(textToPrint);

        verify(outMock, times(1)).println(textToPrint);


    }

}