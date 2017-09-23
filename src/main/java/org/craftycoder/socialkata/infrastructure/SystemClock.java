package org.craftycoder.socialkata.infrastructure;

import org.craftycoder.socialkata.domain.ports.Clock;

public class SystemClock implements Clock {
    @Override
    public Long now() {
        return System.currentTimeMillis();
    }
}
