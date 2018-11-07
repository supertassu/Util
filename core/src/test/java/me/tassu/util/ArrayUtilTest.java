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

import com.google.common.collect.Lists;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static me.tassu.util.ArrayUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilTest {

    @Test
    public void testJoin() {
        assertArrayEquals(new Integer[] { 1, 2, 3 }, join(new Integer[] { 1, 2 }, 3));
        assertArrayEquals(new Integer[] { 1, 2, 3, 3, 4 }, join(new Integer[] { 1, 2, 3 }, 3, 4));
    }

    @Test
    public void testConcatenate() {
        assertArrayEquals(new Integer[] { 1, 2, 3 }, concatenate(new Integer[] { 1, 2 }, new Integer[] { 3 }));
        assertArrayEquals(new Integer[] { 1, 2, 3, 3 }, concatenate(new Integer[] { 1, 2, 3 }, new Integer[] { 3 }));
    }

    @Test
    public void testWithoutFirstCorrectly() {
        val result = withoutFirst(Lists.newArrayList("123", "456", "789"));

        assertEquals(2, result.size());

        assertEquals("456", result.get(0));
        assertEquals("789", result.get(1));
    }


    @Test
    public void testWithoutFirstIncorrectly() {
        assertThrows(IllegalArgumentException.class, () -> withoutFirst(new ArrayList<>()));
    }

    @Test
    public void testGetLast() {
        assertEquals(Optional.empty(), getLast(Lists.newArrayList()));
        assertEquals(Optional.of("asd"), getLast(Lists.newArrayList("asd")));
        assertEquals(Optional.of("quack"), getLast(Lists.newArrayList("asd", "quack")));
    }

}