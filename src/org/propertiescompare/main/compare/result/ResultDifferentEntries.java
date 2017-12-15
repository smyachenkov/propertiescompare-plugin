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
package org.propertiescompare.main.compare.result;

import com.google.common.collect.Sets;
import com.intellij.openapi.util.Pair;
import org.propertiescompare.main.compare.PropertyFile;
import org.propertiescompare.main.compare.LoadedProperties;
import org.propertiescompare.main.compare.PropertiesEntry;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ResultDifferentEntries extends CompareResult {

  public ResultDifferentEntries(PropertyFile left, PropertyFile right) {
    super(left, right);
  }

  @Override
  protected Pair<TreeSet<PropertiesEntry>, TreeSet<PropertiesEntry>> processProperties(LoadedProperties left,
                                                                                       LoadedProperties right) {
    Set<String> keyIntersection = Sets.intersection(left.getKeySet(), right.getKeySet());

    Set<String> fullIntersection = keyIntersection.stream()
        .filter(key -> left.getProperty(key).equals(right.getProperty(key)))
        .collect(Collectors.toSet());

    return new Pair<>(excludePropertyKeys(left, fullIntersection), excludePropertyKeys(right, fullIntersection));
  }

  private TreeSet<PropertiesEntry> excludePropertyKeys(LoadedProperties properties, Set<String> keys) {
    return properties.getKeySet().stream()
        .filter(key -> !keys.contains(key))
        .map(key -> new PropertiesEntry(key, properties.getProperty(key)))
        .collect(Collectors.toCollection(TreeSet::new));
  }
}
