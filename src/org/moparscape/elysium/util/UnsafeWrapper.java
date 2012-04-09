package org.moparscape.elysium.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class UnsafeWrapper {

    private static final Unsafe unsafe;

    static {
        try {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field unsafeField = unsafeClass.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);

            unsafe = (Unsafe) unsafeField.get(unsafeClass);
        } catch (Exception e) {
            throw new Error("Failed to load Unsafe using UnsafeWrapper");
        }
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
