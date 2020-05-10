// C++ JNI
#include <jni.h>
#include <vector>
#include <android/log.h>
#include "poppluslib.hpp"
#include "../encryptor/base64.hpp"
#include "utils.hpp"
#include "../encryptor/aes256.h"

unsigned char* DecodeEncryptKey(std::string publickey) {
	std::string tmppublickey = publickey;
	std::string tmpdevkey = devkey;

	std::reverse(tmppublickey.begin(), tmppublickey.end());
	std::reverse(tmpdevkey.begin(), tmpdevkey.end());

	return base64_decode(tmppublickey + tmpdevkey).data;
}

unsigned char* DecodeEncryptIV(std::string publicvector) {
	std::string tmppublicvector = publicvector;
	std::string tmpdeviv = deviv;

	std::reverse(tmppublicvector.begin(), tmppublicvector.end());
	std::reverse(tmpdeviv.begin(), tmpdeviv.end());

	return base64_decode(tmppublicvector + tmpdeviv).data;
}

extern "C" jstring Java_id_gpi_popplus_CredLib_UserAuth(JNIEnv *env, jclass/* clazz */, jstring publicusername) {
	const char *ccUsername = env->GetStringUTFChars(publicusername, JNI_FALSE);
	std::string strUsername(ccUsername);
	std::reverse(strUsername.begin(), strUsername.end());

	std::string tmpUserAuth = chrUserAuth;
	std::reverse(tmpUserAuth.begin(), tmpUserAuth.end());

	return env-> NewStringUTF((strUsername + tmpUserAuth).c_str());
}

extern "C" jstring Java_id_gpi_popplus_CredLib_PassAuth(JNIEnv *env, jclass/* clazz */, jstring publicpassword) {
	const char *ccPassword = env->GetStringUTFChars(publicpassword, JNI_FALSE);
	std::string strPassword(ccPassword);
	std::reverse(strPassword.begin(), strPassword.end());

	std::string tmpPassAuth = chrPassAuth;
	std::reverse(tmpPassAuth.begin(), tmpPassAuth.end());

	return env-> NewStringUTF((strPassword + tmpPassAuth).c_str());
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

extern "C" jstring Java_id_gpi_popplus_CredLib_DataProcess(JNIEnv *env, jclass/* clazz */,
	jstring mingwen, jstring publickey, jstring publicvector)
{
	const char* ccKey = env->GetStringUTFChars(publickey, JNI_FALSE);
	std::string strKey(ccKey);

	aes256_context ctx;
	aes256_init(&ctx, DecodeEncryptKey(strKey));

	const char *mwChar = env->GetStringUTFChars(mingwen, JNI_FALSE);

	int i;
	int mwSize = strlen(mwChar);
	int remainder = mwSize % BLOCK_SIZE;

	std::string enc;
	int loop=0;

	if (mwSize < BLOCK_SIZE)
	{
		uint8_t input[BLOCK_SIZE];

		for (i = 0; i < BLOCK_SIZE; i++)
		{
			if (i < mwSize)
				input[i] = (unsigned char) mwChar[i];
			else
			{
				if(loop == 0)
					input[i] = 0x23;
				else
					input[i] = (unsigned char) (BLOCK_SIZE - mwSize);

				loop++;
			}
		}

		uint8_t output[BLOCK_SIZE];
		const char* ccVector = env->GetStringUTFChars(publicvector, JNI_FALSE);
		std::string strVector(ccVector);

		aes256_encrypt_cbc(&ctx, input, DecodeEncryptIV(strVector), output);
		enc = base64_encode(output, sizeof(output));
	}
	else
	{
		int group = mwSize / BLOCK_SIZE;
		int size = BLOCK_SIZE * (group + 1);

		uint8_t input[size];

		for (i=0; i<size; i++)
		{
			if (i < mwSize)
				input[i] = (unsigned char) mwChar[i];
			else
			{
				if (remainder == 0)
					input[i] = 0x20;
				else
				{
					if(loop == 0)
						input[i] = 0x23;
					else
						input[i] = (unsigned char) (BLOCK_SIZE - mwSize);

					loop++;
				}
			}
		}

		uint8_t output[size];
		const char* ccVector = env->GetStringUTFChars(publicvector, JNI_FALSE);
		std::string strVector(ccVector);

		aes256_encrypt_cbc(&ctx, input, DecodeEncryptIV(strVector), output);
		enc = base64_encode(output, sizeof(output));
	}

	env->ReleaseStringUTFChars(mingwen, mwChar);
	return env->NewStringUTF(enc.c_str());
}

extern "C" jbyteArray Java_id_gpi_popplus_CredLib_DataDecrypt(JNIEnv *env, jclass/* clazz */, jstring mingwen,
	jstring publickey, jstring publicvector)
{
	const char* ccKey = env->GetStringUTFChars(publickey, JNI_FALSE);
	std::string strKey(ccKey);

	aes256_context ctx;
	aes256_init(&ctx, DecodeEncryptKey(strKey));

	const char *encryptChar = env->GetStringUTFChars(mingwen, JNI_FALSE);
	std::string strData(encryptChar);
	basedata decodebase64 = base64_decode(encryptChar);
	int len = decodebase64.len;

	unsigned char resbuf[len];

	for (int ii = 0; ii < len; ii++) {
		resbuf[ii] = *(decodebase64.data + ii);
	}

	uint8_t output[len];
	const char* ccVector = env->GetStringUTFChars(publicvector, JNI_FALSE);
	std::string strVector(ccVector);

	aes256_decrypt_cbc(&ctx, resbuf, DecodeEncryptIV(strVector), output, len);

	uint8_t pad = output[len - 1];

	if (pad < 1 || pad > 0x20 || pad > 0x23)
		pad = 0;

	int reslen = len - pad;
	char resultChar[reslen];

	for (int i = 0; i < reslen; i++) {
		resultChar[i] = output[i];
	}

	env->ReleaseStringUTFChars(mingwen, encryptChar);
	return charToJbyteArray(env, resultChar, reslen);
}
