#include <jni.h>
#include <cstring>
#include <string>
#include <android/log.h>

jbyteArray charToJbyteArray(JNIEnv *env, char *src, int buffer_size) {
	int i;
	unsigned char buff[buffer_size];

	for (i=0;i<buffer_size;i++)
		buff[i] = src[i];

	jbyteArray array = env->NewByteArray(buffer_size);
	env->SetByteArrayRegion(array, 0, buffer_size, (jbyte*) buff);
	return array;
}

jstring charToJstring(JNIEnv* env, const char* src) {
	std::string strTmp(src);
	return env->NewStringUTF(strTmp.c_str());
}