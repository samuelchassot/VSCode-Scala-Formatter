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
import org.eclipse.lsp4j.TextDocumentRegistrationOptions;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public class DocumentOnTypeFormattingRegistrationOptions extends TextDocumentRegistrationOptions {
  /**
   * A character on which formatting should be triggered, like `}`.
   */
  @NonNull
  private String firstTriggerCharacter;
  
  /**
   * More trigger characters.
   */
  private List<String> moreTriggerCharacter;
  
  public DocumentOnTypeFormattingRegistrationOptions() {
  }
  
  public DocumentOnTypeFormattingRegistrationOptions(@NonNull final String firstTriggerCharacter) {
    this.firstTriggerCharacter = firstTriggerCharacter;
  }
  
  public DocumentOnTypeFormattingRegistrationOptions(@NonNull final String firstTriggerCharacter, final List<String> moreTriggerCharacter) {
    this.firstTriggerCharacter = firstTriggerCharacter;
    this.moreTriggerCharacter = moreTriggerCharacter;
  }
  
  /**
   * A character on which formatting should be triggered, like `}`.
   */
  @Pure
  @NonNull
  public String getFirstTriggerCharacter() {
    return this.firstTriggerCharacter;
  }
  
  /**
   * A character on which formatting should be triggered, like `}`.
   */
  public void setFirstTriggerCharacter(@NonNull final String firstTriggerCharacter) {
    this.firstTriggerCharacter = firstTriggerCharacter;
  }
  
  /**
   * More trigger characters.
   */
  @Pure
  public List<String> getMoreTriggerCharacter() {
    return this.moreTriggerCharacter;
  }
  
  /**
   * More trigger characters.
   */
  public void setMoreTriggerCharacter(final List<String> moreTriggerCharacter) {
    this.moreTriggerCharacter = moreTriggerCharacter;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("firstTriggerCharacter", this.firstTriggerCharacter);
    b.add("moreTriggerCharacter", this.moreTriggerCharacter);
    b.add("documentSelector", getDocumentSelector());
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
    DocumentOnTypeFormattingRegistrationOptions other = (DocumentOnTypeFormattingRegistrationOptions) obj;
    if (this.firstTriggerCharacter == null) {
      if (other.firstTriggerCharacter != null)
        return false;
    } else if (!this.firstTriggerCharacter.equals(other.firstTriggerCharacter))
      return false;
    if (this.moreTriggerCharacter == null) {
      if (other.moreTriggerCharacter != null)
        return false;
    } else if (!this.moreTriggerCharacter.equals(other.moreTriggerCharacter))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.firstTriggerCharacter== null) ? 0 : this.firstTriggerCharacter.hashCode());
    return prime * result + ((this.moreTriggerCharacter== null) ? 0 : this.moreTriggerCharacter.hashCode());
  }
}
