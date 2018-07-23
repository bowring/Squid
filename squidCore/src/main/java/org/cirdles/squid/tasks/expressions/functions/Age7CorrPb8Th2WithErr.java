/* 
 * Copyright 2016 James F. Bowring and CIRDLES.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cirdles.squid.tasks.expressions.functions;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.List;
import static org.cirdles.squid.constants.Squid3Constants.lambda235;
import static org.cirdles.squid.constants.Squid3Constants.lambda238;
import static org.cirdles.squid.constants.Squid3Constants.uRatio;
import static org.cirdles.squid.constants.Squid3Constants.lambda232;
import static org.cirdles.squid.constants.Squid3Constants.sComm0_64;
import static org.cirdles.squid.constants.Squid3Constants.sComm0_76;
import static org.cirdles.squid.constants.Squid3Constants.sComm0_86;
import org.cirdles.squid.exceptions.SquidException;
import org.cirdles.squid.shrimp.ShrimpFractionExpressionInterface;
import org.cirdles.squid.tasks.TaskInterface;
import org.cirdles.squid.tasks.expressions.expressionTrees.ExpressionTreeInterface;
import static org.cirdles.squid.tasks.expressions.expressionTrees.ExpressionTreeInterface.convertObjectArrayToDoubles;

/**
 *
 * @author James F. Bowring
 */
@XStreamAlias("Operation")
public class Age7CorrPb8Th2WithErr extends Function {

    //private static final long serialVersionUID = -6711265919551953531L;
    /**
     * This method combines Ludwig's Age7CorrPb8Th2 and AgeErr7CorrPb8Th2.
     *
     * Ludwig specifies Age7CorrPb8Th2: Returns the 208Pb/232Th age, assuming
     * the true 206/204 is that required to force 206/238-207/235 concordance.
     *
     * Ludwig specifies AgeErr7CorrPb8Th2: Returns the error of the 208Pb/232Th
     * age, where the 208Pb/232Th age is calculated assuming the true 206/204 is
     * that required to force 206/238-207/235 concordance. The error is
     * calculated numerically, by successive perturbation of the input errors.
     *
     * @see
     * https://raw.githubusercontent.com/CIRDLES/LudwigLibrary/master/vbaCode/isoplot3Basic/Pub.bas
     * @see
     * https://raw.githubusercontent.com/CIRDLES/LudwigLibrary/master/vbaCode/isoplot3Basic/UPb.bas
     */
    public Age7CorrPb8Th2WithErr() {

        name = "Age7CorrPb8Th2WithErr";
        argumentCount = 8;
        precedence = 4;
        rowCount = 1;
        colCount = 2;
        labelsForOutputValues = new String[][]{{"Age", "1SigmaUnct"}};
        labelsForInputValues = new String[]{
            "Total 206/238, Total 206/238 1%Unct,"
            + "Total 208/232, Total 208/232 1%Unct, "
            + "Total 208/206, Total 208/206 1%Unct,"
            + "\"Total 207/206, Total 207/206 1%Unct"};
    }

    /**
     *
     * Requires that children 0 - 7 are VariableNodes that evaluate to a double
     * array with column 1 representing the values for Total 206/238, Total
     * 206/238 1%Unct, Total 208/232, Total 208/232 1%Unct, Total 208/206, Total
     * 208/206 1%Unct, Total 207/206, Total 207/206 1%Unct with a row for each
     * member of shrimpFractions.
     *
     * @param childrenET list containing child 0-7
     * @param shrimpFractions a list of shrimpFractions
     * @param task
     * @return the double[1][2] array of age, ageErr
     * @throws org.cirdles.squid.exceptions.SquidException
     */
    @Override
    public Object[][] eval(
            List<ExpressionTreeInterface> childrenET, List<ShrimpFractionExpressionInterface> shrimpFractions, TaskInterface task) throws SquidException {
        Object[][] retVal;
        try {
            double[] totPb206U238 = convertObjectArrayToDoubles(childrenET.get(0).eval(shrimpFractions, task)[0]);
            double[] totPb206U238percentErr = convertObjectArrayToDoubles(childrenET.get(1).eval(shrimpFractions, task)[0]);
            double[] totPb208Th232 = convertObjectArrayToDoubles(childrenET.get(2).eval(shrimpFractions, task)[0]);
            double[] totPb208Th232percentErr = convertObjectArrayToDoubles(childrenET.get(3).eval(shrimpFractions, task)[0]);
            double[] totPb86 = convertObjectArrayToDoubles(childrenET.get(4).eval(shrimpFractions, task)[0]);
            double[] totPb86percentErr = convertObjectArrayToDoubles(childrenET.get(5).eval(shrimpFractions, task)[0]);
            double[] totPb76 = convertObjectArrayToDoubles(childrenET.get(6).eval(shrimpFractions, task)[0]);
            double[] totPb76percentErr = convertObjectArrayToDoubles(childrenET.get(7).eval(shrimpFractions, task)[0]);
            double[] age7CorrPb8Th2WithErr = org.cirdles.ludwig.squid25.PbUTh_2.age7CorrPb8Th2WithErr(
                    totPb206U238[0], 
                    totPb206U238percentErr[0],
                    totPb208Th232[0],
                    totPb208Th232percentErr[0],
                    totPb86[0],
                    totPb86percentErr[0],
                    totPb76[0],
                    totPb76percentErr[0],
                    sComm0_64,sComm0_76,sComm0_86, lambda232, lambda235, lambda238, uRatio);
            retVal = new Object[][]{{age7CorrPb8Th2WithErr[0], age7CorrPb8Th2WithErr[1]}};
        } catch (ArithmeticException | IndexOutOfBoundsException | NullPointerException e) {
            retVal = new Object[][]{{0.0, 0.0}};
        }

        return retVal;
    }

    /**
     *
     * @param childrenET the value of childrenET
     * @return
     */
    @Override
    public String toStringMathML(List<ExpressionTreeInterface> childrenET) {

        StringBuilder retVal = new StringBuilder();
        retVal.append("<mrow>");
        retVal.append("<mi>").append(name).append("</mi>");
        retVal.append("<mfenced>");
        for (int i = 0; i < childrenET.size(); i++) {
            retVal.append(toStringAnotherExpression(childrenET.get(i))).append("&nbsp;\n");
        }

        retVal.append("</mfenced></mrow>\n");

        return retVal.toString();
    }

}