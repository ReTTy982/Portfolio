#include <iostream>
#include <jni.h>

JNIEXPORT jobjectArray JNICALL Java_edu_pwr_convolution_ConvolutionSolverJNI_convolution
  (JNIEnv *, jobject, jobjectArray, jobjectArray){
  std::cout << "Czesc" << std::endl;
  };