//
// Created by aignatd on 06-Aug-19.
//

#include <jni.h>
#include <android/log.h>
#include <string.h>

#ifndef _UTILS_HEADER_H__
#define _UTILS_HEADER_H__

const char* getTimeStamp();
const char* getAuthKey();
const char* getRandomKey(size_t length);
const char* EncryptData(const char* data);

#endif // _UTILS_HEADER_H__
