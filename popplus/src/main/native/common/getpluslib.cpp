//
// Created by Ignat on 26/07/2019
//

#include <jni.h>
#include "onload.hpp"
#include "getpluslib.hpp"
#include "utils.hpp"
#include "json.hpp"

#include <iostream>
#include <iomanip>
#include <string>
#include <vector>
#include <cassert>
#include <zconf.h>
#include <jni.h>
#include <jni.h>
#include <regex>

using json = nlohmann::json;

extern "C" jstring Java_id_gpi_popplus_service_GetPlusLib_EncryptionProcess(JNIEnv *env, jclass clazz)
{
	json j1;
	j1["BaseURL"] = "https://dev-popplus.gpiapis.com";
	j1["ClientID"] = "602027FB561E08105A23F098B4488D0B97AD53265E1BF58522D79A535A4FF715";
	j1["ClientSecret"] = "8FDA2B4A2E4675F7379FE33CAA22DA8B0892E8EFFE2F09F8268E208CA5818FA8";
	j1["DeviceID"] = "356048081566662";
	j1["RandomKey"] = getRandomKey(32);
	j1["Scope"] = "Mobile POS";
	j1["Timestamp"] = getTimeStamp();

	const char* encResult = EncryptData(j1.dump().c_str());
	return env->NewStringUTF(encResult);
//	return env->NewStringUTF(j1.dump().c_str());
}

extern "C" jstring Java_id_gpi_popplus_service_GetPlusLib_GetRSNProcess(JNIEnv *env, jclass clazz, jstring DeviceID, jstring Serial, jstring Imei)
{
	const char *ccDeviceID = env->GetStringUTFChars(DeviceID, 0);
	const char *ccSerial = env->GetStringUTFChars(Serial, 0);
	const char *ccImei = env->GetStringUTFChars(Imei, 0);

	std::string DeviceRSN;

	std::string deviceid(ccDeviceID);
	std::string copy1("");
	std::string cmpr("00000000");

	if (deviceid.find(cmpr) == std::string::npos) {
		copy1 = deviceid;
		std::reverse(copy1.begin(), copy1.end());

		if((std::atoi(copy1.substr(0,1).c_str()) % 2) == 0)
			copy1 = "2" + copy1;
		else
			copy1 = "1" + copy1;

		if((std::atoi(copy1.substr(copy1.length()-1,1).c_str()) % 2) == 0)
			copy1 = copy1 + "2";
		else
			copy1 = copy1 + "1";
	}

	std::string serial(ccSerial);
	std::string copy2("");

	if (serial.find(cmpr) == std::string::npos) {
		copy2 = serial;
		std::reverse(copy2.begin(), copy2.end());

		if((std::atoi(copy2.substr(0,1).c_str()) % 2) == 0)
			copy2 = "2" + copy2;
		else
			copy2 = "1" + copy2;

		if((std::atoi(copy2.substr(copy2.length()-1,1).c_str()) % 2) == 0)
			copy2 = copy2 + "2";
		else
			copy2 = copy2 + "1";
	}

	std::string imei(ccImei);
	std::string copy3("");

	if (imei.find(cmpr) == std::string::npos) {
		copy3 = imei;
		std::reverse(copy3.begin(), copy3.end());

		if((std::atoi(copy3.substr(0,1).c_str()) % 2) == 0)
			copy3 = "2" + copy3;
		else
			copy3 = "1" + copy3;

		if((std::atoi(copy3.substr(copy3.length()-1,1).c_str()) % 2) == 0)
			copy3 = copy3 + "2";
		else
			copy3 = copy3 + "1";
	}

	DeviceRSN = copy1 + copy2 + copy3;

	return env-> NewStringUTF(copy2.c_str());
}

namespace android
{
  double poppluslibversion() {
    return GETPLUSLIB_VERSION;
  }
}

using namespace android;

static JNINativeMethod g_NativeMethods[] =
{
	{"Version", "()D", (void *) poppluslibversion}
};

int register_getpluslib_android(JNIEnv* env) {
  return jniRegisterNativeMethods(env, classpath, g_NativeMethods,
    sizeof(g_NativeMethods) / sizeof(g_NativeMethods[0]));
}

/*
jint JNI_OnLoad(JavaVM* pJavaVM, void* pReserved)
{
	JNIEnv* pEnv;

	if(pJavaVM->GetEnv(reinterpret_cast<void**>(&pEnv), JNI_VERSION_1_6) != JNI_OK)
		return -1;

	static jclass javaLibClass = pEnv->FindClass("id/gpi/getplussdk/common/GetPlusLib");
	if(pEnv->RegisterNatives(javaLibClass, g_NativeMethods, sizeof(g_NativeMethods) / sizeof(g_NativeMethods[0])) < 0)
	{
		if (pEnv->ExceptionOccurred())
		{
			LOGV("Error -2");
			return -2; //Return with error
		}
		else
		{
			LOGV("Error -3");
			return -3; //Return with error
		}
	}

	LOGV("Engine library successfully loaded");

	return JNI_VERSION_1_6;
}

jstring DataEnkrip()
{
	CryptoPP::byte key[CryptoPP::AES::DEFAULT_KEYLENGTH];
	CryptoPP::byte iv[CryptoPP::AES::BLOCKSIZE];
	memset( key, 0x00, CryptoPP::AES::DEFAULT_KEYLENGTH );
	memset( iv, 0x00, CryptoPP::AES::BLOCKSIZE );
}
*/
