#include <iostream>
#include "utils.hpp"
#include <stdio.h>
#include <string.h>
#include <iomanip>
#include <vector>
#include <android/log.h>

/*
#include "../cryptopp/modes.h"
#include "../cryptopp/aes.h"
#include "../cryptopp/filters.h"

#include "../openssl/aes.h"
#include "../openssl/evp.h"
#include "../openssl/ossl_typ.h"
*/

#include "../aeseverywhere/aes256.hpp"

const char* getTimeStamp()
{
	double theTime = (double)time(nullptr);
	std::string strTimeStamp = std::to_string(theTime);
	strTimeStamp[10] = '\0';
	return strTimeStamp.c_str();
}

const char* getRandomKey(size_t length)
{
	auto randchar = []() -> char
	{
		const char charset[] =
			"0123456789"
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			"abcdefghijklmnopqrstuvwxyz";
		const size_t max_index = (sizeof(charset) - 1);
		return charset[ rand() % max_index ];
	};

	std::string str(length, 0);
	std::generate_n(str.begin(), length, randchar);
	return str.c_str();
}

const char* EncryptData(const char* data)
{
//    std::string text = std::string(data);
//    std::string passphrase = std::string("PASSPHRASE");
//    std::string encrypted = AES256::encrypt(text, passphrase);

//    __android_log_print(ANDROID_LOG_DEBUG, "GetPlusLib", "%s", encrypted.c_str());

	return data;
}
