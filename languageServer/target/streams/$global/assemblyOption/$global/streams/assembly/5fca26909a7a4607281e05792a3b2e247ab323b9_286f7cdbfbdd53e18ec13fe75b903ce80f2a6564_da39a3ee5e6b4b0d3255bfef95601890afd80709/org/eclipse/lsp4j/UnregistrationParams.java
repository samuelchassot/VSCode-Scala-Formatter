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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.lsp4j.Unregistration;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * The client/unregisterCapability request is sent from the server to the client to unregister
 * a previously registered capability.
 */
@SuppressWarnings("all")
public class UnregistrationParams {
  @NonNull
  private List<Unregistration> unregisterations;
  
  public UnregistrationParams() {
    this(new ArrayList<Unregistration>());
  }
  
  public UnregistrationParams(@NonNull final List<Unregistration> unregisterations) {
    this.unregisterations = unregisterations;
  }
  
  @Pure
  @NonNull
  public List<Unregistration> getUnregisterations() {
    return this.unregisterations;
  }
  
  public void setUnregisterations(@NonNull final List<Unregistration> unregisterations) {
    this.unregisterations = unregisterations;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("unregisterations", this.unregisterations);
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
    UnregistrationParams other = (UnregistrationParams) obj;
    if (this.unregisterations == null) {
      if (other.unregisterations != null)
        return false;
    } else if (!this.unregisterations.equals(other.unregisterations))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return 31 * 1 + ((this.unregisterations== null) ? 0 : this.unregisterations.hashCode());
  }
}
