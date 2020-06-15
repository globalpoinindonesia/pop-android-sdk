#ifndef _BASE64_H_
#define _BASE64_H_

#include <string>
#include <vector>

typedef struct {
	unsigned char *data;
	int len;
} basedata;

std::string base64_encode(uint8_t const* buf, unsigned int bufLen);
basedata base64_decode(std::string const&);
std::vector<uint8_t> base64_decode_vector(std::string const&);

#endif
