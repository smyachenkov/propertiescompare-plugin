/*
 * Copyright (c) 2017 Stanislav Myachenkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.propertiescompare.main.compare;

import org.jetbrains.annotations.NotNull;

public class PropertiesEntry implements Comparable<PropertiesEntry> {

  private String key;
  private String value;

  public PropertiesEntry(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public int compareTo(@NotNull PropertiesEntry entry) {
    int result = this.key.compareTo(entry.getKey());
    if (result == 0) {
      result = this.value.compareTo(entry.value);
    }
    return result;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
