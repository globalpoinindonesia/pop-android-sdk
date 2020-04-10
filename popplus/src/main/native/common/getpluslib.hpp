//
// Created by aignatd on 06-Aug-19.
//

#ifndef POPPLUS_POPPLUS_H
#define POPPLUS_POPPLUS_H

#include <jni.h>
#include <android/log.h>

#define DEBUG 1
#define GETPLUSLIB_VERSION 0.1

#define LOG_TAG "GETPLUS"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

#if DEBUG
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__)
#else
#define LOGV(...)
#endif

static const char* chrEncryptKey = "mBI+LWhYkOjClxjfuUrFZHHYfO17lBgRh4bLnVuuLRQ=";
static const char* chrInitVector = "hhMtWk0xUL6sFlkOrw9hXA==";
static const char* chrConsumerKey = "fAycQ9xEH0uobDv2twcclA";
static const char* chrSecretKey = "i2WzqlmoAkh4vTFZaEnFgsFsVO4Kq8AgLDU2PNCAtQg";

static const char *classpath = "id/gpi/popplus/service/GetPlusLib";

JNIEnv* getJNIEnv();
int jniThrowException(JNIEnv *env,const char* className,const char* msg);
int jniRegisterNativeMethods(JNIEnv* env,const char* className,const JNINativeMethod* gMethod,int numMethods);

#endif //POPPLUS_POPPLUS_H
