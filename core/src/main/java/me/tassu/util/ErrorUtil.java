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

public final class ErrorUtil {

    private ErrorUtil() {}

    /**
     * Utility to convert any exceptions to a {@link RuntimeException}.
     * @param in Runnable to run.
     */
    public static void run(ErrorRunnable in) {
        try {
            in.run();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Utility to convert any exceptions to a {@link RuntimeException}.
     * @param in Runnable to run.
     * @param message Message to use in the RuntimeException.
     */
    public static void run(ErrorRunnable in, String message) {
        try {
            in.run();
        } catch (Throwable ex) {
            throw new RuntimeException(message, ex);
        }
    }

    /**
     * A {@link Runnable} where {@link Runnable#run()} can throw anything.
     */
    @FunctionalInterface
    public interface ErrorRunnable {
        void run() throws Throwable;
    }

}
