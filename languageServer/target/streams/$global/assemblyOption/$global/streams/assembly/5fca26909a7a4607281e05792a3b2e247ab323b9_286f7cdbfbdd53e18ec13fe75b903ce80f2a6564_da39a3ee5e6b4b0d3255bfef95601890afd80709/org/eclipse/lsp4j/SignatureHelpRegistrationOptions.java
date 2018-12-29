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
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public class SignatureHelpRegistrationOptions extends TextDocumentRegistrationOptions {
  /**
   * The characters that trigger signature help automatically.
   */
  private List<String> triggerCharacters;
  
  public SignatureHelpRegistrationOptions() {
  }
  
  public SignatureHelpRegistrationOptions(final List<String> triggerCharacters) {
    this.triggerCharacters = triggerCharacters;
  }
  
  /**
   * The characters that trigger signature help automatically.
   */
  @Pure
  public List<String> getTriggerCharacters() {
    return this.triggerCharacters;
  }
  
  /**
   * The characters that trigger signature help automatically.
   */
  public void setTriggerCharacters(final List<String> triggerCharacters) {
    this.triggerCharacters = triggerCharacters;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("triggerCharacters", this.triggerCharacters);
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
    SignatureHelpRegistrationOptions other = (SignatureHelpRegistrationOptions) obj;
    if (this.triggerCharacters == null) {
      if (other.triggerCharacters != null)
        return false;
    } else if (!this.triggerCharacters.equals(other.triggerCharacters))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return 31 * super.hashCode() + ((this.triggerCharacters== null) ? 0 : this.triggerCharacters.hashCode());
  }
}
