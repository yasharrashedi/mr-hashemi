package ninja.soroosh.hashem.lang.nodes.expression;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import ninja.soroosh.hashem.lang.HashemException;
import ninja.soroosh.hashem.lang.nodes.HashemBinaryNode;
import ninja.soroosh.hashem.lang.runtime.HashemBigNumber;

/**
 * This class is similar to the extensively documented {@link HashemAddNode}.
 */
@NodeInfo(shortName = "-")
public abstract class HashemSubNode extends HashemBinaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long sub(long left, long right) {
        return Math.subtractExact(left, right);
    }

    @Specialization
    @TruffleBoundary
    protected HashemBigNumber sub(HashemBigNumber left, HashemBigNumber right) {
        return new HashemBigNumber(left.getValue().subtract(right.getValue()));
    }

    @Fallback
    protected Object typeError(Object left, Object right) {
        throw HashemException.typeError(this, left, right);
    }

}
