package id.gpi.popplus.base;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import id.gpi.popplus.common.FixValue;
import id.gpi.popplus.model.CredentialsData;
import id.gpi.popplus.model.ResponsePojo;
import id.gpi.popplus.service.BasicAuthInterceptor;
import id.gpi.popplus.service.POSLink;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PosLinkGenerator {

    public POSLink provideManisApi(Context context) {
        return createService(context);
    }

//    public static <S> POSLink createServiceWithToken(Context context) {
//        return retroBuilder(okBuilderToken(context).build()).create(POSLink.class);
//    }

    public static <S> POSLink createService(Context context) {
        return retroBuilder(okBuilder(context).build()).create(POSLink.class);
    }

  private static Retrofit retroBuilder(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(FixValue.POP_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient.Builder okBuilder(Context context) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      return new OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(new BasicAuthInterceptor(FixValue.BasicUser, FixValue.BasicPass))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(chain -> {
          Request request = chain.request();
          Response response = chain.proceed(request);

          try
          {
            Gson gson = new Gson();
            JSONObject json = new JSONObject(response.body().string());
            ResponsePojo responsePojo = gson.fromJson(json.toString(), ResponsePojo.class);
            CredentialsData.getInstance().setResponsePojo(responsePojo);
          }
          catch (JSONException e)
          {
            ResponsePojo responsePojo = new ResponsePojo();
            responsePojo.setaFaultCode(String.valueOf(response.code()));
            responsePojo.setaFaultDescription(response.message());
            CredentialsData.getInstance().setResponsePojo(responsePojo);
          }

          return response;
        });
    }

    private static Request requestBuilderToken(Interceptor.Chain chain, Context context) {
        Request.Builder requestBuilder = chain.request().newBuilder();
        return requestBuilder.build();
    }
}
