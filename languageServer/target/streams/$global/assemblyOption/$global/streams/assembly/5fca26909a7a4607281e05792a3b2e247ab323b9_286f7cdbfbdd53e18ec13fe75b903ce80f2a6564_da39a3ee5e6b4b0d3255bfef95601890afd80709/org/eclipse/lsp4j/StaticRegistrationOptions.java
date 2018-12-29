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

import org.eclipse.lsp4j.TextDocumentRegistrationOptions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Static registration options to be returned in the initialize request.
 */
@SuppressWarnings("all")
public class StaticRegistrationOptions extends TextDocumentRegistrationOptions {
  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also Registration#id.
   */
  private String id;
  
  public StaticRegistrationOptions() {
  }
  
  public StaticRegistrationOptions(final String id) {
    this.id = id;
  }
  
  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also Registration#id.
   */
  @Pure
  public String getId() {
    return this.id;
  }
  
  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also Registration#id.
   */
  public void setId(final String id) {
    this.id = id;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("id", this.id);
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
    StaticRegistrationOptions other = (StaticRegistrationOptions) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return 31 * super.hashCode() + ((this.id== null) ? 0 : this.id.hashCode());
  }
}
