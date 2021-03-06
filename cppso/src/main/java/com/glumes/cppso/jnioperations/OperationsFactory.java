package com.glumes.cppso.jnioperations;

import android.util.SparseArray;

import com.glumes.cppso.utils.ConstantsKt;

/**
 * @Author glumes
 */
public class OperationsFactory {


    private SparseArray<BaseOperation> mOperations = new SparseArray<>();

    private static class OperationsFactoryHolder {
        private static OperationsFactory mInstance = new OperationsFactory();
    }

    private OperationsFactory() {

    }

    public static OperationsFactory getInstance() {
        return OperationsFactoryHolder.mInstance;
    }

    public BaseOperation getOperations(int type) {

        BaseOperation ops = mOperations.get(type);

        if (ops != null) {
            return ops;
        }

        switch (type) {
            case ConstantsKt.NATIVE_BASIC_TYPE:
                ops = new BasicTypeOps();
                break;
            case ConstantsKt.NATIVE_STRING:
                ops = new StringTypeOps();
                break;
            case ConstantsKt.NATIVE_ARRAY:
                ops = new ArrayTypeOps();
                break;
            case ConstantsKt.NATIVE_FIELD_AND_METHOD:
                ops = new FieldAndMethodOps();
                break;
            case ConstantsKt.NATIVE_INVOKE_CONSTRUCTORS:
                ops = new InvokeConstructorOps();
                break;
            case ConstantsKt.NATIVE_CACHE_FIELD_AND_METHOD:
                ops = new CacheFieldAndMethodOps();
                break;
            case ConstantsKt.NATIVE_LOCAL_AND_GLOBAL_REFERENCES:
                ops = new LocalAndGlobalReferenceOps();
                break;
            case ConstantsKt.NATIVE_EXCEPTIONS_OPERATIONS:
                ops = new ExceptionOps();
                break;
            case ConstantsKt.NATIVE_ON_LOAD:
                ops = new NativeOnLoadOps();
                break;
            case ConstantsKt.NATIVE_THREAD_OPS:
                ops = new ThreadOps();
                break;
            default:
                break;
        }

        if (ops != null) {
            mOperations.put(type, ops);
            return mOperations.get(type);
        }

        // default operations
        return new NoOperation();

    }
}
