/*
 * Copyright (c) 2016 oleh Agustinus Ignat Deswanto
 *
 *  Dilarang menyalah gunakan aplikasi ini terutama untuk tindak kejahatan.
 *  Jika ada pertanyaan seputar aplikasi ini silakan menghubungi :
 *
 *  Agustinus Ignat Deswanto
 *  Permata Depok Nilam F5a No. 5
 *  Citayam - Depok 16430
 *  Jawa Barat - Indonesia
 *  HP/WA : 085770706777
 *  Email : aignatd@gmail.com
 *
 */

package id.gpi.popplus.service;

import id.gpi.popplus.common.FixValue;
import id.gpi.popplus.model.DeviceData;
import id.gpi.popplus.model.ResponsePojo;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Dibuat oleh : ignat
 * Tanggal : 15-Nov-2018
 * HP/WA : 0857 7070 6 777
 */
public interface POSLink
{
  @POST(FixValue.PathCredentials + FixValue.PathGetToken)
  Observable<ResponsePojo> GetTokenService(@Body DeviceData devicedata);
}

