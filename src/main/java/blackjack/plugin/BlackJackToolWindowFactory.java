/*
 *  Copyright 2020 Mark Scott
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package blackjack.plugin;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class BlackJackToolWindowFactory implements DumbAware, ToolWindowFactory {

  /**
   *  Constructor
   */
  public BlackJackToolWindowFactory() {

  }

  /**
   * @param project Project
   * @param toolWindow ToolWindow
   */
  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    final ToolWindowManager manager = ToolWindowManager.getInstance(project);

    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(new BrowserPanel(new JavaFxBrowserView()),"", false);
    toolWindow.getContentManager().addContent(content);
    //toolWindow.getActivation().doWhenDone(() -> System.out.println("doWhenDone"));
    //toolWindow.activate(() -> System.out.println("activate"));
    //toolWindow.hide(() -> System.out.println("hide"));
    toolWindow.getContentManager().getComponent().addComponentListener(new ComponentListener() {
      @Override
      public void componentResized(ComponentEvent e) {}

      @Override
      public void componentMoved(ComponentEvent e) { }

      @Override
      public void componentShown(ComponentEvent e) {}

      @Override
      public void componentHidden(ComponentEvent e) {}
    });

    final ToolWindowManagerListener listener = new ToolWindowManagerListener() {
      @Override
      public void toolWindowRegistered(@NotNull String id) {}

      @Override
      public void stateChanged() {}
    };

    toolWindow.show(() -> ((ToolWindowManagerEx) manager).addToolWindowManagerListener(listener));
  }

}
