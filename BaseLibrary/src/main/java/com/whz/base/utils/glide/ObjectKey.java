package com.whz.base.utils.glide;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;

import java.security.MessageDigest;



/**
 * Created by whz  on 2019-06-27
 */
public final class ObjectKey implements Key {
    private final Object object;

    public ObjectKey(Object object) {
        this.object = Preconditions.checkNotNull(object);
    }

    @Override
    public String toString() {
        return "ObjectKey{"
                + "object=" + object
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ObjectKey) {
            ObjectKey other = (ObjectKey) o;
            return object.equals(other.object);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return object.hashCode();
    }

    // Charset CHARSET = Charset.forName("UTF-8");
    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(object.toString().getBytes(CHARSET));
    }
}
