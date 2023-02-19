package org.example.infrastructure.io.writer;

public final class DummyWriterMock implements Writer {
    @Override
    public boolean write(String value) {
        return true;
    }
}
