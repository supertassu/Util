/*
 * The MIT License.
 *
 * Copyright (c) tassu <hello@tassu.me>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.tassu.util;

import lombok.val;

import java.util.*;

public final class ArrayUtil {

    private ArrayUtil() {}

    @SafeVarargs
    public static <T> T[] join(T[] a, T... b) {
        return concatenate(a, b);
    }

    public static <T> T[] concatenate(T[] a, T[] b) {
        ArrayList<T> copy = new ArrayList<>(a.length + b.length);
        copy.addAll(Arrays.asList(a));
        copy.addAll(Arrays.asList(b));
        return copy.toArray(a);
    }

    /**
     * Returns the given list without the first element
     */
    public static <T> List<T> withoutFirst(List<T> in) {
        if (in.size() < 1) throw new IllegalArgumentException("Given list should have at least one entry.");
        val copy = new ArrayList<>(in);
        copy.remove(0);

        //noinspection unchecked - false warning
        return copy;
    }

    public static <T> Optional<T> getLast(Collection<T> collection) {
        if (collection.isEmpty()) return Optional.empty();

        Iterator<T> iterator = collection.iterator();
        T lastElement = iterator.next();

        while (iterator.hasNext()) {
            lastElement = iterator.next();
        }

        return Optional.ofNullable(lastElement);
    }

}
