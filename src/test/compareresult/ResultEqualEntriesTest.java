/*
 * Copyright (c) 2018 Stanislav Myachenkov
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
package compareresult;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.propertiescompare.main.compare.result.ResultEqualEntries;

public class ResultEqualEntriesTest extends AbstractResultTest {

  @Test
  public void resultEqualEntries() {
    String sameKey = RandomStringUtils.random(10);
    String sameValue = RandomStringUtils.random(10);
    this.leftProperties.put(sameKey, sameValue);
    this.rightProperties.put(sameKey, sameValue);

    ResultEqualEntries resultEqualEntries = new ResultEqualEntries(leftProperties, rightProperties);

    Assert.assertEquals(resultEqualEntries.getRightSide().size(), 1);
    Assert.assertEquals(resultEqualEntries.getLeftSide().size(), 1);
    Assert.assertEquals(resultEqualEntries.getLeftSide(), resultEqualEntries.getRightSide());
  }

}
