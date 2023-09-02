#include <jni.h>
#include "java_class_JniLoader.h"
JNIEXPORT jobjectArray JNICALL Java_java_1class_JniLoader_calculate
  (JNIEnv* env, jobject thisObject, jobjectArray data, jobjectArray kernel)
 {
     int size1 = env->GetArrayLength(data);
     int size2 = env->GetArrayLength(kernel);

     if (size1 != size2) {
         return nullptr;
     }

     int rowSize = 0;

     jdoubleArray firstRow = (jdoubleArray)env->GetObjectArrayElement(data, 0);
     rowSize = env->GetArrayLength(firstRow);
     env->DeleteLocalRef(firstRow);


     jclass doubleArrayClass = env->FindClass("[D");
     jobjectArray resultArray = env->NewObjectArray(size1, doubleArrayClass, nullptr);

     for (int i = 0; i < size1; i++) {
         jdoubleArray inputRow = (jdoubleArray)env->GetObjectArrayElement(data, i);
         jdouble* inputData = env->GetDoubleArrayElements(inputRow, nullptr);

         jdoubleArray kernelRow = (jdoubleArray)env->GetObjectArrayElement(kernel, i);
         jdouble* kernelData = env->GetDoubleArrayElements(kernelRow, nullptr);

         jdoubleArray resultRow = env->NewDoubleArray(rowSize);
         jdouble* resultData = new jdouble[rowSize];

         for (int j = 0; j < rowSize; j++) {
             resultData[j] = 0.0;

             for (int k = 0; k < rowSize; k++) {
                 resultData[j] += inputData[k] * kernelData[rowSize - k - 1];
             }
         }

         env->SetDoubleArrayRegion(resultRow, 0, rowSize, resultData);
         env->SetObjectArrayElement(resultArray, i, resultRow);
         env->ReleaseDoubleArrayElements(inputRow, inputData, JNI_ABORT);
         env->ReleaseDoubleArrayElements(kernelRow, kernelData, JNI_ABORT);
         env->DeleteLocalRef(inputRow);
         env->DeleteLocalRef(kernelRow);
         delete[] resultData;
     }

     return resultArray;
 }


