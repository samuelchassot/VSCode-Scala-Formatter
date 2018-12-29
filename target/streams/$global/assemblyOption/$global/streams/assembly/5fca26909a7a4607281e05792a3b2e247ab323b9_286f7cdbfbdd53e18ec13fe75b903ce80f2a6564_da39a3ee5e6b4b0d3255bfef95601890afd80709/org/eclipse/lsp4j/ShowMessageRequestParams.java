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

import java.util.List;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * The show message request is sent from a server to a client to ask the client to display a particular message in the
 * user class. In addition to the show message notification the request allows to pass actions and to wait for an
 * answer from the client.
 */
@SuppressWarnings("all")
public class ShowMessageRequestParams extends MessageParams {
  /**
   * The message action items to present.
   */
  private List<MessageActionItem> actions;
  
  public ShowMessageRequestParams() {
  }
  
  public ShowMessageRequestParams(final List<MessageActionItem> actions) {
    this.actions = actions;
  }
  
  /**
   * The message action items to present.
   */
  @Pure
  public List<MessageActionItem> getActions() {
    return this.actions;
  }
  
  /**
   * The message action items to present.
   */
  public void setActions(final List<MessageActionItem> actions) {
    this.actions = actions;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("actions", this.actions);
    b.add("type", getType());
    b.add("message", getMessage());
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
    if (!super.equals(obj))
      return false;
    ShowMessageRequestParams other = (ShowMessageRequestParams) obj;
    if (this.actions == null) {
      if (other.actions != null)
        return false;
    } else if (!this.actions.equals(other.actions))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return 31 * super.hashCode() + ((this.actions== null) ? 0 : this.actions.hashCode());
  }
}
