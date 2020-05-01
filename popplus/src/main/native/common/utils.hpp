#include <jni.h>

jbyteArray charToJbyteArray(JNIEnv *env, char *src, int buffer_size);
jstring charToJstring(JNIEnv* env, const char* src);