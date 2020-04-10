//
// Created by aignatd on 06-Aug-19.
//

#include <jni.h>
#include <android/log.h>

#ifndef _ON_LOAD_HEADER_H__
#define _ON_LOAD_HEADER_H__

JNIEnv* getJNIEnv();
int jniThrowException(JNIEnv *env, const char* className, const char* msg);
int jniRegisterNativeMethods(JNIEnv* env, const char* className, const JNINativeMethod* gMethod, int numMethods);

#endif // _ON_LOAD_HEADER_H__
