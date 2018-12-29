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

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * The parameters of a Workspace Symbol Request.
 */
@SuppressWarnings("all")
public class WorkspaceSymbolParams {
  /**
   * A non-empty query string
   */
  @NonNull
  private String query;
  
  public WorkspaceSymbolParams() {
  }
  
  public WorkspaceSymbolParams(@NonNull final String query) {
    this.query = query;
  }
  
  /**
   * A non-empty query string
   */
  @Pure
  @NonNull
  public String getQuery() {
    return this.query;
  }
  
  /**
   * A non-empty query string
   */
  public void setQuery(@NonNull final String query) {
    this.query = query;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("query", this.query);
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
    WorkspaceSymbolParams other = (WorkspaceSymbolParams) obj;
    if (this.query == null) {
      if (other.query != null)
        return false;
    } else if (!this.query.equals(other.query))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return 31 * 1 + ((this.query== null) ? 0 : this.query.hashCode());
  }
}
