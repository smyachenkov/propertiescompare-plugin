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
import org.propertiescompare.main.compare.result.ResultDifferentEntries;

public class ResultDifferentEntriesTest extends AbstractResultTest {

  @Test
  public void testResultDifferentEntries() {
    String sameKey = RandomStringUtils.random(10);
    String sameValue = RandomStringUtils.random(10);
    leftProperties.put(sameKey, sameValue);
    rightProperties.put(sameKey, sameValue);
    ResultDifferentEntries resultDifferentEntries = new ResultDifferentEntries(leftProperties, rightProperties);

    Assert.assertEquals(leftProperties.size() - 1 , resultDifferentEntries.getLeftSide().size());
    Assert.assertEquals(rightProperties.size() - 1, resultDifferentEntries.getRightSide().size());

  }

}
