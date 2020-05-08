// C++ JNI
#include <jni.h>
#include <vector>
#include <android/log.h>
#include "poppluslib.hpp"
#include "../encryptor/base64.hpp"
#include "utils.hpp"
#include "../encryptor/aes256.h"

unsigned char* DecodeEncryptKey() {
		return base64_decode(devkey).data;
}

unsigned char* DecodeEncryptIV() {
		return base64_decode(deviv).data;
}

extern "C" jstring Java_id_gpi_popplus_CredLib_UserAuth(JNIEnv *env, jclass/* clazz */) {
	return env-> NewStringUTF(chrUserAuth.c_str());
}

extern "C" jstring Java_id_gpi_popplus_CredLib_PassAuth(JNIEnv *env, jclass/* clazz */) {
	return env-> NewStringUTF(chrPassAuth.c_str());
}

extern "C" jstring Java_id_gpi_popplus_CredLib_DeviceRSN(JNIEnv *env, jclass/* clazz */, jstring DeviceID, jstring Serial, jstring Imei)
{
	const char *ccDeviceID = env->GetStringUTFChars(DeviceID, JNI_FALSE);
	const char *ccSerial = env->GetStringUTFChars(Serial, JNI_FALSE);
	const char *ccImei = env->GetStringUTFChars(Imei, JNI_FALSE);

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
	env->ReleaseStringUTFChars(DeviceID, ccDeviceID);
	env->ReleaseStringUTFChars(Serial, ccSerial);
	env->ReleaseStringUTFChars(Imei, ccImei);

	return env->NewStringUTF(DeviceRSN.c_str());
}

extern "C" jstring Java_id_gpi_popplus_CredLib_DataProcess(JNIEnv *env, jclass/* clazz */, jstring mingwen)
{
	aes256_context ctx;
	aes256_init(&ctx, DecodeEncryptKey());

	const char *mwChar = env->GetStringUTFChars(mingwen, JNI_FALSE);
	std::string strData(mwChar);

	size_t length = strData.length();
	int block_num = length / BLOCK_SIZE + 1;

	char* szDataIn = new char[block_num * BLOCK_SIZE + 1];
	memset(szDataIn, 0x00, static_cast<size_t>(block_num * BLOCK_SIZE + 1));
	strcpy(szDataIn, strData.c_str());

	// Do PKCS7Padding fill.
	int k = length % BLOCK_SIZE;
	int j = length / BLOCK_SIZE;
	int padding = BLOCK_SIZE - k;

	for (int i = 0; i < padding; i++)
	{
		szDataIn[j * BLOCK_SIZE + k + i] = static_cast<char>(padding);
	}

	szDataIn[block_num * BLOCK_SIZE] = '\0';
	char *szDataOut = new char[block_num * BLOCK_SIZE + 1];
	memset(szDataOut, 0, static_cast<size_t>(block_num * BLOCK_SIZE + 1));

	aes256_encrypt_cbc(&ctx, (unsigned char *)(szDataIn), DecodeEncryptIV(),
	                   (unsigned char *)(szDataOut));
	std::string enc = base64_encode((const uint8_t *)(szDataOut), strlen(szDataOut));

	delete[] szDataIn;
	delete[] szDataOut;
	env->ReleaseStringUTFChars(mingwen, mwChar);

	return env->NewStringUTF(enc.c_str());

/*
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
*/
}

extern "C" jstring Java_id_gpi_popplus_CredLib_DataCheck(JNIEnv *env, jclass/* clazz */, jstring mingwen)
{
	aes256_context ctx;
	aes256_init(&ctx, DecodeEncryptKey());

	const char *encryptChar = env->GetStringUTFChars(mingwen, JNI_FALSE);
	std::string strData(encryptChar);
	basedata decodebase64 = base64_decode(encryptChar);
	int len = decodebase64.len;

	unsigned char resbuf[len];

	for (int ii = 0; ii < len; ii++) {
		resbuf[ii] = *(decodebase64.data + ii);
	}

	uint8_t output[len];
	aes256_decrypt_cbc(&ctx, resbuf, DecodeEncryptIV(), output, len);

	// Go PKCS7Padding
	if (0x00 < output[len - 1] <= 0x16)
	{
		int tmp = output[len - 1];

		for (int i = len - 1; i >= len - tmp; i--)
		{
			if (output[i] != tmp)
			{
				memset(output, 0, (size_t)(len));
				break;
			}
			else
				output[i] = 0;
		}
	}

	env->ReleaseStringUTFChars(mingwen, encryptChar);
	return env->NewStringUTF((const char*) output);
}
