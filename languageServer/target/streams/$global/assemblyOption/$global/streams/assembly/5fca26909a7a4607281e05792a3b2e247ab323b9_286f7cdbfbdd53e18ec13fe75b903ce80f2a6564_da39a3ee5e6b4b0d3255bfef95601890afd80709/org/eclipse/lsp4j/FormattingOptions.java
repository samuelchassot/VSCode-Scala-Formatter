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

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.lsp4j.jsonrpc.messages.Either3;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

/**
 * Value-object describing what options formatting should use.
 */
@SuppressWarnings("all")
public class FormattingOptions extends LinkedHashMap<String, Either3<String, Number, Boolean>> {
  private static final String TAB_SIZE = "tabSize";
  
  private static final String INSERT_SPACES = "insertSpaces";
  
  public FormattingOptions() {
  }
  
  public FormattingOptions(final int tabSize, final boolean insertSpaces) {
    this.setTabSize(tabSize);
    this.setInsertSpaces(insertSpaces);
  }
  
  /**
   * @deprecated See https://github.com/eclipse/lsp4j/issues/99
   */
  @Deprecated
  public FormattingOptions(final int tabSize, final boolean insertSpaces, final Map<String, String> properties) {
    this(tabSize, insertSpaces);
    this.setProperties(properties);
  }
  
  public String getString(final String key) {
    Either3<String, Number, Boolean> _get = this.get(key);
    String _first = null;
    if (_get!=null) {
      _first=_get.getFirst();
    }
    return _first;
  }
  
  public void putString(final String key, final String value) {
    this.put(key, Either3.<String, Number, Boolean>forFirst(value));
  }
  
  public Number getNumber(final String key) {
    Either3<String, Number, Boolean> _get = this.get(key);
    Number _second = null;
    if (_get!=null) {
      _second=_get.getSecond();
    }
    return _second;
  }
  
  public void putNumber(final String key, final Number value) {
    this.put(key, Either3.<String, Number, Boolean>forSecond(value));
  }
  
  public Boolean getBoolean(final String key) {
    Either3<String, Number, Boolean> _get = this.get(key);
    Boolean _third = null;
    if (_get!=null) {
      _third=_get.getThird();
    }
    return _third;
  }
  
  public void putBoolean(final String key, final Boolean value) {
    this.put(key, Either3.<String, Number, Boolean>forThird(value));
  }
  
  /**
   * Size of a tab in spaces.
   */
  public int getTabSize() {
    final Number value = this.getNumber(FormattingOptions.TAB_SIZE);
    if ((value != null)) {
      return value.intValue();
    } else {
      return 0;
    }
  }
  
  public void setTabSize(final int tabSize) {
    this.putNumber(FormattingOptions.TAB_SIZE, Integer.valueOf(tabSize));
  }
  
  /**
   * Prefer spaces over tabs.
   */
  public boolean isInsertSpaces() {
    final Boolean value = this.getBoolean(FormattingOptions.INSERT_SPACES);
    if ((value != null)) {
      return (value).booleanValue();
    } else {
      return false;
    }
  }
  
  public void setInsertSpaces(final boolean insertSpaces) {
    this.putBoolean(FormattingOptions.INSERT_SPACES, Boolean.valueOf(insertSpaces));
  }
  
  /**
   * @deprecated See https://github.com/eclipse/lsp4j/issues/99
   */
  @Deprecated
  public Map<String, String> getProperties() {
    final LinkedHashMap<String, String> properties = CollectionLiterals.<String, String>newLinkedHashMap();
    Set<Map.Entry<String, Either3<String, Number, Boolean>>> _entrySet = this.entrySet();
    for (final Map.Entry<String, Either3<String, Number, Boolean>> entry : _entrySet) {
      {
        Serializable _switchResult = null;
        Either3<String, Number, Boolean> _value = entry.getValue();
        final Either3<String, Number, Boolean> it = _value;
        boolean _matched = false;
        boolean _isFirst = it.isFirst();
        if (_isFirst) {
          _matched=true;
          _switchResult = it.getFirst();
        }
        if (!_matched) {
          boolean _isSecond = it.isSecond();
          if (_isSecond) {
            _matched=true;
            _switchResult = it.getSecond();
          }
        }
        if (!_matched) {
          boolean _isThird = it.isThird();
          if (_isThird) {
            _matched=true;
            _switchResult = it.getThird();
          }
        }
        final Serializable value = _switchResult;
        if ((value != null)) {
          properties.put(entry.getKey(), value.toString());
        }
      }
    }
    return Collections.<String, String>unmodifiableMap(properties);
  }
  
  /**
   * @deprecated See https://github.com/eclipse/lsp4j/issues/99
   */
  @Deprecated
  public void setProperties(final Map<String, String> properties) {
    Set<Map.Entry<String, String>> _entrySet = properties.entrySet();
    for (final Map.Entry<String, String> entry : _entrySet) {
      this.putString(entry.getKey(), entry.getValue());
    }
  }
}
