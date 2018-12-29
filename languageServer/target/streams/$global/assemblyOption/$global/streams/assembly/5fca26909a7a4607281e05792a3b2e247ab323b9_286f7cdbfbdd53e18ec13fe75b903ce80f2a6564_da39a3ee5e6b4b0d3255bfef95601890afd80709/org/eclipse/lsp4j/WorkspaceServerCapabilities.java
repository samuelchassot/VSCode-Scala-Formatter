/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import org.eclipse.lsp4j.WorkspaceFoldersOptions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Workspace specific server capabilities
 */
@SuppressWarnings("all")
public class WorkspaceServerCapabilities {
  /**
   * The server supports workspace folder.
   * 
   * Since 3.6.0
   */
  private WorkspaceFoldersOptions workspaceFolders;
  
  public WorkspaceServerCapabilities() {
  }
  
  public WorkspaceServerCapabilities(final WorkspaceFoldersOptions workspaceFolders) {
    this.workspaceFolders = workspaceFolders;
  }
  
  /**
   * The server supports workspace folder.
   * 
   * Since 3.6.0
   */
  @Pure
  public WorkspaceFoldersOptions getWorkspaceFolders() {
    return this.workspaceFolders;
  }
  
  /**
   * The server supports workspace folder.
   * 
   * Since 3.6.0
   */
  public void setWorkspaceFolders(final WorkspaceFoldersOptions workspaceFolders) {
    this.workspaceFolders = workspaceFolders;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("workspaceFolders", this.workspaceFolders);
    return b.toString();
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    WorkspaceServerCapabilities other = (WorkspaceServerCapabilities) obj;
    if (this.workspaceFolders == null) {
      if (other.workspaceFolders != null)
        return false;
    } else if (!this.workspaceFolders.equals(other.workspaceFolders))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return 31 * 1 + ((this.workspaceFolders== null) ? 0 : this.workspaceFolders.hashCode());
  }
}
