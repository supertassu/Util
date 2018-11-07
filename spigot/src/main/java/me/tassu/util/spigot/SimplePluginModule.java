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

package me.tassu.util.spigot;

import com.google.inject.AbstractModule;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

@AllArgsConstructor
public class SimplePluginModule<T extends JavaPlugin> extends AbstractModule {

    private final Class<T> clazz;
    private final T plugin;

    @Override
    protected void configure() {
        bind(clazz).toInstance(plugin);
        bind(Logger.class).toInstance(LoggerFactory.getLogger(plugin.getLogger().getName()));
        bind(PluginDescriptionFile.class).toInstance(plugin.getDescription());

        bind(Path.class)
                .annotatedWith(PluginFolder.class)
                .toInstance(plugin.getDataFolder().toPath());

        bind(Server.class).toInstance(Bukkit.getServer());
        bind(BukkitScheduler.class).toInstance(Bukkit.getScheduler());
        bind(PluginManager.class).toInstance(Bukkit.getPluginManager());
    }
}
