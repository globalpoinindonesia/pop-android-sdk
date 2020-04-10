#include<stdlib.h>
#include<android/log.h>
#include "onload.hpp"

extern int register_getpluslib_android(JNIEnv *env);
static JavaVM *sEnv;

int jniThrowException(JNIEnv *env, const char* className, const char* msg)
{
  jclass exceptionClass = env->FindClass(className);

  if (exceptionClass == NULL)
    return -1;

  if (env->ThrowNew(exceptionClass, msg) != JNI_OK) {
  }

  return 0;
}

JNIEnv* getJNIEnv()
{
  JNIEnv* env = NULL;

  if (sEnv->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK)
    return NULL;

  return env;
}

int jniRegisterNativeMethods(JNIEnv* env, const char* className,
        const JNINativeMethod* gMethods, int numMethods)
{
  jclass clazz;

  clazz = env->FindClass(className);

  if (clazz == NULL)
    return -1;

  if (env->RegisterNatives(clazz, gMethods, numMethods) < 0)
    return -1;

  return 0;
}

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
  JNIEnv* env = NULL;
  jint result = JNI_ERR;
  sEnv = vm;

  if (vm->GetEnv((void**) &env, JNI_VERSION_1_6) != JNI_OK)
    return result;

  if (register_getpluslib_android(env) != JNI_OK)
    goto end;

  return JNI_VERSION_1_6;
  end: return result;
}