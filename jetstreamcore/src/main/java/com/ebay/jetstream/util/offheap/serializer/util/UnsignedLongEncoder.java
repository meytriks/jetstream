/*******************************************************************************
 *  Copyright © 2012-2015 eBay Software Foundation
 *  This program is dual licensed under the MIT and Apache 2.0 licenses.
 *  Please see LICENSE for more information.
 *******************************************************************************/
package com.ebay.jetstream.util.offheap.serializer.util;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

public class UnsignedLongEncoder {
    /**
     * Decode the long from the byte buffer.
     * 
     * The input byte buffer should be readable, and it read from its current
     * position.
     * 
     * @param buffer
     * @return
     */
    public Long decode(ByteBuffer buffer) {
        byte b = buffer.get();
        long result = b & 0x7F;
        if ((b & 0x80) != 0) {
                b = buffer.get();
                result |= (b & 0x7F) << 7;
                if ((b & 0x80) != 0) {
                        b = buffer.get();
                        result |= (b & 0x7F) << 14;
                        if ((b & 0x80) != 0) {
                                b = buffer.get();
                                result |= (b & 0x7F) << 21;
                                if ((b & 0x80) != 0) {
                                        b = buffer.get();
                                        result |= (long)(b & 0x7F) << 28;
                                        if ((b & 0x80) != 0) {
                                                b = buffer.get();
                                                result |= (long)(b & 0x7F) << 35;
                                                if ((b & 0x80) != 0) {
                                                        b = buffer.get();
                                                        result |= (long)(b & 0x7F) << 42;
                                                        if ((b & 0x80) != 0) {
                                                                b = buffer.get();
                                                                result |= (long)(b & 0x7F) << 49;
                                                                if ((b & 0x80) != 0) {
                                                                        b = buffer.get();
                                                                        result |= (long)b << 56;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
        return Long.valueOf(result);
    }

    /**
     * Encode the long to the buffer, the integer must be 0 or positive.
     * 
     * The input byte buffer is writable and it write from its current position.
     * 
     * If the input buffer capacity is not enough, the BufferOverflowException
     * will be thrown.
     * 
     * @param value
     * @param buffer
     * @throws BufferOverflowException
     *             when buffer capacity is not enough to write the string.
     */
    public void encode(Long l, ByteBuffer buffer) throws BufferOverflowException {
        long value = l;
        if (value >>> 7 == 0) {
            buffer.put((byte) value);
        } else if (value >>> 14 == 0) {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7));
        } else if (value >>> 21 == 0) {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7 | 0x80));
            buffer.put((byte) (value >>> 14));
        } else if (value >>> 28 == 0) {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7 | 0x80));
            buffer.put((byte) (value >>> 14 | 0x80));
            buffer.put((byte) (value >>> 21));
        } else if (value >>> 35 == 0) {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7 | 0x80));
            buffer.put((byte) (value >>> 14 | 0x80));
            buffer.put((byte) (value >>> 21 | 0x80));
            buffer.put((byte) (value >>> 28));
        } else if (value >>> 42 == 0) {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7 | 0x80));
            buffer.put((byte) (value >>> 14 | 0x80));
            buffer.put((byte) (value >>> 21 | 0x80));
            buffer.put((byte) (value >>> 28 | 0x80));
            buffer.put((byte) (value >>> 35));
        } else if (value >>> 49 == 0) {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7 | 0x80));
            buffer.put((byte) (value >>> 14 | 0x80));
            buffer.put((byte) (value >>> 21 | 0x80));
            buffer.put((byte) (value >>> 28 | 0x80));
            buffer.put((byte) (value >>> 35 | 0x80));
            buffer.put((byte) (value >>> 42));
        } else if (value >>> 56 == 0) {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7 | 0x80));
            buffer.put((byte) (value >>> 14 | 0x80));
            buffer.put((byte) (value >>> 21 | 0x80));
            buffer.put((byte) (value >>> 28 | 0x80));
            buffer.put((byte) (value >>> 35 | 0x80));
            buffer.put((byte) (value >>> 42 | 0x80));
            buffer.put((byte) (value >>> 49));
        } else {
            buffer.put((byte) ((value & 0x7F) | 0x80));
            buffer.put((byte) (value >>> 7 | 0x80));
            buffer.put((byte) (value >>> 14 | 0x80));
            buffer.put((byte) (value >>> 21 | 0x80));
            buffer.put((byte) (value >>> 28 | 0x80));
            buffer.put((byte) (value >>> 35 | 0x80));
            buffer.put((byte) (value >>> 42 | 0x80));
            buffer.put((byte) (value >>> 49 | 0x80));
            buffer.put((byte) (value >>> 56));
        }
    }
}
