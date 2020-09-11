package ro.trm.demo.tools;

import android.content.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;


public class SubclassingController {
    private static Constructor<?> getConstructor(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException {
        Constructor<?> constructor = clazz.getDeclaredConstructor(Context.class);
        if (constructor == null) {
            throw new NoSuchMethodException();
        }
        int modifiers = constructor.getModifiers();
        if (Modifier.isPublic(modifiers) ||
                (!(Modifier.isProtected(modifiers) || Modifier.isPrivate(modifiers)))) {
            return constructor;
        }
        throw new IllegalAccessException();
    }

    public static Object newInstance(Class<?> clazz, Context context) throws NoSuchMethodException, IllegalAccessException {
        Constructor<?> constructor;
        constructor = getConstructor(clazz);

        try {
            return constructor != null
                    ? constructor.newInstance(context)
                    : null;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of subclass.", e);
        }
    }

    public static Object newInstance(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException {
        Constructor<?> constructor;

        constructor = getConstructor(clazz);

        try {
            return constructor != null
                    ? constructor.newInstance()
                    : null;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of subclass.", e);
        }
    }
}