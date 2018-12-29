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

import org.eclipse.lsp4j.CompletionItemCapabilities;
import org.eclipse.lsp4j.CompletionItemKindCapabilities;
import org.eclipse.lsp4j.DynamicRegistrationCapabilities;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Capabilities specific to the `textDocument/completion`
 */
@SuppressWarnings("all")
public class CompletionCapabilities extends DynamicRegistrationCapabilities {
  /**
   * The client supports the following `CompletionItem` specific
   * capabilities.
   */
  private CompletionItemCapabilities completionItem;
  
  /**
   * The client supports the following `CompletionItemKind` specific
   * capabilities.
   */
  private CompletionItemKindCapabilities completionItemKind;
  
  /**
   * The client supports sending additional context information for a
   * `textDocument/completion` request.
   */
  private Boolean contextSupport;
  
  public CompletionCapabilities() {
  }
  
  public CompletionCapabilities(final CompletionItemCapabilities completionItem) {
    this.completionItem = completionItem;
  }
  
  public CompletionCapabilities(final CompletionItemKindCapabilities completionItemKind) {
    this.completionItemKind = completionItemKind;
  }
  
  public CompletionCapabilities(final Boolean contextSupport) {
    this.contextSupport = contextSupport;
  }
  
  /**
   * The client supports the following `CompletionItem` specific
   * capabilities.
   */
  @Pure
  public CompletionItemCapabilities getCompletionItem() {
    return this.completionItem;
  }
  
  /**
   * The client supports the following `CompletionItem` specific
   * capabilities.
   */
  public void setCompletionItem(final CompletionItemCapabilities completionItem) {
    this.completionItem = completionItem;
  }
  
  /**
   * The client supports the following `CompletionItemKind` specific
   * capabilities.
   */
  @Pure
  public CompletionItemKindCapabilities getCompletionItemKind() {
    return this.completionItemKind;
  }
  
  /**
   * The client supports the following `CompletionItemKind` specific
   * capabilities.
   */
  public void setCompletionItemKind(final CompletionItemKindCapabilities completionItemKind) {
    this.completionItemKind = completionItemKind;
  }
  
  /**
   * The client supports sending additional context information for a
   * `textDocument/completion` request.
   */
  @Pure
  public Boolean getContextSupport() {
    return this.contextSupport;
  }
  
  /**
   * The client supports sending additional context information for a
   * `textDocument/completion` request.
   */
  public void setContextSupport(final Boolean contextSupport) {
    this.contextSupport = contextSupport;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("completionItem", this.completionItem);
    b.add("completionItemKind", this.completionItemKind);
    b.add("contextSupport", this.contextSupport);
    b.add("dynamicRegistration", getDynamicRegistration());
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
    CompletionCapabilities other = (CompletionCapabilities) obj;
    if (this.completionItem == null) {
      if (other.completionItem != null)
        return false;
    } else if (!this.completionItem.equals(other.completionItem))
      return false;
    if (this.completionItemKind == null) {
      if (other.completionItemKind != null)
        return false;
    } else if (!this.completionItemKind.equals(other.completionItemKind))
      return false;
    if (this.contextSupport == null) {
      if (other.contextSupport != null)
        return false;
    } else if (!this.contextSupport.equals(other.contextSupport))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.completionItem== null) ? 0 : this.completionItem.hashCode());
    result = prime * result + ((this.completionItemKind== null) ? 0 : this.completionItemKind.hashCode());
    return prime * result + ((this.contextSupport== null) ? 0 : this.contextSupport.hashCode());
  }
}
