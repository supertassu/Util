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

package me.tassu.util.config;

import lombok.val;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializerCollection;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static me.tassu.util.ArrayUtil.withoutFirst;
import static me.tassu.util.ErrorUtil.run;

/**
 * Class providing configuration loaders.
 * @author tassu
 * @version 1.0.0
 */
public class ConfigFactory {

    private TypeSerializerCollection serializers;

    @SuppressWarnings("unused") // should allow usage like this, just not done currently.
    public ConfigFactory(Path folder) {
        this(folder, HoconConfigurationLoader.builder().getDefaultOptions().getSerializers());
    }

    @SuppressWarnings("WeakerAccess") // should allow usage like this, just not done currently.
    public ConfigFactory(Path folder, TypeSerializerCollection serializers) {
        this.folder = folder;
        this.serializers = serializers;
    }

    private Path folder;

    /**
     * Creates a ConfigurationLoader for given file and folder.
     * @param folder Folder to use.
     * @param file File to use.
     * @return a ConfigurationLoader.
     */
    @SuppressWarnings("WeakerAccess") // should allow usage like this, just not done currently.
    public ConfigurationLoader<CommentedConfigurationNode> getLoader(Path folder, String file) {
        // create the folder
        if (Files.notExists(folder.getParent())) {
            run(() -> Files.createDirectory(folder.getParent()));
        }

        if (Files.notExists(folder)) {
            run(() -> Files.createDirectory(folder));
        }

        // create the loader
        val builder = HoconConfigurationLoader.builder();

        return builder
                .setPath(folder.resolve(file))
                .setDefaultOptions(builder.getDefaultOptions().setSerializers(serializers))
                .build();
    }

    /**
     * Creates a ConfigurationLoader for given path inside given folder.
     * @param path Path to use. Everything but last element is a folder and last element is the file name.
     * @return a ConfigurationLoader.
     */
    @SuppressWarnings("WeakerAccess") // should allow usage like this, just not done currently.
    public ConfigurationLoader<CommentedConfigurationNode> getLoader(Path folder, List<String> path) {
        if (folder == null) throw new IllegalArgumentException("folder is null. do not do that kids");

        // Fix NPE
        if (Files.notExists(folder.getParent())) {
            run(() -> Files.createDirectory(folder.getParent()));
        }

        if (Files.notExists(folder)) {
            run(() -> Files.createDirectory(folder));
        }

        if (path.size() <= 0) {
            throw new IllegalArgumentException("path should not be empty");
        } else if (path.size() == 1) {
            return getLoader(folder, path.get(0));
        } else if (path.size() == 2) {
            return getLoader(folder.resolve(path.get(0)), path.get(1));
        }

        return getLoader(folder.resolve(path.get(0)), withoutFirst(path));
    }

    /**
     * Creates a ConfigurationLoader for given path inside given folder.
     * @param pathIn Path to use. Everything but last element is a folder and last element is the file name.
     * @return a ConfigurationLoader.
     */
    @SuppressWarnings("WeakerAccess") // should allow usage like this, just not done currently.
    public ConfigurationLoader<CommentedConfigurationNode> getLoader(Path folder, String... pathIn) {
        val path = Arrays.asList(pathIn);
        return getLoader(folder, path);
    }

    /**
     * Creates a ConfigurationLoader for given path inside the plugin's main folder.
     * @param path Path to use. Everything but last element is a folder and last element is the file name.
     * @return a ConfigurationLoader.
     */
    public ConfigurationLoader<CommentedConfigurationNode> getLoader(String... path) {
        return getLoader(folder, path);
    }

}
