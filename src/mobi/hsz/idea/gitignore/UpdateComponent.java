/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 hsz Jakub Chrzanowski <jakub@hsz.mobi>
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

package mobi.hsz.idea.gitignore;

import com.intellij.notification.*;
import com.intellij.openapi.components.ProjectComponent;
import mobi.hsz.idea.gitignore.lang.IgnoreLanguage;
import mobi.hsz.idea.gitignore.util.Utils;
import org.jetbrains.annotations.NotNull;

/**
 * @author Jakub Chrzanowski <jakub@hsz.mobi>
 * @since 1.3
 */
public class UpdateComponent implements ProjectComponent {

    private IgnoreApplicationComponent application;

    @Override
    public void initComponent() {
        application = IgnoreApplicationComponent.getInstance();
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "UpdateComponent";
    }

    @Override
    public void projectOpened() {
        if (application.isUpdated() && !application.isUpdateNotificationShown()) {
            application.setUpdateNotificationShown(true);
            NotificationGroup group = new NotificationGroup(IgnoreLanguage.GROUP, NotificationDisplayType.STICKY_BALLOON, true);
            Notification notification = group.createNotification(
                    IgnoreBundle.message("update.title", Utils.getVersion()),
                    IgnoreBundle.message("update.content"),
                    NotificationType.INFORMATION,
                    NotificationListener.URL_OPENING_LISTENER
            );
            Notifications.Bus.notify(notification);
        }
    }

    @Override
    public void projectClosed() {

    }
}
